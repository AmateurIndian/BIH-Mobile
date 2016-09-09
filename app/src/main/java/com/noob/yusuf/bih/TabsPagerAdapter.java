package com.noob.yusuf.bih;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	boolean isConnected;

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			return new LibraryInfo();
		case 1:
			return new SportsInfo();
		case 2:
			return new RadioLife();
		case 3:
			return new HealthInfo();
		case 4:
			return new PostalInfo();
		case 5:
			return new BookInfo();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 6;
	}




}
