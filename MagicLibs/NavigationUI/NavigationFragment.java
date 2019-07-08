package com.nasable.adapter.NavigationUI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.nasable.adapter.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {


    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_navigation, container, false);
        drawerLayout=root.findViewById(R.id.drawer);
        contentLayout=root.findViewById(R.id.content);
        overlay=root.findViewById(R.id.overlay);

        root.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                 if(isShowing && (drawerLayout.getWidth()<event.getX()))
                     hide();

                return false;
            }
        });


        getFragmentManager().beginTransaction().add(R.id.drawer,new DrawerFragment()).commit();
        return root;

    }


    public void setContent(Fragment fragment){
        Animation alpha = AnimationUtils.loadAnimation(getContext(),R.anim.alpha_in);
        contentLayout.startAnimation(alpha);
        getFragmentManager().beginTransaction().replace(R.id.content,fragment).commit();
    }

    private boolean isShowing=false;
    FrameLayout contentLayout;
    FrameLayout drawerLayout;
    FrameLayout overlay;

    public void toggle(){
        if(isShowing)
            hide();
        else
            show();
    }

    public void show(){
        Animation enter = AnimationUtils.loadAnimation(getContext(),R.anim.enter_from_left);
        Animation alpha = AnimationUtils.loadAnimation(getContext(),R.anim.alpha_in);
        enter.setFillAfter(true);
        alpha.setFillAfter(true);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                overlay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        drawerLayout.startAnimation(enter);
        overlay.startAnimation(alpha);
        isShowing=true;
    }

    public void hide(){
        Animation leave = AnimationUtils.loadAnimation(getContext(),R.anim.leave_to_left);
        Animation alpha = AnimationUtils.loadAnimation(getContext(),R.anim.alpha_out);
        alpha.setFillAfter(true);
        drawerLayout.startAnimation(leave);
        overlay.startAnimation(alpha);
        isShowing=false;
    }

}
