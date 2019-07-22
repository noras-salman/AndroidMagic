package com.nasable.magiclibs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.nasable.magiclibs.MagicLibs.Navigation.NavigationExample;
import com.nasable.magiclibs.MagicLibs.Navigation.NavigationExample2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(getApplicationContext(), NavigationExample2.class));
    }
}
