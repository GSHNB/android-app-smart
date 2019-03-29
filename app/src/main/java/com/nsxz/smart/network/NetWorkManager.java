package com.nsxz.smart.network;

import com.nsxz.smart.network.request.Request;
import com.nsxz.smart.network.ssl.TrustAllCerts;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaoshun on 2019/3/18.
 */

public class NetWorkManager {
    private static NetWorkManager mInstance;
    private static Retrofit retrofit;
    private static Request request;

    public static NetWorkManager getInstance() {
        if (mInstance == null) {
            synchronized (NetWorkManager.class) {
                if (mInstance == null) {
                    mInstance = new NetWorkManager();
                }
            }
        }
        return mInstance;
    }

    public void init() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
//                .sslSocketFactory(createSSLSocketFactory())
//                .hostnameVerifier(new TrustAllCerts.TrustAllHostnameVerifier())
                .addInterceptor(logging)
                .build();
//        OkHttpClient client = new OkHttpClient();
//        client.newBuilder().addInterceptor(logging).build();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(HostManager.getHost())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Request getRequest() {
        if (request == null) {
            synchronized (Request.class) {
                request = retrofit.create(Request.class);
            }
        }
        return request;
    }

    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory factory = null;
        try {
            SSLContext ssl = SSLContext.getInstance("TLS");
            ssl.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            factory = ssl.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return factory;
    }

}
