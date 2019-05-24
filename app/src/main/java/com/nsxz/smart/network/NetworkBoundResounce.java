package com.nsxz.smart.network;

import com.nsxz.smart.api.ApiResponse;
import com.nsxz.smart.app.AppExecutors;
import com.nsxz.smart.vo.Resource;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class NetworkBoundResounce<ResultType, RequestType> {
    private AppExecutors appExecutors;
    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResounce(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    private void init() {
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, resultType -> {
            result.removeSource(dbSource);
            if (shouldFetch(resultType)) {
                fetchFromNetwork(dbSource);
            } else {
                result.addSource(dbSource, newData -> {
                    setValue(Resource.success(newData));
                });
            }
        });


    }


    private void setValue(Resource<ResultType> newValue) {
        if (!result.getValue().equals(newValue)) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource) {
        LiveData<ApiResponse<ResultType>> apiResponse = createCall();
        result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);
            if (response instanceof ApiResponse) {
                appExecutors.getDiskIO().execute(() -> {
                    saveCallResult(processResponse());
                    appExecutors.getMainThread().execute(() ->
                            result.addSource(loadFromDb(),
                                    newData -> setValue(Resource.success(newData)))
                    );
                });
            }

            if (false) {
                appExecutors.getMainThread().execute(() ->
                        result.addSource(loadFromDb(),
                                newData -> setValue(Resource.success(newData)))
                );
            }

            if (false) {
                onFetchFailed();
                result.addSource(dbSource, newData -> setValue(Resource.error("", newData)));
            }
        });
    }

    protected void onFetchFailed() {}

    public LiveData<Resource<ResultType>> asLiveData(){
        return result;
    }

    @WorkerThread
    protected RequestType processResponse() {
        // TODO: 2019/5/23
        return null;
    }

    @WorkerThread
    protected abstract void saveCallResult(RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @MainThread
    protected abstract LiveData<ApiResponse<ResultType>> createCall();


}
