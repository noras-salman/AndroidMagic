package com.nasable.magiclibs.MagicLibs.MagicView.Factory;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nasable.magiclibs.MagicLibs.MagicView.MagicRecyclerViewPaginationListener;
import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewFactory;
import com.nasable.magiclibs.MagicLibs.MagicView.OnItemClickListener;

public  class RecyclerViewPreparer {
    public MagicViewBuilder magicViewBuilder;

    private int orientation = RecyclerView.VERTICAL;
    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;
    private MagicRecyclerViewPaginationListener paginationListener;

    public RecyclerViewPreparer(RecyclerView recyclerView, MagicViewBuilder magicViewBuilder) {
        this.magicViewBuilder = magicViewBuilder;
        this.recyclerView = recyclerView;


    }

    public RecyclerViewPreparer setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
        return  this;
    }

    public RecyclerViewPreparer setPaginationListener(MagicRecyclerViewPaginationListener magicRecyclerViewPaginationListener){
        this.paginationListener=magicRecyclerViewPaginationListener;
        recyclerView.addOnScrollListener(paginationListener);
        return  this;
    }

    public RecyclerAdapterLoader setup() {
        magicViewBuilder.getMagicRecyclerViewAdapter();
        if (magicViewBuilder.magicRecyclerViewAdapter != null) {
            magicViewBuilder.magicRecyclerViewAdapter.setOnItemClickListener(this.onItemClickListener);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(magicViewBuilder.magicRecyclerViewAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(magicViewBuilder.magicViewFactory.context);
            linearLayoutManager.setOrientation(orientation);
            if(paginationListener!=null){
                paginationListener.setLayoutManager(linearLayoutManager);
            }
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
        return new RecyclerAdapterLoader(this);
    }

    public RecyclerViewPreparer withHorizontalOrientation() {
        orientation = RecyclerView.HORIZONTAL;
        return this;
    }




    public RecyclerAdapterLoader setupGridRecyclerView(int numberOfColumns) {
        magicViewBuilder.getMagicRecyclerViewAdapter();
        if (magicViewBuilder.magicRecyclerViewAdapter != null) {
            magicViewBuilder.magicRecyclerViewAdapter.setOnItemClickListener(this.onItemClickListener);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(magicViewBuilder.magicRecyclerViewAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(magicViewBuilder.magicViewFactory.context, numberOfColumns);
            if(paginationListener!=null){
                paginationListener.setLayoutManager(gridLayoutManager);
            }
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

        }
        return new RecyclerAdapterLoader(this);
    }




}