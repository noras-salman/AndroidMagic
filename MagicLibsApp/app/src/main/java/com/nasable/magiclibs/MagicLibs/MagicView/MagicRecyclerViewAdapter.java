package com.nasable.magiclibs.MagicLibs.MagicView;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MagicRecyclerViewAdapter<T> extends RecyclerView.Adapter<MagicRecyclerViewViewHolder> {

    private MagicRecyclerViewViewHolder magicRecyclerViewViewHolder;
    private List<T> items;
    private OnItemClickListener onItemClickListener;
    public MagicRecyclerViewAdapter(MagicRecyclerViewViewHolder magicRecyclerViewViewHolder, List<T> items) {
        this.magicRecyclerViewViewHolder = magicRecyclerViewViewHolder;
        this.items = items;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void reload(List<T> items){
        this.items.clear();
        this.items=items;
        notifyDataSetChanged();

    }

    public void add(List<T> items){
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MagicRecyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /* Return a new instance */
        return magicRecyclerViewViewHolder.createNewInstance();
    }


    @Override
    public void onBindViewHolder(@NonNull MagicRecyclerViewViewHolder magicRecyclerViewViewHolder, final int position) {
        /* Call build */
        magicRecyclerViewViewHolder.build(items.get(position),position,this.onItemClickListener);

        magicRecyclerViewViewHolder.itemView.setTag(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
