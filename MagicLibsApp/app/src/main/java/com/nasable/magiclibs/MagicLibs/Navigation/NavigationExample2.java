package com.nasable.magiclibs.MagicLibs.Navigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.BottomNavigationFragment;
import com.nasable.magiclibs.R;

public class NavigationExample2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_example2);

        /* This is what the layout looks like */
        /**
         *    <FrameLayout
         *         android:id="@+id/bottomNavigationFragmentHolder"
         *         android:layout_width="match_parent"
         *         android:layout_height="match_parent" />
         */
        getSupportFragmentManager().beginTransaction().replace(R.id.bottomNavigationFragmentHolder,new BottomNavigationFragment()).commit();
    }





}
