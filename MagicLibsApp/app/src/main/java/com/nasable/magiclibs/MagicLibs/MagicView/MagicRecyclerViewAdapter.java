package com.nasable.magiclibs.MagicLibs.MagicView;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MagicRecyclerViewAdapter extends RecyclerView.Adapter<MagicRecyclerViewViewHolder> {

    private MagicRecyclerViewViewHolder magicRecyclerViewViewHolder;
    private List<? extends Object> items;

    public MagicRecyclerViewAdapter(MagicRecyclerViewViewHolder magicRecyclerViewViewHolder, List<? extends Object> items) {
        this.magicRecyclerViewViewHolder = magicRecyclerViewViewHolder;
        this.items=items;
    }

    public void setItems(List<? extends Object> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public MagicRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /* Return a new instance */
        return magicRecyclerViewViewHolder.createNewInstance();
    }


    @Override
    public void onBindViewHolder(@NonNull MagicRecyclerViewViewHolder magicRecyclerViewViewHolder, int position) {
        /* Call build */
        magicRecyclerViewViewHolder.build(items.get(position));
        magicRecyclerViewViewHolder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
