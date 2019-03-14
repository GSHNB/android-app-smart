package com.nsxz.smart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.nsxz.smart.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        sendBroadcast();
//        Observable novel = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
//                observableEmitter.onNext("1");
//                observableEmitter.onNext("2");
//                observableEmitter.onComplete();
//            }
//        });


//        Observer<String> reader = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable disposable) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };
//
//        novel.subscribe(reader);
//
//        Observable.create(
//                new ObservableOnSubscribe<Object>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
//
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//
//                    }
//                });
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.1.8:8080/RetrofitService/")
//                .build();
//        IGetRequest requestService = retrofit.create(IGetRequest.class);
//        requestService.getNews(1).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
//
//            }
//        });


    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Double.parseDouble(null);
//        Toast.makeText(this, "bugly修复成功", Toast.LENGTH_SHORT).show();
    }
}
