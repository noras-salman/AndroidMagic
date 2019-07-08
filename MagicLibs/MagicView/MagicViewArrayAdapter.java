package se.mat.matse.MagicView;


/**
 *  Provides a way to automatically  generate and ArrayAdapter
 *  for a list of objects from a class that extend the
 *  MagicViewDataModel and creates a set of views using
 *  the MagicViewHolder abstract class model.
 *
 *
 * @see MagicViewHolder
 * @see MagicViewDataModel
 *
 * @author Noras Salman
 * @since 2018-12-04
 * @version 3.0
 **/


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;


public class MagicViewArrayAdapter extends ArrayAdapter<Object> {

    MagicViewHolder magicViewHolder;

    /**
     * The constructor
     *
     * @param context         The context
     * @param magicViewHolder The costume view you made
     */
    public MagicViewArrayAdapter(Context context, MagicViewHolder magicViewHolder) {
        super(context, 0);
        this.magicViewHolder = magicViewHolder;
    }

    /**
     * A fast way to refill the adapter with a new set of objects
     *
     * @param objectList a list of objects from a class that extends MagicViewDataModel
     * */
    public void refill(List<MagicViewDataModel> objectList) {
        clear();
        addAll(objectList);
        notifyDataSetChanged();

    }

    /**
     * Overriding the arrayAdapter method
     *
     * @param position
     * @param convertView
     * @param parent
     * @return view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MagicViewHolder viewHolder;
        if (convertView == null) {

            /* Get a new instance for each new item view that has NOT been created before */
            viewHolder = magicViewHolder.getInstance();
            convertView = viewHolder.getView();
            convertView.setTag(viewHolder);

        } else {
            /* Cast the tag to the costume view type you pass in the constructor */
            viewHolder = magicViewHolder.getClass().cast(convertView.getTag()) ;
        }

        /* A dataSetOnChangeListener to listen for actions from each item  */
        viewHolder.setDataSetChangeListener(new MagicViewHolder.DataSetChangeListener() {
            @Override
            public void notifyChange() {
                notifyDataSetChanged();
            }
        });

        /* Call build from each item with the object data */
        viewHolder.buildView(getItem(position));


        return convertView;
    }

}
