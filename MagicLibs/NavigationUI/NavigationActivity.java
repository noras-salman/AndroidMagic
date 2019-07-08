package com.nasable.adapter.NavigationUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.nasable.adapter.R;

public class NavigationActivity extends AppCompatActivity {

    AppBarFragment appBarFragment;
    NavigationFragment navigationFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        appBarFragment= new AppBarFragment();

        navigationFragment =new NavigationFragment();


        appBarFragment.setOnClickListenerToggle(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFragment.toggle();
                appBarFragment.setBadgeText("12");
            }
        });

        build();
    }

    public void build(){
        getSupportFragmentManager().beginTransaction().add(R.id.app_bar,appBarFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.navigation_fragment, navigationFragment).commit();


    }
}
