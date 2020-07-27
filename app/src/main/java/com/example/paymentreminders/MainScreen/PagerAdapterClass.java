package com.example.paymentreminders.MainScreen;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapterClass extends FragmentPagerAdapter {

    private List<Fragment> mainFragments = Arrays.asList(new ReceivedFragment(), new RequestFragment());

    public PagerAdapterClass(FragmentManager fragmentManager) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mainFragments.get(position);
    }

    @Override
    public int getCount() {
        return mainFragments.size();
    }
}
