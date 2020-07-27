package com.example.paymentreminders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;

    SliderAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    private int[] slide_images = {
            R.drawable.layer1,
            R.drawable.layer2,
            R.drawable.layer3
    };


    private String[] slide_heading = {
            "Money Record", "Easy Payment", "Notification Alert"
    };


    @Override
    public int getCount() {
        return slide_heading.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);


        TextView heading = view.findViewById(R.id.slide_main_heading);
        ImageView photo = view.findViewById(R.id.slide_img);

        heading.setText(slide_heading[position]);
        photo.setImageResource(slide_images[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
