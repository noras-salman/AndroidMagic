package com.nasable.magiclibs.MagicLibs.MagicDatabase;

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
    public static final String COLUMN_NAME_TIMESTAMP="timestamp";
    public static final String COLUMN_NAME_ID="id";


    private String name;
    private String type;
    private String defaultValue;

    /**
     *
     * @param name
     * @param type
     */
    public MagicColumn(String name, String type) {
        this.name = name;
        this.type = type;
    }

    /**
     *
     * @param name
     * @param type
     * @param defaultValue
     */
    public MagicColumn(String name, String type, String defaultValue) {
        this.name = name;
        this.type = type;
        this.defaultValue = defaultValue;
    }

    /**
     *
     * @return
     */
    public String getCreateSQL(){
        String sql= this.name+SPACE+this.type;
        if(defaultValue!=null)
            sql+=SPACE+KEYWORD_DEFAULT+SPACE+defaultValue;
        return sql;
    }


    /**
     *
     * @return
     */
    public static MagicColumn getIdInstance(){
        return new MagicColumn(COLUMN_NAME_ID,TYPE_INTEGER+SPACE+PRIMARY_KEY);
    }

    /**
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static MagicColumn getIntegerInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_INTEGER,defaultValue);
    }

    /**
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static MagicColumn getTextInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_TEXT,defaultValue);
    }

    /**
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static MagicColumn getLongInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_LONG,defaultValue);
    }

    /**
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static MagicColumn getDoubleInstance(String name,String defaultValue){
        return new MagicColumn(name,TYPE_DOUBLE,defaultValue);
    }

    /**
     *
     * @param name
     * @return
     */
    public static MagicColumn getTimestampInstance(String name){
        return new MagicColumn(name,TYPE_TIMESTAMP,DEFAULT_CURRENT_TIMESTAMP);
    }

    /**
     *
     * @return
     */
    public static MagicColumn getTimestampInstance(){
        return new MagicColumn(COLUMN_NAME_TIMESTAMP,TYPE_TIMESTAMP,DEFAULT_CURRENT_TIMESTAMP);
    }

}