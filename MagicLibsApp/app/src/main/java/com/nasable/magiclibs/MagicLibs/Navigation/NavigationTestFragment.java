package com.nasable.magiclibs.MagicLibs.Navigation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nasable.magiclibs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationTestFragment extends Fragment {


    public NavigationTestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_test, container, false);
    }

}
