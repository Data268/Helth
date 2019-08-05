package com.wd.doctor.common.bean.be;

import android.util.Log;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static final int DEFAULT_TIME=10;
    //双重锁
    private static RetrofitUtils retrofitUtils;
    private void RetrofitUtils(){}
    public static RetrofitUtils getRetrofitUtils(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    //返回OkHttpClient 通过应用拦截器添加公共参数
    public OkHttpClient getOkHttp(final String sessionId, final String userId) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("sessionId", sessionId)
                                .addHeader("userId", userId)
                                .build();
                        return chain.proceed(request);
                    }
                })
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(DEFAULT_TIME,TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
        return okHttpClient;


    }

    //初始化必要对象和参数------有请求头的
    public Retrofit getRetrofitHeader(String url, final String sessionId, final String userId){

        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttp(userId,sessionId))
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return  retrofit;

    }

    public <T> T getApiServiceHeader(String url, final String sessionId, final String userId, Class<T> service) {

        Retrofit retrofit = getRetrofitHeader(url,sessionId,userId);
        //通过java动态代理模式获取代理对象
        T t = retrofit.create(service);

        return t;

    }







    //没有请求头的
    public Retrofit getRetrofit(String url){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(DEFAULT_TIME,TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return  retrofit;

    }

    public <T> T getApiService(String url, Class<T> service) {

        Retrofit retrofit = getRetrofit(url);
        //通过java动态代理模式获取代理对象
        T t = retrofit.create(service);
        return t;

    }







    //日志拦截器
    public class LogInterceptor implements Interceptor {
        private String TAG = "okhttp";

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.w(TAG,"request:" + request.toString());
            long t1 = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            Log.w(TAG,String.format(Locale.getDefault(),
                    "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.w(TAG, "response body:" + content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }



}

