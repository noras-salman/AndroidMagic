package com.nasable.magiclibs.MagicLibs.MagicView;

import androidx.recyclerview.widget.RecyclerView;

public class MagicRecyclerViewViewHolder extends RecyclerView.ViewHolder {

    private MagicViewHolder magicViewHolder;


    public MagicRecyclerViewViewHolder(MagicViewHolder magicViewHolder) {
        super(magicViewHolder.getView());
        this.magicViewHolder=magicViewHolder;
    }

    public void build(Object object){
        magicViewHolder.buildView(object);
    }

    /*Not static: uses the same magicViewHolder*/
    public MagicRecyclerViewViewHolder createNewInstance(){

        /*Should not be attached when called, so we create a new instance*/
        magicViewHolder=magicViewHolder.getInstance();

        return new MagicRecyclerViewViewHolder(magicViewHolder);
    }
}
