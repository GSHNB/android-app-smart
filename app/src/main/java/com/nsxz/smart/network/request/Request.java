package com.nsxz.smart.network.request;

import com.nsxz.smart.bean.Aa;
import com.nsxz.smart.bean.MallCateShell;
import com.nsxz.smart.bean.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gaoshun on 2019/3/5.
 */
public interface Request {

    @GET("v2/dianshang/channelpage/lists")
    Observable<MallCateShell> getNews();

    @GET("data/福利/3/1")
    Observable<Aa> getMallCate();

    @GET("da")
    Call<User> getUser();
}
