package com.noob.yusuf.bih;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class CampusLife extends FragmentActivity implements  OnTabChangeListener, OnPageChangeListener {

    private TabsPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private TabHost mTabHost;
    private HorizontalScrollView scroll;
    int width;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_life);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        scroll = (HorizontalScrollView)findViewById(R.id.horizontalScroll);


        // Tab Initialization
        initialiseTabHost();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        // Fragments and ViewPager Initialization


        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(CampusLife.this);
    }

    // Method to add a TabHost
    private static void AddTab(CampusLife activity, TabHost tabHost, TabHost.TabSpec tabSpec) {
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
        this.scroll.scrollTo(((int)(width*(arg0/(double)(mTabHost.getChildCount()-1)))),0);


        View tabView = mTabHost.getTabWidget().getChildAt(pos);
        if (tabView != null)
        {
            final int width = scroll.getWidth();
            final int scrollPos = tabView.getLeft() - (width - tabView.getWidth()) / 2;
            scroll.scrollTo(scrollPos, 0);
        } else {
            scroll.scrollBy(0, 0);
        }



        //Log.d("width", (width*(arg0/(double)(mTabHost.getChildCount()-1)) +""));
       /* Log.d("currTab", pos+"");
        if(pos == 3)
            scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        if(pos == 2)
            scroll.fullScroll(HorizontalScrollView.FOCUS_LEFT);*/
    }

    @Override
    public void onPageSelected(int arg0) {
    }


    // Tabs Creation
    private void initialiseTabHost() {
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        // TODO Put here your Tabs
        CampusLife.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Library").setIndicator("Library"));
        CampusLife.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Sports").setIndicator("Sports"));
        CampusLife.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Radio").setIndicator("Radio"));
        CampusLife.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Health Center").setIndicator("Health Center"));
        CampusLife.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Post & Banking").setIndicator("Post & Banking"));
        CampusLife.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("Books").setIndicator("Books"));



        mTabHost.setOnTabChangedListener(this);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
