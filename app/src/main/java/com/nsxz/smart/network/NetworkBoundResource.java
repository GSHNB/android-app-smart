package com.nsxz.smart.network;

import com.nsxz.smart.api.ApiResponse;
import com.nsxz.smart.app.AppExecutors;
import com.nsxz.smart.vo.Resource;

import androidx.annotation.MainThread;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

public abstract class NetworkBoundResource<ResultType, RequestType> {
    private AppExecutors appExecutors;
    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
        init();
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
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            if (response.isSuccessful()) {
                appExecutors.getDiskIO().execute(() -> {
                    saveCallResult(processResponse(response));
                    appExecutors.getMainThread().execute(() ->
                            result.addSource(loadFromDb(),
                                    newData -> setValue(Resource.success(newData)))
                    );
                });
            } else {
                onFetchFailed();
                result.addSource(dbSource, newData -> setValue(Resource.error(response.errorMessage, newData)));
            }
        });
    }

    public LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

    @MainThread
    protected void onFetchFailed() {
    }

    @WorkerThread
    protected RequestType processResponse(ApiResponse<RequestType> response) {
        return response.body;
    }

    @WorkerThread
    protected abstract void saveCallResult(RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(ResultType data);

    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();


}
