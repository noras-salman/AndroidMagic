package com.nasable.magiclibs.MagicLibs.MagicView.Factory;

import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewFactory;

import java.util.List;

public class RecyclerAdapterLoader {
    private RecyclerViewPreparer recyclerViewPreparer;

    public RecyclerAdapterLoader(RecyclerViewPreparer recyclerViewPreparer) {
        this.recyclerViewPreparer = recyclerViewPreparer;
    }


    public void load(List<? extends Object> items) {
        if (recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter != null)
            recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter.setItems(items);
    }

    public void reload(List<? extends Object> items){
        if (recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter != null)
            recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter.reload(items);
    }

    public void add(List<? extends Object> items){
        if (recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter != null)
            recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter.add(items);
    }
}