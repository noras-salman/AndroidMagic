package com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation;

import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Abstraction.AbstractBottomBarFragment;
import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Views.IconButtonRow;
import com.nasable.magiclibs.R;

public class BottomBarFragment extends AbstractBottomBarFragment {


    @Override
    public void addItems(IconButtonRow iconButtonRow) {
      //  iconButtonRow.setIconOnly(true);

        iconButtonRow.addItem("TEST", R.drawable.ic_favourite_off);
        iconButtonRow.addItem("TEST",R.drawable.ic_favourite_off);
        iconButtonRow.addItem("TEST",R.drawable.ic_favourite_off);
        iconButtonRow.addItem("TEST",R.drawable.ic_favourite_off);
        iconButtonRow.addItem("TEST",R.drawable.ic_favourite_off);
    }

    @Override
    public int getBackgroundColor() {
        return R.color.colorPrimary;
    }

    @Override
    public int getActiveColor() {
        return R.color.Blue500;
    }

    @Override
    public int getInactiveColor() {
        return  R.color.Grey600;
    }
}