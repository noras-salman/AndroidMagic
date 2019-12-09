package com.nasable.magiclibs.MagicLibs.MagicView.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.nasable.magiclibs.MagicLibs.MagicView.Factory.RecyclerAdapterLoader;
import com.nasable.magiclibs.MagicLibs.MagicView.MagicRecyclerViewPaginationListener;
import com.nasable.magiclibs.MagicLibs.MagicView.MagicViewFactory;
import com.nasable.magiclibs.MagicLibs.MagicView.OnItemClickListener;
import com.nasable.magiclibs.R;

import java.util.ArrayList;

public class MagicViewExampleActivity extends AppCompatActivity {
    private static final String TAG = "MagicViewExampleActivit";
    RecyclerAdapterLoader recyclerAdapterLoader=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_view_example);
        RecyclerView recyclerView=findViewById(R.id.recycleView);

        setTitle("Example");
        final ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");

         recyclerAdapterLoader= MagicViewFactory.withContext(getApplicationContext())
                .forViewHolder(Example.class)
                .prepareRecyclerView(recyclerView)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                    }
                })
                .setPaginationListener(new MagicRecyclerViewPaginationListener() {

                    @Override
                    public void onLoadMore() {
                        Log.d(TAG, "onLoadMore: ");
                        recyclerAdapterLoader.add(arrayList);
                    }

                    @Override
                    public boolean isLastPage() {
                        return false;
                    }

                    @Override
                    public boolean isLoading() {
                        return false;
                    }
                })
                .setup();

        recyclerAdapterLoader.load(arrayList);

    }
}
