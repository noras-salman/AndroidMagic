package com.nasable.magiclibs.MagicLibs.MagicDatabase;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class _ExampleUser extends MagicDatabase {
    /**
     * @param context         The context
     * // @param databaseName    The databaseName
     * //@param databaseVersion The version of the database, should be when adding new columns
     **/
    public _ExampleUser(Context context) {
        super(context, "user.db", 1);
    }

    public class User{
        private int id;
        private String email;


        private String dateCreated;

        public User(int id, String email, String dateCreated) {
            this.id = id;
            this.email = email;
            this.dateCreated = dateCreated;
        }

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getDateCreated() {
            return dateCreated;
        }
    }

    @Override
    public List<MagicTable> getTables() {
        List<MagicTable> magicTables=new ArrayList<>();




        /* Approach 1 : Using a class
        * ------------*/
        magicTables.add(new MagicTableUsingModel(this, User.class) {
            @Override
            public Object populateObject(Cursor cursor) {
                // populating a class is better with hand haha
                return new User(cursor.getInt(cursor.getColumnIndex(MagicColumn.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("date_created")));
            }

            @Override
            public List<? extends Object> populateObject(List<Object> objectList) {
                return ((List<User>)(List<?>) objectList);
            }
        });




        /* Approach 2 : Building real time
         * ------------*/
        magicTables.add(new MagicTable(this) {
            @Override
            public String getTableName() {
                return "user";
            }

            @Override
            public List<MagicColumn> getColumns() {
                List<MagicColumn> magicColumns=new ArrayList<>();
                magicColumns.add(MagicColumn.getIdInstance());
                magicColumns.add(MagicColumn.getTextInstance("email",null));
                magicColumns.add(MagicColumn.getTimestampInstance("date_created" ));
                return magicColumns;
            }

            @Override
            public List<? extends Object> populateObject(List<Object> objectList) {
                return ((List<User>)(List<?>) objectList);
            }

            @Override
            public Object populateObject(Cursor cursor) {
                return new User(cursor.getInt(cursor.getColumnIndex(MagicColumn.COLUMN_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex("email")),
                        cursor.getString(cursor.getColumnIndex("date_created")));
            }
        });


        return magicTables;
    }
}
