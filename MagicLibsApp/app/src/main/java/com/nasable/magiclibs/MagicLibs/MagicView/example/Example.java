package com.nasable.magiclibs.MagicLibs.MagicView.example;

import android.content.Context;
import android.view.View;

import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewHolder;
import com.nasable.magiclibs.R;

public class Example extends MagicViewHolder {

    public Example(Context context) {
        super(context, R.layout.magic_view_holder_example);
    }

    @Override
    public void bindView(View parent) {

    }

    @Override
    public void buildView(Object object) {

    }

    @Override
    public MagicViewHolder getInstance() {
        return new Example(getContext());
    }
}
