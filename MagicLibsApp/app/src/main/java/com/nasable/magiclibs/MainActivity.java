package com.nasable.magiclibs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;


import com.nasable.magiclibs.MagicLibs.Navigation.NavigationExample;
import com.nasable.magiclibs.MagicLibs.Navigation.NavigationExample2;
import com.nasable.magiclibs.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        
        startActivity(new Intent(getApplicationContext(), NavigationExample2.class));
    }
}
