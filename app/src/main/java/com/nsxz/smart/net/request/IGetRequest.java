package com.nsxz.smart.net.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by gaoshun on 2019/3/5.
 */
public interface IGetRequest {
    @GET("NewsServlet")
    Call<ResponseBody> getNews(@Query("id") int id);
}
