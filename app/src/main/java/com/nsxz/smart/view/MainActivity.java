package com.nsxz.smart.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nsxz.smart.R;
import com.nsxz.smart.bean.Aa;
import com.nsxz.smart.bean.MallCateShell;
import com.nsxz.smart.network.NetWorkManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getCate();


    }


    public void getCate() {
        NetWorkManager.getInstance()
                .getRequest()
                .getMallCate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Aa>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Aa cateResponse) {
                        Log.i("main", cateResponse.toString());
                        tv.setText(cateResponse.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("main", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        NetWorkManager.getInstance()
                .getRequest()
                .getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MallCateShell>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MallCateShell s) {
                        Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
