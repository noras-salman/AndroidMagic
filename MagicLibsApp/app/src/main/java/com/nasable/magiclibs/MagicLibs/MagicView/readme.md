## Usage
  
    - Create fast customized views from a model
    - Auto generate array adapters/recycler view adapter from the view/model 
  
### How to
    - Create a itemx_layout.xml in res/layout
    - Create a view class extending MagicViewHolder
   
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
        String s=((String)object).tvValue;
        textView.setText(s);
    }
    
    @Override
    public MagicViewHolder getInstance() {
        return new TViewParent(getContext());
   }
}

```


Your objects

```
 List<String> objects=new ArrayList<String>();
 objects.add("test");
 objects.add("test2");
```

# Create an array adapter for a listView or gridView
```
 ListView listView=findViewById(R.id.listView);
 MagicViewArrayAdapter adapter=MagicViewFactory.withContext(getApplicationContext())
                                               .forViewHolder(TViewParent.class)
                                               .getMagicViewArrayAdapter();

 listView.setAdapter(adapter);
 adapter.refill(objects);
```

# Create a recycleViewAdapter for a recycleView 
```
MagicRecyclerViewAdapter adapter= 
                MagicViewFactory.withContext(getApplicationContext())
                .forViewHolder(TViewParent.class)
                .getMagicRecyclerViewAdapter();
```
# Use to attach to a recycleView

## Vertical scroll
```
RecyclerView recycleView=findViewById(R.id.recycleView);
        MagicViewFactory.withContext(getApplicationContext())
                .forViewHolder(TViewParent.class)
                .prepareRecyclerView(recycleView)
                .setup()
                .load(objects);
```

## Horizontal scroll
```
RecyclerView recycleView=findViewById(R.id.recycleView);
        MagicViewFactory.withContext(getApplicationContext())
                .forViewHolder(CountryView.class)
                .prepareRecyclerView(recycleView)
                .withHorizontalOrientation()
                .setup()
                .load(objects);
```

## Gird
```
RecyclerView recycleView=findViewById(R.id.recycleView);
        int columns=3;
        MagicViewFactory.withContext(getApplicationContext())
                .forViewHolder(CountryView.class)
                .prepareRecyclerView(recycleView)
                .setupGridRecyclerView(columns)
                .load(objects);
```

