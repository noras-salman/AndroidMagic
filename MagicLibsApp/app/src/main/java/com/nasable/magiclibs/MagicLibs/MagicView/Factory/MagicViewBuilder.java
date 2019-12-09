package com.nasable.magiclibs.MagicLibs.MagicView.Factory;


import androidx.recyclerview.widget.RecyclerView;

import com.nasable.magiclibs.MagicLibs.MagicView.MagicRecyclerViewAdapter;
import com.nasable.magiclibs.MagicLibs.MagicView.MagicRecyclerViewViewHolder;
import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewArrayAdapter;
import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewFactory;

import java.util.ArrayList;

public class MagicViewBuilder {

    public MagicViewFactory magicViewFactory;
    public MagicRecyclerViewAdapter magicRecyclerViewAdapter;
    private MagicRecyclerViewViewHolder magicRecyclerViewViewHolder;

    public MagicViewBuilder(MagicViewFactory magicViewFactory) {
        this.magicViewFactory = magicViewFactory;
        magicRecyclerViewViewHolder = new MagicRecyclerViewViewHolder(magicViewFactory.magicViewHolder);
    }

    public MagicViewArrayAdapter getMagicViewArrayAdapter() {
        return new MagicViewArrayAdapter(magicViewFactory.context, magicViewFactory.magicViewHolder);
    }

    public RecyclerViewPreparer prepareRecyclerView(RecyclerView recyclerView) {
        return new RecyclerViewPreparer(recyclerView, this);
    }

    public MagicRecyclerViewAdapter getMagicRecyclerViewAdapter() {
        if (magicRecyclerViewViewHolder != null)
            magicRecyclerViewAdapter = new MagicRecyclerViewAdapter(magicRecyclerViewViewHolder, new ArrayList<>());
        return magicRecyclerViewAdapter;
    }





}