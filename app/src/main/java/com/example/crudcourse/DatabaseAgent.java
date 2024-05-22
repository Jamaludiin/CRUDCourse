package com.example.crudcourse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;

public class DatabaseAgent extends SQLiteOpenHelper {
    // Optional to create variables
    private static final String DATABASE_NAME = "CourseInfo.db";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME = "my_course";
    private static final String COLUMN_ID = "c_id";
    private static final String COLUMN_NAME = "c_name";
    private static final String COLUMN_DESCRIPTION= "c_description";
    private static final String COLUMN_LEVEL = "c_level";
    private static final String COLUMN_INSTRUCTOR = "c_instructor";

    Context context;
    public DatabaseAgent(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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

    // insert
    void insertData( String name, String des, String level, String instru){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,name);
        cv.put(COLUMN_DESCRIPTION,des);
        cv.put(COLUMN_LEVEL,level);
        cv.put(COLUMN_INSTRUCTOR,instru);

        long result = db.insert(TABLE_NAME,null,cv);

        if(result == -1){
            Toast.makeText(context, "No success ", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "There is success inserted", Toast.LENGTH_SHORT).show();
        }

    }
}
