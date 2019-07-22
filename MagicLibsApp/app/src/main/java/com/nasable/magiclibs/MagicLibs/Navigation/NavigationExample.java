package com.nasable.magiclibs.MagicLibs.Navigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.nasable.magiclibs.MagicLibs.Navigation.NavigationTestFragment;
import com.nasable.magiclibs.MagicLibs.Navigation.ScreenSliderAdapter;
import com.nasable.magiclibs.R;

import java.util.ArrayList;
import java.util.List;

public class NavigationExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example);

        ViewPager viewPager=findViewById(R.id.viewPager);
        TabLayout tabLayout=findViewById(R.id.tabLayout);

        List<Fragment> fragments=new ArrayList<Fragment>();
        fragments.add(new NavigationTestFragment());
        fragments.add(new NavigationTestFragment());
        fragments.add(new NavigationTestFragment());
        fragments.add(new NavigationTestFragment());
        fragments.add(new NavigationTestFragment());
        fragments.add(new NavigationTestFragment());
        fragments.add(new NavigationTestFragment());
        ScreenSliderAdapter screenSliderAdapter=new ScreenSliderAdapter(getSupportFragmentManager(),fragments);



        List<String> titles=new ArrayList<String>();
        titles.add("Test1");
        titles.add("Test2");
        titles.add("Test3");
        titles.add("Test4");
        titles.add("Test5");
        titles.add("Test6");
        titles.add("Test7");


        screenSliderAdapter.setPageTitles(titles);
        viewPager.setAdapter(screenSliderAdapter);

        screenSliderAdapter.addTabs(viewPager,tabLayout);


        screenSliderAdapter.setZoomTransformer(viewPager);


    }
}
