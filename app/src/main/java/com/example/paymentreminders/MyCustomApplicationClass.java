package com.example.paymentreminders;


import android.app.Application;

import com.example.paymentreminders.Utility.CacheClass;
import com.example.paymentreminders.Utility.OkhttpClass;
import com.example.paymentreminders.Utility.PicassoClass;
import com.example.paymentreminders.Utility.RetrofitInterface;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyCustomApplicationClass extends Application {

    private OkHttpClient okHttpClient;
    private Picasso picasso;
    private RetrofitInterface retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        okHttpClient = new OkhttpClass.Builder()
                .addCache(CacheClass.getCache(this, 28 * 1024 * 8))
                .addInterceptor(new HttpLoggingInterceptor())
                .config(true)
                .build();

        picasso = new PicassoClass.Builder(this)
                .setOkhttpDownloader(new OkHttp3Downloader(okHttpClient))
                .getPicasso();

        retrofit = new RetrofitClass.Builder()
                .addBaseUrl("http://google.com")
                .addClient(okHttpClient)
                .addGSONConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public Picasso getPicasso() {
        return picasso;
    }

    public RetrofitInterface getRetrofit() {
        return retrofit;
    }
}
