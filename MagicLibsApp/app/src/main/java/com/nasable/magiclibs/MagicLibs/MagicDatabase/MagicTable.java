package com.nasable.magiclibs.MagicLibs.MagicDatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicTable {


    private MagicDatabase magicDatabase;

    /**
     * @param magicDatabase
     */
    public MagicTable(MagicDatabase magicDatabase) {
        this.magicDatabase = magicDatabase;
    }

    public abstract String getTableName();

    public String getSqlDeleteEntries() {
        return "DROP TABLE IF EXISTS " + getTableName() + ";";
    }

    public abstract List<MagicColumn> getColumns();

    public String getTableCreateSQL() {
        String sql = "CREATE TABLE IF NOT EXISTS " + getTableName() + " ( ";
        List<MagicColumn> columns = getColumns();
        for (int i = 0; i < columns.size(); i++) {
            sql += columns.get(i).getCreateSQL();
            if (i != columns.size() - 1)
                sql += ",";

        }
        sql += ");";
        return sql;
    }

    /**
     * @param contentValues
     * @return
     */
    public long insert(ContentValues contentValues) {
        SQLiteDatabase db = magicDatabase.getWritableDatabase();
        long id = db.insert(getTableName(), null, contentValues);
        db.close();
        return id;
    }

    /**
     * @param id
     */
    public void deleteById(int id) {
        SQLiteDatabase db = magicDatabase.getWritableDatabase();
        db.execSQL("DELETE from " + getTableName() + " where id=" + id);
        db.close();
    }

    /**
     *
     */
    public void cleanTable() {
        SQLiteDatabase db = magicDatabase.getWritableDatabase();
        db.execSQL("DELETE from " + getTableName());
        db.close();
    }

    /**
     * @param id
     * @param contentValues
     */
    public void updateById(int id, ContentValues contentValues) {
        SQLiteDatabase db = magicDatabase.getWritableDatabase();
        db.update(getTableName(), contentValues, "id=" + id, null);
        db.close();
    }


    public interface CursorFetchListener{
        public void onCursorResult(Cursor cursor);
        public void onFinish();
    }


    public void rawQuery(String sqlQuery,CursorFetchListener cursorFetchListener){
        SQLiteDatabase db = magicDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        while (cursor.moveToNext()) {
            if (cursorFetchListener != null)
                cursorFetchListener.onCursorResult(cursor);
        }

        cursor.close();
        db.close();
        if (cursorFetchListener != null)
            cursorFetchListener.onFinish();
    }

    public interface RowFetchListener {
        public void onCursorResult(Object object);

        public void onFinish();
    }



    /**
     * should return new object of your (YourModel)
     * @param cursor
     * @return new YourModel( .. using your columns ..)
     */
    public abstract Object populateObject(Cursor cursor);

    /**
     * just return  ((List<YourModel>)(List<?>) objectList)
     * @param objectList
     * @return  ((List<YourModel>)(List<?>) objectList)
     */
    public abstract List<? extends Object> populateObject(List<Object> objectList);

    /**
     * cursor.getInt(cursor.getColumnIndex(COL_DURATION)),
     * TODO: look this design pattern for query https://stackoverflow.com/questions/34253161/how-should-i-populate-my-object
     **/
    public List<Object> queryForObjects(String sqlQuery, RowFetchListener rowFetchListener) {


        SQLiteDatabase db = magicDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        List<Object> objects = new ArrayList<>();
        while (cursor.moveToNext()) {
            if (rowFetchListener != null)
                rowFetchListener.onCursorResult(populateObject(cursor));
            objects.add(populateObject(cursor));
        }

        cursor.close();
        db.close();
        if (rowFetchListener != null)
            rowFetchListener.onFinish();

        return objects;
    }

    public List<Object> queryForObjects(String sqlQuery) {
        return queryForObjects(sqlQuery,null);
    }

    public Object queryForObject(String sqlQuery){
        List<Object> objectList=queryForObjects(sqlQuery,null);
        if(objectList.size()==0)
            return null;
        return objectList.get(0);
    }

    public Object getById(int id){
        return queryForObject("SELECT * FROM "+getTableName()+" WHERE id="+id);
    }

    public int rowCount(){
        int count=0;
        SQLiteDatabase db = magicDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) AS row_count FROM "+getTableName()+" ", null);
        while (cursor.moveToNext()) {
            count=cursor.getInt(cursor.getColumnIndex("row_count"));
            break;
        }
        cursor.close();
        db.close();
        return count;
    }




}
