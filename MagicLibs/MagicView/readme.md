## Usage
  
    - Create fast customized views from a model
    - Auto generate array adapters/recycler view adapter from the view/model 
  
### How to
    - Create a itemx_layout.xml in res/layout
    - Create a view class extending MagicViewHolder
    - Create a model class extending MagicViewDataModel 
   
### Example

Layout
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="wrap_content">
<TextView
    android:id="@+id/tv"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="16dp"/>
</LinearLayout>
```

View Class
```
public class TViewParent extends MagicViewHolder {

    public TViewParent(Context context) {
        super(context, R.layout.item_a);
    }

    @Override
    public MagicViewHolder getInstance() {
        return new TViewParent(getContext());
    }

    TextView textView;

    @Override
    public void bindView(View convertView) {
         textView=convertView.findViewById(R.id.tv);
    }

    @Override
    public void buildView(Object object) {
        String s=((TvMagicViewDataModel)object).tvValue;
        textView.setText(s);
    }
    
    @Override
    public MagicViewHolder getInstance() {
        return new TViewParent(getContext());
   }
}

```

Model Class
```
public class TvMagicViewDataModel extends MagicViewDataModel {

    String tvValue;

    public TvMagicViewDataModel(String tvValue) {
        this.tvValue = tvValue;
    }
}
```

Usage in an activity,fragment ..etc
```
 ListView listView=findViewById(R.id.listView);
 TViewParent tViewParent=new TViewParent(getApplicationContext());
 MagicViewArrayAdapter adapter=tViewParent.getAdapterInstance();

 listView.setAdapter(adapter);
 List<MagicViewDataModel> objectList=new ArrayList<MagicViewDataModel>();
 objectList.add(new TvMagicViewDataModel("test1"));
 objectList.add(new TvMagicViewDataModel("test3"));
 adapter.refill(objectList);
```

```
ProductCard productCard=new ProductCard(getContext());


 RecyclerView recyclerView=root.findViewById(R.id.recyclerView);
 recyclerView.setHasFixedSize(true)
 recyclerView.setAdapter(productCard.getRecycleViewAdapter(objectList));
 
 //horizontal scroll view 
 MagicViewHolder.setUpRecycleView(getContext(),recyclerView,LinearLayoutManager.HORIZONTAL);
 
 //or vertical scroll view 
 MagicViewHolder.setUpRecycleView(getContext(),recyclerView,LinearLayoutManager.VERTICAL);
 
 //or grid with 3 clomuns
 MagicViewHolder.setUpGridRecycleView(getContext(),recyclerView,3);
```