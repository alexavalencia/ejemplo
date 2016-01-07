package com.alexandracastrillonvalencia.ejemplo;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilmsFragment extends Fragment {


    public FilmsFragment() {
        // Required empty public constructor
    }

    public static TabLayout tabLayout;
    public  static ViewPager viewPager;
    public static int count_item=2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View auxi=inflater.inflate(R.layout.fragment_films,null);
        tabLayout=(TabLayout)auxi.findViewById(R.id.tab);
        viewPager=(ViewPager)auxi.findViewById(R.id.viewpager);

        viewPager.setAdapter(new mPagerAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return auxi;
    }
    class mPagerAdapter extends FragmentPagerAdapter {
        public mPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0: return new CarteleraFragment();
                case 1: return new EstrenosFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return count_item;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String[] menu;
            menu=getResources().getStringArray(R.array.films);
            switch (position){
                case 0:
                    return menu[0];
                case 1:
                    return menu[1];
            }
            return null;
        }
    }

}
