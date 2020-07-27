package com.example.paymentreminders;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paymentreminders.Utility.RetrofitInterface;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import okhttp3.OkHttpClient;

@SuppressLint("Registered")
public abstract class BaseFragment extends androidx.fragment.app.Fragment {

    private OkHttpClient okHttpClient;
    protected Picasso picasso;
    private RetrofitInterface retrofit;
    protected Context context;

    public abstract void inOnCreateView(View view);

    public abstract int getLayoutResource();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutResource(), container, false);

        MyCustomApplicationClass myCustomApplicationClass = getApp();
        okHttpClient = myCustomApplicationClass.getOkHttpClient();
        picasso = myCustomApplicationClass.getPicasso();
        retrofit = myCustomApplicationClass.getRetrofit();

        inOnCreateView(view);
        return view;
    }

    private MyCustomApplicationClass getApp() {
        return (MyCustomApplicationClass) context.getApplicationContext();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}
