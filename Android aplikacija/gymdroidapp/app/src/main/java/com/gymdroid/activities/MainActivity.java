package com.gymdroid.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.fragments.main.EquipmentFragment;
import com.gymdroid.fragments.main.MainFragment;
import com.gymdroid.fragments.main.WeightFragment;
import com.gymdroid.fragments.main.WorkoutFragment;

public class MainActivity extends WhiteActivity {

    private static final int NUMBER_OF_FRAGMENTS = 4;
    private static final int STARTER_FRAGMENT = 1;

    ViewPager navigationViewPager;
    PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        Intent intent = getIntent();
        int starter_fragment = intent.getIntExtra("STARTER_FRAGMENT", 1);

        navigationViewPager = findViewById(R.id.navigationViewPager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        navigationViewPager.setAdapter(pagerAdapter);
        navigationViewPager.setCurrentItem(starter_fragment);

        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(navigationViewPager, true);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new WorkoutFragment();
                case 1:
                    return new MainFragment();
                case 2:
                    return new WeightFragment();
                default:
                    return new EquipmentFragment();
            }
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Logout
        allServices.getConfigurationService().clearUser();
        allServices.getStartActivityService().startSplashScreenActivity(activity);
    }
}
