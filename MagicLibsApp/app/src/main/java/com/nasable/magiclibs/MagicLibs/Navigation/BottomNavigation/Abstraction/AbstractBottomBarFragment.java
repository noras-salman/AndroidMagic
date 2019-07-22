package com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Abstraction;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.nasable.magiclibs.MagicLibs.Navigation.BottomNavigation.Views.IconButtonRow;
import com.nasable.magiclibs.R;




/**
 * A simple {@link Fragment} subclass.
 */
public abstract class AbstractBottomBarFragment extends Fragment {

    private LinearLayout icon_holder;
    private IconButtonRow iconButtonRow;

    public AbstractBottomBarFragment() {
        // Required empty public constructor
    }

    private OnBottomNavigationSelectListener onBottomNavigationSelectListener;

    public void setOnBottomNavigationSelectListener(OnBottomNavigationSelectListener onBottomNavigationSelectListener) {
        this.onBottomNavigationSelectListener = onBottomNavigationSelectListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout._magic_theme_fragment_bottom_bar, container, false);

        icon_holder = root.findViewById(R.id.icon_holder);

        iconButtonRow = new IconButtonRow(getContext(), icon_holder);
        iconButtonRow.setActiveColor(ContextCompat.getColor(getContext(), R.color.Blue500));
        iconButtonRow.setInactiveColor(ContextCompat.getColor(getContext(), R.color.Grey600));


        addItems(iconButtonRow);

        iconButtonRow.setOnSelectChangeListener(new IconButtonRow.OnSelectChangeListener() {
            @Override
            public void onSelectChange(int index) {
                if (onBottomNavigationSelectListener != null)
                    onBottomNavigationSelectListener.onSelect(index);
            }
        });


        icon_holder.addView(iconButtonRow.getView());
        return root;
    }

    /**
     * abstraction for easier building
     * example iconButtonRow.addItem("TEST", R.drawable.my_icon);
     *
     * @param iconButtonRow
     */
    public abstract void  addItems(IconButtonRow iconButtonRow);

    public void addItem(String text,int iconResource){
        iconButtonRow.addItem(text,iconResource);
    }



    public interface OnBottomNavigationSelectListener {
        public void onSelect(int index);
    }

}
