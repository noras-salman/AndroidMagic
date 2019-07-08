package com.nasable.notamia.MagicDatabase;

public class MagicColumn{
    private static final String SPACE=" ";
    private static final String KEYWORD_DEFAULT="DEFAULT";
    private static final String PRIMARY_KEY="PRIMARY KEY AUTOINCREMENT";

    public static final String DEFAULT_CURRENT_TIMESTAMP="CURRENT_TIMESTAMP";
    public static final String TYPE_INTEGER="INTEGER";
    public static final String TYPE_TIMESTAMP="TIMESTAMP";
    public static final String TYPE_LONG="LONG";
    public static final String TYPE_DOUBLE="DOUBLE";
    public static final String TYPE_TEXT="TEXT";


    private String name;
    private String type;
    private String defaultValue;

    public MagicColumn(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public MagicColumn(String name, String type, String defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    public String getCreateSQL(){
        String sql= this.name+SPACE+this.type;
        if(defaultValue!=null)
            sql+=SPACE+KEYWORD_DEFAULT+SPACE+defaultValue;
        return sql;
    }



    public static MagicColumn getIdInstance(){
        return new MagicColumn("id",TYPE_INTEGER+SPACE+PRIMARY_KEY);
    }

    public static MagicColumn getIntegerInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_INTEGER,defaultValue);
    }

    public static MagicColumn getTextInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_TEXT,defaultValue);
    }

    public static MagicColumn getLongInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_LONG,defaultValue);
    }

    public static MagicColumn getDoubleInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_DOUBLE,defaultValue);
    }

    public static MagicColumn getTimestampInstance(String name){
        return new MagicColumn(name,TYPE_TIMESTAMP,DEFAULT_CURRENT_TIMESTAMP);
    }

    public static MagicColumn getTimestampInstance(){
        return new MagicColumn("timestamp",TYPE_TIMESTAMP,DEFAULT_CURRENT_TIMESTAMP);
    }

}