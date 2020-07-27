package com.example.paymentreminders;

import com.example.paymentreminders.Utility.RetrofitInterface;

import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClass {

    static class Builder {
        private static final Retrofit.Builder BUILDER = new Retrofit.Builder();

        Builder addGSONConverterFactory(@NonNull GsonConverterFactory gsonConverterFactory) {
            BUILDER.addConverterFactory(gsonConverterFactory);
            return this;
        }

        Builder addClient(@NonNull OkHttpClient okHttpClient) {
            BUILDER.client(okHttpClient);
            return this;
        }

        Builder addBaseUrl(String baseUrl) {
            BUILDER.baseUrl(baseUrl);
            return this;
        }

        RetrofitInterface build() {
            return BUILDER.build().create(RetrofitInterface.class);
        }
    }

    private RetrofitClass() {
    }
}


