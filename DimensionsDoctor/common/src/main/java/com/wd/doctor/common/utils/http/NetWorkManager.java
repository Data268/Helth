package com.wd.doctor.common.utils.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.wd.doctor.common.core.BasePresenter;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description ：
 * project name：DimensionMedical
 * author : 李旭斌
 * creation date: 2019/7/10 16:23
 *
 * @version 1.0
 */
public class NetWorkManager {
    OkHttpClient okHttpClient;
    private static NetWorkManager netWorkManager;
    private static Retrofit app_retrofit,baidu_retrofit;
//    public final static String BASE_URL = "http://mobile.bwstudent.com/";
public final static String BASE_URL = "http://172.17.8.100/";


    //网络判断
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    private NetWorkManager(){

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();


        app_retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();


        //第三方的SDK
        baidu_retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)//base_url:http+域名
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用Rxjava对回调数据进行处理
                .addConverterFactory(GsonConverterFactory.create())//响应结果的解析器，包含gson，xml，protobuf
                .build();
    }


    public static synchronized NetWorkManager getInstener(){

        if (netWorkManager==null){
            netWorkManager = new NetWorkManager();
        }

        return netWorkManager;
    }



    public <T> T create(int requestType ,final Class<T> service){
        if (requestType == BasePresenter.REQUEST_TYPE_SDK_BD){//如果请求百度SDK的接口
            return baidu_retrofit.create(service);
        }
        return app_retrofit.create(service);
    }


}
