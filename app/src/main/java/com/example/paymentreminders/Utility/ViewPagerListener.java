package com.example.paymentreminders.Utility;


import android.content.SharedPreferences;

import androidx.viewpager.widget.ViewPager;

public class ViewPagerListener implements ViewPager.OnPageChangeListener {


    private PagerListener<Integer> pagerListener;

    public ViewPagerListener(PagerListener<Integer> pagerListener) {
        this.pagerListener = pagerListener;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        pagerListener.onPageSelected(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public interface PagerListener<T> {
        void onPageSelected(T t);
    }
}
