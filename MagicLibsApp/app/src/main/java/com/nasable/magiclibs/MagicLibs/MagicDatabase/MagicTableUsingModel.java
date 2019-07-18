package com.nasable.magiclibs.MagicLibs.MagicDatabase;

import android.database.Cursor;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class MagicTableUsingModel extends MagicTable{

   private Class model;

    /**
     * @param magicDatabase
     */
    public MagicTableUsingModel(MagicDatabase magicDatabase,Class model) {
        super(magicDatabase);
        this.model=model;
    }

    @Override
    public String getTableName() {
        return model.getCanonicalName();
    }

    @Override
    public List<MagicColumn> getColumns() {
        List<MagicColumn> magicColumns=new ArrayList<>();
        /*
        getDeclaredFields() gives you all fields on that Class, including private ones.
        getFields() gives you all public fields on that Class AND it's superclasses
        */
        for(Field f:model.getFields()){
           if(String.class.isAssignableFrom(f.getType())){

               magicColumns.add(MagicColumn.getTextInstance( f.getName(),null));
           }else if(Long.class.isAssignableFrom(f.getType())){

               magicColumns.add(MagicColumn.getLongInstance( f.getName(),null));
           }else if(Double.class.isAssignableFrom(f.getType())){

               magicColumns.add(MagicColumn.getDoubleInstance( f.getName(),null));
           }else if(Integer.class.isAssignableFrom(f.getType())){

               magicColumns.add(MagicColumn.getIntegerInstance(f.getName(),null));
           }
        }
        return magicColumns;
    }


}