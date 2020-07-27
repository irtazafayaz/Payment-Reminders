package com.example.paymentreminders.Utility;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpClass {

    private OkhttpClass() {
    }

    public static class Builder {

        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();
        private boolean isDebugable = false;

        public Builder config(boolean isDebugable) {
            this.isDebugable = isDebugable;
            return this;
        }

        public Builder addInterceptor(HttpLoggingInterceptor interceptor) {
            if (isDebugable)
                BUILDER.addInterceptor(interceptor);
            return this;
        }

        public Builder addCache(Cache cache) {
            BUILDER.cache(cache);
            return this;
        }

        public OkHttpClient build() {
            return BUILDER.build();
        }
    }
}


