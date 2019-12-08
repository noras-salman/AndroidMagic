package com.nasable.magiclibs.MagicLibs.MagicView;


import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class MagicRecyclerViewViewHolder extends RecyclerView.ViewHolder {

    private MagicViewHolder magicViewHolder;



    public MagicRecyclerViewViewHolder(MagicViewHolder magicViewHolder) {
        super(magicViewHolder.getView());
        this.magicViewHolder = magicViewHolder;
    }

    public void build(Object object, final int position, final OnItemClickListener onItemClickListener) {
        magicViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("build", "onClick: ");
                if(onItemClickListener!=null)
                    onItemClickListener.onItemClick(position);
            }
        });
        magicViewHolder.buildView(object);
    }



    /*Not static: uses the same magicViewHolder*/
    public MagicRecyclerViewViewHolder createNewInstance() {

        /*Should not be attached when called, so we create a new instance*/
        magicViewHolder = magicViewHolder.getInstance();

        return new MagicRecyclerViewViewHolder(magicViewHolder);
    }
}
