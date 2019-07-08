package com.nasable.adapter.NavigationUI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nasable.adapter.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AppBarFragment extends Fragment {


    public AppBarFragment() {
        // Required empty public constructor
    }

    RelativeLayout search_button;
    RelativeLayout cart_button;
    RelativeLayout toggle_button;
    TextView badge;
    View.OnClickListener onClickListenerToggle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_app_bar, container, false);
        toggle_button=root.findViewById(R.id.toggle_button);
        search_button=root.findViewById(R.id.search_button);
        badge=root.findViewById(R.id.badge);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        cart_button=root.findViewById(R.id.cart_button);
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        toggle_button.setOnClickListener(onClickListenerToggle);
        return root;
    }

    public void setOnClickListenerToggle(final View.OnClickListener onClickListenerToggle) {

        this.onClickListenerToggle=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(getContext(),R.anim.rotate_left);
                rotate.setFillAfter(true);
                onClickListenerToggle.onClick(v);
                toggle_button.startAnimation(rotate);
            }
        };
    }

    public void setBadgeText(String text){
        badge.setText(text);
        Animation grow = AnimationUtils.loadAnimation(getContext(),R.anim.grow);
        badge.startAnimation(grow);
    }
}
