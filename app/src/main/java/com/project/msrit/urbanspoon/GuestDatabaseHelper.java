package com.project.msrit.urbanspoon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Karanth on 11-01-2018.
 */

public class GuestDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_Name = "urbanSpoon";
    private static final String availability_list = "table_availability";
    private static final String guest_list = "guest";
    private static final String availability_list_col_1 = "ID";
    private static final String availability_list_col_2 = "Tablename";
    private static final String availability_list_col_3 = "Available";
    private static final String guest_list_col_1 = "ID";
    private static final String guest_list_col_2 = "Name";
    private static final String guest_list_col_3 = "Phno";

    public GuestDatabaseHelper(Context context) {
        super(context, DB_Name, null, 7);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//       String query1 = "Create table " + availability_list + "(" + availability_list_col_1 + " Integer Primary key AutoIncrement, " + availability_list_col_2 + " text, " + availability_list_col_3 + " Boolean)";
        String query2 = "Create table " + guest_list + "(" + guest_list_col_2 + " text, " + guest_list_col_3 + " Boolean )";

//        sqLiteDatabase.execSQL(query1);
        sqLiteDatabase.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + availability_list);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + guest_list);
        onCreate(sqLiteDatabase);
    }

    //The below module is to change availability value to true/false of a row, based on ID. Can be changed to table_name field dependent as well
    public boolean update(String ID, String availability) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(availability_list_col_3, availability);
        int val = sqLiteDatabase.update(availability_list, contentValues, "ID = ?", new String[]{ID});

        if (val != -1) {
            return true;
        } else {
            return false;
        }
    }

    //Below module adds new entry to guest table. Used when new guest arrives at the queue
    public boolean add_entry(String name, String phno) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(guest_list_col_3, phno);
        contentValues.put(guest_list_col_2, name);

        long result = sqLiteDatabase.insert(guest_list, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //Retrieve first guest details from the list
    public Cursor retrieve_next_guest() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor next_guest = sqLiteDatabase.rawQuery("Select * from " + guest_list + " limit 1", null);
        return next_guest;
    }

    public int remove_guest_from_queue(String name) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String delete = "Delete from " + guest_list + " where rowid in (select rowid from " + guest_list + " limit 1)";
//        sqLiteDatabase.execSQL(delete);

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int deleted = sqLiteDatabase.delete("guest", "Name = ?", new String[]{name});
//        String delete = "Delete from studentinfo1 where rowid in (select rowid from studentinfo1 limit 1)";
//        sqLiteDatabase.execSQL(delete);
        return deleted;
    }

    public Cursor view_all() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor res = sqLiteDatabase.rawQuery("Select * from " + guest_list, null);
        Log.d("Inside view", "Display all");
        return res;
    }

}

