package com.nasable.magiclibs.MagicLibs.MagicView;

import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class MagicViewFactory {
    private static final String TAG = "MagicViewFactory";

    private Context context;
    private MagicViewHolder magicViewHolder;


    private MagicViewFactory(Context context){
        this.context=context;
    }

    public static MagicViewFactory withContext(Context context) {
        return new MagicViewFactory(context);
    }

    public MagicViewBuilder forViewHolder(Class<? extends MagicViewHolder> magicViewHolderClass) {
        try {
            Constructor<?> constructor = magicViewHolderClass.getConstructor(Context.class);
            Object object = constructor.newInstance(context);
            magicViewHolder = (MagicViewHolder) object;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new MagicViewBuilder(this);
    }


    public static class MagicViewBuilder {

        private MagicViewFactory magicViewFactory;
        private MagicRecyclerViewAdapter magicRecyclerViewAdapter;
        private MagicRecyclerViewViewHolder magicRecyclerViewViewHolder;

        private MagicViewBuilder(MagicViewFactory magicViewFactory) {
            this.magicViewFactory=magicViewFactory;
            magicRecyclerViewViewHolder = new MagicRecyclerViewViewHolder(magicViewFactory.magicViewHolder);
        }

        public MagicViewArrayAdapter getMagicViewArrayAdapter(){
            return new MagicViewArrayAdapter(magicViewFactory.context,magicViewFactory.magicViewHolder);
        }

        public RecyclerViewPreparer prepareRecyclerView(RecyclerView recyclerView) {
            return new RecyclerViewPreparer(recyclerView, this);
        }

        public MagicRecyclerViewAdapter getMagicRecyclerViewAdapter() {
            if (magicRecyclerViewViewHolder != null)
                magicRecyclerViewAdapter = new MagicRecyclerViewAdapter(magicRecyclerViewViewHolder, new ArrayList<>());
            return magicRecyclerViewAdapter;
        }


        public static class RecyclerViewPreparer {
            private MagicViewBuilder magicViewBuilder;

            private int orientation = RecyclerView.VERTICAL;
            private RecyclerView recyclerView;

            public RecyclerViewPreparer(RecyclerView recyclerView, MagicViewBuilder magicViewBuilder) {
                this.magicViewBuilder = magicViewBuilder;
                this.recyclerView = recyclerView;


            }

            public RecyclerAdapterLoader setup() {
                magicViewBuilder.getMagicRecyclerViewAdapter();
                if (magicViewBuilder.magicRecyclerViewAdapter != null) {
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(magicViewBuilder.magicRecyclerViewAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(magicViewBuilder.magicViewFactory.context);
                    linearLayoutManager.setOrientation(orientation);
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
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(magicViewBuilder.magicRecyclerViewAdapter);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(magicViewBuilder.magicViewFactory.context, numberOfColumns);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
                return new RecyclerAdapterLoader(this);
            }

            public static class RecyclerAdapterLoader {
                private RecyclerViewPreparer recyclerViewPreparer;

                public RecyclerAdapterLoader(RecyclerViewPreparer recyclerViewPreparer) {
                    this.recyclerViewPreparer = recyclerViewPreparer;
                }

                public void load(List<? extends Object> items) {
                    if (recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter != null)
                        recyclerViewPreparer.magicViewBuilder.magicRecyclerViewAdapter.setItems(items);
                }
            }


        }


    }


}
