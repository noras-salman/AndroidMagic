package com.nasable.magiclibs.MagicLibs.MagicView;

import android.content.Context;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nasable.magiclibs.MagicLibs.MagicView.Factory.MagicViewBuilder;
import com.nasable.magiclibs.MagicLibs.MagicView.Factory.RecyclerAdapterLoader;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class MagicViewFactory<T> {
    private static final String TAG = "MagicViewFactory";

    public Context context;
    public MagicViewHolder magicViewHolder;


    private MagicViewFactory(Context context) {
        this.context = context;
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




}
