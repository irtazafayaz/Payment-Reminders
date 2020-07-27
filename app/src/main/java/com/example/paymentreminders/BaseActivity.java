package com.example.paymentreminders;


import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.paymentreminders.Utility.RetrofitInterface;
import com.squareup.picasso.Picasso;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

    protected OkHttpClient okHttpClient;
    protected Picasso picasso;
    protected RetrofitInterface retrofit;

    @LayoutRes
    abstract int getLayoutRes();

    abstract void inOnCreateView(Bundle bundle);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        initViews();

        MyCustomApplicationClass myCustomApplicationClass = (MyCustomApplicationClass) getApplicationContext();
        okHttpClient = myCustomApplicationClass.getOkHttpClient();
        picasso = myCustomApplicationClass.getPicasso();
        retrofit = myCustomApplicationClass.getRetrofit();
        inOnCreateView(savedInstanceState);
    }

    protected void initViews() {

    }
}
