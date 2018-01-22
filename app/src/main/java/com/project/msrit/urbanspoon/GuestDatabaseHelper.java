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
    private static final String guest_list = "guest";
    private static final String guest_list_col_2 = "Name";
    private static final String guest_list_col_3 = "Phno";

    public GuestDatabaseHelper(Context context) {
        super(context, DB_Name, null, 29);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query2 = "Create table " + guest_list + "(" + guest_list_col_2 + " text, " + guest_list_col_3 + " text )";
        db.execSQL(query2);
    }

    //    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {

//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + guest_list);
        onCreate(db);
    }


//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

//    }

    //Below module adds new entry to guest table. Used when new guest arrives at the queue
    public boolean add_entry(String Name, String Phno) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(guest_list_col_3, Phno);
        contentValues.put(guest_list_col_2, Name);

//        long result = sqLiteDatabase.insert(guest_list, null, contentValues);
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

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int deleted = sqLiteDatabase.delete("guest", "Name = ?", new String[]{name});
        return deleted;
    }

    public Cursor view_all() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor res = sqLiteDatabase.rawQuery("Select * from " + guest_list, null);
        Log.d("Inside view", "Display all");
        return res;
    }

}

