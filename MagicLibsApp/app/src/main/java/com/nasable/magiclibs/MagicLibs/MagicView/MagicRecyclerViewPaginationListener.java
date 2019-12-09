package com.nasable.magiclibs.MagicLibs.MagicView;


import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MagicRecyclerViewPaginationListener extends RecyclerView.OnScrollListener {

   private RecyclerView.LayoutManager mLayoutManager;
   private boolean isUseLinearLayoutManager;
   private boolean isUseGridLayoutManager;

    private int PAGE_SIZE=5;


    public void setPageSize(int PAGE_SIZE) {
        this.PAGE_SIZE = PAGE_SIZE;
    }

    public void setLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.mLayoutManager = linearLayoutManager;
        isUseLinearLayoutManager = true;

    }

    public void setLayoutManager(GridLayoutManager gridLayoutManager) {
        this.mLayoutManager = gridLayoutManager;
        isUseGridLayoutManager = true;

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int firstVisibleItemPosition=0, visibleItemCount, totalItemCount;

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();

        if(isUseLinearLayoutManager && mLayoutManager instanceof LinearLayoutManager){
            firstVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if(isUseGridLayoutManager && mLayoutManager instanceof GridLayoutManager){
            firstVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }

        if (!isLoading() && !isLastPage()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0
                    && totalItemCount >= PAGE_SIZE) {
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        onLoadMore();
                    }
                });
            }
        }
    }

    public abstract void onLoadMore();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

}
