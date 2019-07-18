package com.nasable.magiclibs.MagicLibs.MagicView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class MagicRecyclerViewAdapter extends RecyclerView.Adapter<MagicRecyclerViewViewHolder> {

    MagicViewHolder magicViewHolder;
    List<Object> items;

    public MagicRecyclerViewAdapter(MagicViewHolder magicViewHolder, List<Object> items) {
        this.magicViewHolder = magicViewHolder;
        this.items = items;
    }

    @Override
    public MagicRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return magicViewHolder.getMagicRecycleViewHolder();
    }


    @Override
    public void onBindViewHolder(@NonNull MagicRecyclerViewViewHolder magicRecyclerViewViewHolder, int position) {
        // build
        magicRecyclerViewViewHolder.build(items.get(position));
        magicRecyclerViewViewHolder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
