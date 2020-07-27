package com.example.paymentreminders.Utility;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;


public class PicassoClass {

    public static class Builder {

        private final Picasso.Builder BUILDER;

        public Builder(@NonNull Context context) {
            BUILDER = new Picasso.Builder(context);
        }

        public Builder setOkhttpDownloader(OkHttp3Downloader okhttpDownloader) {
            BUILDER.downloader(okhttpDownloader);
            return this;
        }

        public Picasso getPicasso() {
            return BUILDER.build();
        }


    }

    private PicassoClass() {
    }
}
