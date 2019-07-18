package com.nasable.magiclibs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.BottomNavigationFragment;

public class NavigationExample2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example2);
        getSupportFragmentManager().beginTransaction().replace(R.id.bottomNavigationFragmentHolder,new BottomNavigationFragment()).commit();
    }





}
