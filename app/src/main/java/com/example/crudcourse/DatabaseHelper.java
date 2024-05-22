package com.example.crudcourse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Optional to create variables
    private static final String DATABASE_NAME = "CourseInfo.db";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME = "my_course";
    private static final String COLUMN_ID = "c_id";
    private static final String COLUMN_NAME = "c_name";
    private static final String COLUMN_DESCRIPTION= "c_description";
    private static final String COLUMN_LEVEL = "c_level";
    private static final String COLUMN_INSTRUCTOR = "c_instructor";

    // context
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // you can use the var names such as DATABASE_NAME, DATABASE_VERSION
        this.context = context; // Initialize the context field
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_LEVEL + " TEXT, " +
                        COLUMN_INSTRUCTOR + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // create CRUD METHODS
    void insertCourse(String c_name, String c_description, String c_level, String c_instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, c_name);
        cv.put(COLUMN_DESCRIPTION, c_description);
        cv.put(COLUMN_LEVEL, c_level);
        cv.put(COLUMN_INSTRUCTOR, c_instructor);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed to Insert ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Inserted new row " + result, Toast.LENGTH_SHORT).show();

        }
    }



    /*
    // update course
    void updatetCourse(String c_name, String c_description, String c_level, String c_instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //cv.put(COLUMN_NAME, c_name);
        cv.put(COLUMN_DESCRIPTION, c_description);
        cv.put(COLUMN_LEVEL, c_level);
        cv.put(COLUMN_INSTRUCTOR, c_instructor);

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME+"WHERE COLUMN_NAME = ?", new String[] {c_name});

        if (cursor.getCount()>0)   {
            long result = db.update(TABLE_NAME, cv, "c_name=?", new String[] {c_name});

            if(result == -1){
                Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Successfully Updated new row", Toast.LENGTH_SHORT).show();

            }} else {
            Toast.makeText(context, "Successfully Updated new rows", Toast.LENGTH_SHORT).show();
        }
    }

    // delete course
    void deletetCourse(String c_name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_NAME + "WHERE c_name=?", new String[] {c_name});

        if (cursor.getCount()>0)   {
            long result = db.delete(TABLE_NAME, "c_name=?", new String[] {c_name});

            if(result == -1){
                Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context, "Successfully Deleted new row", Toast.LENGTH_SHORT).show();

            }} else {
            Toast.makeText(context, "Successfully Deleted new rows", Toast.LENGTH_SHORT).show();
        }
    }

    // retrieve course
    public Cursor viewCourse() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }*/


    void updateCourse(String c_name, String c_description, String c_level, String c_instructor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DESCRIPTION, c_description);
        cv.put(COLUMN_LEVEL, c_level);
        cv.put(COLUMN_INSTRUCTOR, c_instructor);

        int result = db.update(TABLE_NAME, cv, COLUMN_NAME + "=?", new String[]{c_name});

        if (result == -1) {
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated the row " + result, Toast.LENGTH_SHORT).show();
        }
    }

    void deleteCourse(String c_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUMN_NAME + "=?", new String[]{c_name});

        if (result == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted the row", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor viewCourse() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}

