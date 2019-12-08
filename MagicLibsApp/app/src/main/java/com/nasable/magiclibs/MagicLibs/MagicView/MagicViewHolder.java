package com.nasable.magiclibs.MagicLibs.MagicView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created 2018-12-04
 *
 * @author Noras Salman
 * @version 3.0
 * @since 0.1
 **/


public abstract class MagicViewHolder {


    /**
     * Context is used to get the inflater service and can be handy to get other resources in the bind
     **/
    private Context context;


    /**
     * The parent view that is inflated using the xml view
     **/
    private View view;
    private View.OnClickListener onClickListener;




    /**
     * Event listener to update the view when object changes are made
     **/
    private DataSetChangeListener dataSetChangeListener;


    /**
     * Interface for the event listener
     **/
    public interface DataSetChangeListener {
        public void notifyChange();
    }

    public MagicViewHolder(Context context) {
        this(context, 0);
    }

    /**
     * @param context        The context
     * @param layoutResource The xml view resource
     **/
    public MagicViewHolder(Context context, int layoutResource) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.view = inflater.inflate(layoutResource, null);

        this.context = context;

        /* Bind is called when the view is inflated */
        bindView(this.view);
    }

    public void setOnClickListener(View.OnClickListener onClickListener){

            this.onClickListener=onClickListener;
            this.view.setOnClickListener(  this.onClickListener);
    }


    public View.OnClickListener getOnClickListener(){
        return this.onClickListener;
    }
    /**
     * Binding the inflated view to the convert view (called in the constructor)
     *
     * @param parent The inflated holder view.
     **/
    public abstract void bindView(View parent);


    /**
     * Building the view using an object
     *
     * @param object The object that has the information which will be shown.
     **/
    public abstract void buildView(Object object);


    /**
     * Listener for notifying host view.. requires implementation of the listener when called
     *
     * @param dataSetChangeListener the listener that will publish the event
     **/
    public void setDataSetChangeListener(DataSetChangeListener dataSetChangeListener) {
        this.dataSetChangeListener = dataSetChangeListener;
    }


    /**
     * Used in case an array adapter
     **/
    public void notifyDataSetChanged() {
        if (dataSetChangeListener != null)
            dataSetChangeListener.notifyChange();
    }


    /**
     * @return the parent holderView
     **/
    public View getView() {
        return view;
    }


    /**
     * @return the context
     **/
    public Context getContext() {
        return context;
    }


    /**
     * Implement to return the following
     *
     * @return new TheView(getContext()); or new TheView(getContext(),resource);
     **/
    public abstract MagicViewHolder getInstance();


}