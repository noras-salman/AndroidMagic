package com.nasable.magiclibs.MagicLibs.MagicView;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

/**
 * Created 2018-12-04
 * @author Noras Salman
 * @since 0.1
 * @version 3.0
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


    /**
     * @param context        The context.. The activity..
     * @param layoutResource The xml view resource
     **/
    public MagicViewHolder(Context context, int layoutResource) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.view = inflater.inflate(layoutResource, null);

        this.context = context;

        /* Bind is called when the view is inflated */
        bindView(this.view);
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


    /** Implement to return the following
     * @return new TheView(getContext()); or new TheView(getContext(),resource);
     * **/
    public abstract MagicViewHolder getInstance();


    /**
     * @return a new instance of an array adapter for this view;
     * **/
    public MagicViewArrayAdapter getAdapterInstance(){
       return new MagicViewArrayAdapter(getContext(),this);
    }

    public MagicRecyclerViewViewHolder getMagicRecycleViewHolder(){
        return new MagicRecyclerViewViewHolder(getInstance().getView(),this);
    }

    public MagicRecyclerViewAdapter getRecycleViewAdapter(List<Object> items){
        return new MagicRecyclerViewAdapter(this,items);
    }

    /**
     *
     * @param context
     * @param recyclerView
     * @param orientation       example LinearLayoutManager.HORIZONTAL
     */
    public static void setUpRecycleView(Context context,RecyclerView recyclerView,int orientation){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(orientation);
        recyclerView.setLayoutManager(linearLayoutManager );
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public static void setUpGridRecycleView(Context context,RecyclerView recyclerView,int numberOfColumns){
        GridLayoutManager gridLayoutManager=new GridLayoutManager(context, numberOfColumns);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}
