package com.nasable.magiclibs.MagicLibs.MagicDatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public abstract class MagicDatabase extends SQLiteOpenHelper {


    /**
     * @param context           The context
     * @param databaseName      The databaseName
     * @param databaseVersion   The version of the database, should be when adding new columns
     * **/
    public MagicDatabase( Context context,  String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }

    /**
     *
     * @return a list with tables
     */
    public abstract List<MagicTable> getTables();

    /**
     *
     * @return a SQL instruction to create the database from the tables
     */
    private String getTableCreateSQL(){
        String sql="";
        for(MagicTable magicTable:getTables()){
            Log.d("MagicDatabase",  "Creating "+magicTable.getTableName());
            sql+=magicTable.getTableCreateSQL();
        }
        return sql;
    }

    /**
     *
     * @return a SQL instruction to delete all entries
     */
    private String getSqlDeleteTablesEntries(){
        String sql="";
        for(MagicTable magicTable:getTables()){
            Log.d("MagicDatabase",  "Deleting "+magicTable.getTableName());
            sql+=magicTable.getSqlDeleteEntries();
        }
        return sql;
    }

    /**
     * Handles creating a new database on request if it does not exist
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MagicDatabase", getDatabaseName()+" database being created");
        db.execSQL(getTableCreateSQL());
    }

    /**
     * Handles update // can be override in case you want to keep old entries on update
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(getSqlDeleteTablesEntries());
        onCreate(db);
    }
}
