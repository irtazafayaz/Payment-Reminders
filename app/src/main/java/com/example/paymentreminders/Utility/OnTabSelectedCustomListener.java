package com.example.paymentreminders.Utility;

import com.google.android.material.tabs.TabLayout;

public class OnTabSelectedCustomListener implements TabLayout.OnTabSelectedListener {

    private CustomListenerInterface customListenerInterface;

    public OnTabSelectedCustomListener(CustomListenerInterface customListenerInterface) {
        this.customListenerInterface = customListenerInterface;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        customListenerInterface.onTabSelected(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    public interface CustomListenerInterface {
        void onTabSelected(TabLayout.Tab tab);
    }

}
