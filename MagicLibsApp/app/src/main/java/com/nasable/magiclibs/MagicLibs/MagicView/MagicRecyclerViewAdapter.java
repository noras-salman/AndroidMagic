package se.mat.matse.ui._holders.MagicView;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MagicRecyclerViewAdapter extends RecyclerView.Adapter<MagicRecyclerViewViewHolder> {

    private MagicRecyclerViewViewHolder magicRecyclerViewViewHolder;
    private List<? extends Object> items;
    private OnItemClickListener onItemClickListener;
    public MagicRecyclerViewAdapter(MagicRecyclerViewViewHolder magicRecyclerViewViewHolder, List<? extends Object> items) {
        this.magicRecyclerViewViewHolder = magicRecyclerViewViewHolder;
        this.items = items;
    }

    public void setItems(List<? extends Object> items) {
        this.items = items;
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
