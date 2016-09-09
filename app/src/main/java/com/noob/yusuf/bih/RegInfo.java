package com.noob.yusuf.bih;

/**
 * Created by yusuf on 17.08.2016.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class RegInfo extends FragmentActivity implements  OnTabChangeListener, OnPageChangeListener {

    private RegTabsAdapter mAdapter;
    private ViewPager mViewPager;
    private TabHost mTabHost;
    private HorizontalScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_life);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        scroll = (HorizontalScrollView)findViewById(R.id.horizontalScroll);

        // Tab Initialization
        initialiseTabHost();
        mAdapter = new RegTabsAdapter(getSupportFragmentManager());
        // Fragments and ViewPager Initialization


        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(RegInfo.this);
    }

    // Method to add a TabHost
    private static void AddTab(RegInfo activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
        tabSpec.setContent(new MyTabFactory(activity));
        tabHost.addTab(tabSpec);
    }

    // Manages the Tab changes, synchronizing it with Pages
    public void onTabChanged(String tag) {
        int pos = this.mTabHost.getCurrentTab();
        this.mViewPager.setCurrentItem(pos);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    // Manages the Page changes, synchronizing it with Tabs
    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        int pos = this.mViewPager.getCurrentItem();
        this.mTabHost.setCurrentTab(pos);
        Log.d("currTab", pos+"");

        View tabView = mTabHost.getTabWidget().getChildAt(pos);
        if (tabView != null)
        {
            final int width = scroll.getWidth();
            final int scrollPos = tabView.getLeft() - (width - tabView.getWidth()) / 2;
            scroll.scrollTo(scrollPos, 0);
        } else {
            scroll.scrollBy(0, 0);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
    }


    // Tabs Creation
    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        // TODO Put here your Tabs
        RegInfo.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Required Documents").setIndicator("Required Documents"));
        RegInfo.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Scholarships").setIndicator("Scholarships"));
        RegInfo.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Residence Permit").setIndicator("Residence Permit"));
        RegInfo.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tuition Fees").setIndicator("Tuition Fees"));
        RegInfo.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Misc.").setIndicator("Misc."));



        mTabHost.setOnTabChangedListener(this);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
