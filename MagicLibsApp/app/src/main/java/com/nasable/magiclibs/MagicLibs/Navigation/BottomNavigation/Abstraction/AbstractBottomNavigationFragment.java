package com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Abstraction;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nasable.magiclibs.MagicLibs.Navigation.ScreenSliderAdapter;
import com.nasable.magiclibs.R;

import java.util.List;

/**
 * Extends this
 * requires layout/_magic_theme_fragment_abstract_bottom_navigation.xmlavigation.xml
 */
public abstract class AbstractBottomNavigationFragment extends Fragment {


    public AbstractBottomNavigationFragment() {
        // Required empty public constructor
    }

    AbstractBottomBarFragment bottomBarFragment;

    /**
     *
     * @return a new instance of an object from a class that extends AbstractBottomBarFragment
     */
    public abstract AbstractBottomBarFragment getBottomBarFragment();

    /**
     *
     * @return a list of fragments that will be the content
     */
    public abstract List<Fragment> getFragments();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout._magic_theme_fragment_abstract_bottom_navigation, container, false);


        final ViewPager viewPager=root.findViewById(R.id.viewPager);

        bottomBarFragment=getBottomBarFragment();

        List<Fragment> fragments=getFragments();

        ScreenSliderAdapter screenSliderAdapter=new ScreenSliderAdapter(getFragmentManager(),fragments);

        viewPager.setAdapter(screenSliderAdapter);
        screenSliderAdapter.setZoomTransformer(viewPager);

        bottomBarFragment.setOnBottomNavigationSelectListener(new AbstractBottomBarFragment.OnBottomNavigationSelectListener() {
            @Override
            public void onSelect(int index) {
                viewPager.setCurrentItem(index,true);
            }
        });

        getFragmentManager().beginTransaction().replace(R.id.bottomNavigation, bottomBarFragment).commit();
        return root;
    }

}
