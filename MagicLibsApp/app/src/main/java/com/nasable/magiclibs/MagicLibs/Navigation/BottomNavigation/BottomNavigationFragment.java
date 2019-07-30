package com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation;


import androidx.fragment.app.Fragment;

import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Abstraction.AbstractBottomBarFragment;
import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Abstraction.AbstractBottomNavigationFragment;
import com.nasable.magiclibs.MagicLibs.Navigation.NavigationTestFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationFragment extends AbstractBottomNavigationFragment {

    @Override
    public AbstractBottomBarFragment getBottomBarFragment() {

        return new BottomBarFragment();
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> list=new ArrayList<>();
        list.add(new NavigationTestFragment());
        list.add(new NavigationTestFragment());
        list.add(new NavigationTestFragment());
        return list;
    }
}