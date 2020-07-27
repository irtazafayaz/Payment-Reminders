package com.example.paymentreminders.Utility;

import android.content.Context;

import okhttp3.Cache;

public class CacheClass {

    private static Cache cache;

    public static Cache getCache(Context context, int cacheSize) {
        if (cache == null) {
            cache = new Cache(context.getApplicationContext().getCacheDir(), cacheSize);
        }
        return cache;
    }

    private CacheClass() {
    }
}
