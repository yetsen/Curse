package tr.com.orties.curse.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import tr.com.orties.curse.fragments.TabFragment2;
import tr.com.orties.curse.fragments.TabFragment1;

/**
 * Created by Yunus on 3.5.2015.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
