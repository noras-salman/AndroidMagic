package com.nasable.notamia.MagicDatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class MagicTable {


    private MagicDatabase magicDatabase;

    /**
     *
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
     *
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
     *
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
     *
     * @param id
     * @param contentValues
     */
    public void updateById(int id, ContentValues contentValues) {
        SQLiteDatabase db = magicDatabase.getWritableDatabase();
        db.update(getTableName(), contentValues, "id=" + id, null);
        db.close();
    }


    public interface RowFetchListener {
        public void onCursorResult(Object object);
        public void onFinish();
    }

    public abstract  List<? extends Object>  populateObject(List<Object> objectList);
    public abstract Object populateObject(Cursor cursor);

    /**
     * cursor.getInt(cursor.getColumnIndex(COL_DURATION)),
     * TODO: look this design pattern for query https://stackoverflow.com/questions/34253161/how-should-i-populate-my-object
     * **/
    public List<Object> queryForObjects(String sqlQuery, RowFetchListener rowFetchListener) {
        SQLiteDatabase db = magicDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);
        List<Object> objects=new ArrayList<>();
        while (cursor.moveToNext()) {
            if(rowFetchListener!=null)
            rowFetchListener.onCursorResult(populateObject(cursor));
            objects.add(populateObject(cursor));
        }

        cursor.close();
        db.close();
        if(rowFetchListener!=null)
        rowFetchListener.onFinish();

        return objects;
    }




}
