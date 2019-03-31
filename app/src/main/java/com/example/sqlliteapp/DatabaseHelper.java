package com.example.sqlliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.TextView;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student.dp";
    public static final String TABLE_NAME ="Students";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "SURNAME";
    private static final String COL_4 = "MARKS";


    /**
     * Instantiates a new Database helper.
     *
     * @param context the context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //typically called first lunch after installation
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //typically called when you update the app
       // db.execSQL("Drop Table if Exists " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Insert data boolean.
     *
     * @param name    the name
     * @param surname the surname
     * @param marks   the marks
     * @return the true in case success
     */
    public boolean insertData(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    /**
     * Get all data cursor.
     *
     * @return the cursor {Cursor is an interface that provides a random read-write access to the result returned by the database query}
     */
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * FROM " + TABLE_NAME,null);
        return res;
    }

    /**
     * Update data.
     *
     * @param id      the id
     * @param name    the name
     * @param surname the surname
     * @param marks   the marks
     * @return the number of updated rows
     */
    public int updateData (String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id} );
        return db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id} );
    }

    /**
     * Delete  a row using an id .
     *
     * @param id the id
     * @return the number of deleted rows
     */
    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}
