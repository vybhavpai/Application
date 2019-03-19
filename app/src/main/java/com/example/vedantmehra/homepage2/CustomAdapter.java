package com.example.vedantmehra.homepage2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CustomAdapter extends FragmentPagerAdapter {
    public int stat,pos;
    public CustomAdapter(FragmentManager a,int status,int position){
        super(a);
        this.pos= position;
        this.stat= status;
    }
    public String tabtitle[] = new String[] {"Events", "Requests"};
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new EventFragment();
        } else
            return new RequestFragment();

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitle[position];
    }
}
