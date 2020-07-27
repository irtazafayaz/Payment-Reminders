package com.example.paymentreminders;

import android.content.Intent;
import android.os.Bundle;

import com.example.paymentreminders.MainScreen.PagerAdapterClass;
import com.example.paymentreminders.NewRequests.RequestActivity;
import com.example.paymentreminders.Utility.OnTabSelectedCustomListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class HomePageActivity extends BaseActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem request_tabItem;
    TabItem received_tabItem;
    PagerAdapterClass pagerAdapterClass;
    ViewPager viewPager;

    FloatingActionButton floatingActionButton;

    @Override
    int getLayoutRes() {
        return R.layout.activity_home_page;
    }

    @Override
    void inOnCreateView(Bundle bundle) {
        toolbar.setTitle("Main Screen");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        pagerAdapterClass = new PagerAdapterClass(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapterClass);

        tabLayout.addOnTabSelectedListener(new OnTabSelectedCustomListener(tab -> viewPager.setCurrentItem(tab.getPosition(), true)));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(V -> {
            Intent intent = new Intent(HomePageActivity.this, RequestActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void initViews() {
        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        request_tabItem = findViewById(R.id.request);
        received_tabItem = findViewById(R.id.received);
    }


}
