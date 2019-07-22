package com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewHolder;
import com.nasable.magiclibs.R;

import java.util.ArrayList;
import java.util.List;



public class IconButtonRow extends MagicViewHolder {
    /**
     * @param context        The context.. The activity..
     **/

    Animation selectAnimation;
    public IconButtonRow(Context context,LinearLayout linearLayout) {
        super(context, R.layout._magic_theme_view_icon_button_row);
        row=linearLayout;
        selectAnimation= AnimationUtils.loadAnimation(getContext(),R.anim._magic_theme_shake);
    }

    private LinearLayout row;
    private List<IconButtonView> items;
    private  int activeColor;
    private  int inactiveColor;
    private  int backgroundColor;
    private int selectedItem=0;
    private OnSelectChangeListener onSelectChangeListener;

    @Override
    public void bindView(View parent) {

        items=new ArrayList<IconButtonView>();
        inactiveColor= Color.GRAY;
        activeColor= Color.BLUE;
        backgroundColor=Color.WHITE;

    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public void setInactiveColor(int inactiveColor) {
        this.inactiveColor = inactiveColor;
    }

    public void addItem(final IconButtonView item){
        final int index=items.size();
        item.setIndex(index);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.startIconAnimation(selectAnimation);
                selectItem(index);
            }
        });

        items.add(item);


    }

    public void addItem(String text,int iconResource){
        IconButtonView iconButtonView=new IconButtonView(getContext());
        iconButtonView.setUnderText(text);
        iconButtonView.setIcon(iconResource);
        addItem(iconButtonView);
    }

    public interface OnSelectChangeListener {
        public void onSelectChange(int index);
    }

    public void setOnSelectChangeListener(OnSelectChangeListener onSelectChangeListener) {
        this.onSelectChangeListener = onSelectChangeListener;
    }

    public void selectItem(int index){
        try {

            cleanSelection();

            items.get(index).setUnderTextStyle(Typeface.BOLD);
            items.get(index).setIconTint(activeColor);
            items.get(index).setUnderTextColor(activeColor);
            selectedItem=index;
            if(onSelectChangeListener!=null){
                onSelectChangeListener.onSelectChange(index);
            }

        }catch (Exception e){
            //index out
        }

    }

    public int getSelectedItem() {
        return selectedItem;
    }

    private void cleanSelection(){
        for(IconButtonView item:items){

                item.setUnderTextColor(inactiveColor);
                item.setIconTint(inactiveColor);
                item.setUnderTextStyle(Typeface.NORMAL);
        }
    }

    @Override
    public View getView() {
        if(items.size()==0)
            return super.getView();
        cleanSelection();
        selectItem(0);


        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        param.weight=1;

        for(IconButtonView item:items){
            item.setWeight(1);
            item.getView().setLayoutParams(param);
            row.addView(item.getView());
        }
        row.setWeightSum(items.size());
        row.setBackgroundColor(backgroundColor);
        return super.getView();
    }

    @Override
    public void buildView(Object object) {

    }

    @Override
    public MagicViewHolder getInstance() {
        return null;
    }
}
