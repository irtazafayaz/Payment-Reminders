package com.example.paymentreminders;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.paymentreminders.Utility.ViewPagerListener;

import androidx.viewpager.widget.ViewPager;

public class MainActivity extends MiddlewareClass {

    private final float MIN_SCALE = 0.65f;
    private final float MIN_ALPHA = 0.3f;
    ViewPager viewPager;
    LinearLayout linearLayout;
    SliderAdapter sliderAdapter;
    private TextView[] mDots;

    private Button getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearlayout);
        viewPager = findViewById(R.id.viewpager);
        getStarted = findViewById(R.id.getstarted);
        sliderAdapter = new SliderAdapter(MainActivity.this);
        viewPager.setAdapter(sliderAdapter);

        viewPager.setPageTransformer(true, this::zoomOutTransformation);

        addDosts(0);
        viewPager.addOnPageChangeListener(new ViewPagerListener(this::addDosts));
        getStarted.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

    }

    private void zoomOutTransformation(View page, float position) {
        if (position < -1)
            page.setAlpha(0f);
        else if (position <= 1) {
            page.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(position)));
            page.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(position)));
            page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));
        } else
            page.setAlpha(0f);
    }

    public void addDosts(int position) {
        mDots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(50);
            mDots[i].setTextColor(getResources().getColor(R.color.transwhite));
            linearLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
