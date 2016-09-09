package com.noob.yusuf.bih;

/**
 * Created by yusuf on 17.08.2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RegTabsAdapter extends FragmentPagerAdapter {

    boolean isConnected;

    public RegTabsAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new RegDocuments();
            case 1:
                return new RegScholarship();
            case 2:
                return new RegPermit();
            case 3:
                return new RegTuition();
            case 4:
                return new RegOther();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
