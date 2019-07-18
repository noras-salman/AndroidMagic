package com.nasable.magiclibs.MagicLibs.MagicView;

import androidx.recyclerview.widget.RecyclerView;
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
