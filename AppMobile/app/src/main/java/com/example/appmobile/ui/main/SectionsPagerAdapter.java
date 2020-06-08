package com.example.appmobile.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.appmobile.AccueilFragment;
import com.example.appmobile.StatsFragment;
import com.example.appmobile.CompteFragment;
import com.example.appmobile.PhotoFragment;
import com.example.appmobile.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2,R.string.tab_text_3,R.string.tab_text_4,R.string.tab_text_5};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public Fragment getItem(int position) {
      Fragment fragment =null;
      switch (position){
          case 0:
              fragment = new AccueilFragment();
              break;
          case 1:
              fragment=new PhotoFragment();
              break;
          case 2:
              fragment=new StatsFragment();
              break;
          case 3:
              fragment=new CompteFragment();
              break;

      }
      return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }
}