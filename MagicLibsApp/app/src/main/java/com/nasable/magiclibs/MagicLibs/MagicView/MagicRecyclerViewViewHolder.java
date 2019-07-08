package com.nasable.magiclibs.MagicLibs.MagicView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MagicRecyclerViewViewHolder extends RecyclerView.ViewHolder {

    MagicViewHolder magicViewHolder;
    public MagicRecyclerViewViewHolder( View itemView, MagicViewHolder magicViewHolder) {
        super(itemView);
        this.magicViewHolder=magicViewHolder;
    }

    public void build(Object object){
        magicViewHolder.buildView(object);
    }
}
