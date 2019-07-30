package com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Views;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewHolder;
import com.nasable.magiclibs.R;


public class IconButtonView extends MagicViewHolder {
    /**
     * @param context        The context.. The activity..
     **/

    private TextView badge;
    private TextView under_text;
    private  ImageView icon;
    private LinearLayout holder_button;
    private int index=0;
    private int iconResource=-1;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public IconButtonView(Context context) {
        super(context, R.layout._magic_theme_view_icon_button);
    }

     @Override
    public void bindView(View parent) {
        badge=parent.findViewById(R.id.badge);
        under_text=parent.findViewById(R.id.under_text);
        icon=parent.findViewById(R.id.icon);
        holder_button=parent.findViewById(R.id.holder_button);

        //init
        setBadgeVisible(false);
        setBadgeCounter(0);
    }

    public void setUnderTextStyle(int style){
        under_text.setTypeface(null, style);
    }

    public void startIconAnimation(Animation animation){
        icon.startAnimation(animation);
    }

    public void setWeight(float weight){
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        param.weight=weight;
        holder_button.setLayoutParams(param);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        holder_button.setOnClickListener(onClickListener);
    }

    public void setUnderText(String text){
        under_text.setText(text);
    }



    public void setIconTint(int color){
        if(iconResource!=-1) {
            icon.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        }
    }


    public void setUnderTextColor(int color){
        under_text.setTextColor(color);
    }


    public void setIcon(int resource){
        iconResource=resource;
        icon.setImageDrawable(ContextCompat.getDrawable(getContext(),resource));
    }

    public void setBadgeCounter(int count){
        badge.setText(""+count);
    }

    public void setBadgeVisible(boolean visible){
        if(visible){
            badge.setVisibility(View.VISIBLE);
        }else {
            badge.setVisibility(View.INVISIBLE);
        }
    }

    public void seLabelVisible(boolean visible){
        if(visible){
            under_text.setVisibility(View.VISIBLE);
        }else {
            under_text.setVisibility(View.GONE);
        }
    }

    @Override
    public void buildView(Object object) {

    }

    @Override
    public MagicViewHolder getInstance() {
        return new IconButtonView(getContext());
    }
}
