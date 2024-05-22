package com.example.crudcourse;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // declare these variables
    EditText courseName, courseDes, courseLevel, courseInstructor;
    Button btnInsert, btnUpdate, btnDelete, btnView, btnFont;
    TextView txtTitle;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // intialize the views
        courseDes = findViewById(R.id.edtCourseDescription);
        courseLevel = findViewById(R.id.edtCourseLevel);
        courseInstructor = findViewById(R.id.edtCourseInstructor);
        courseName = findViewById(R.id.edtCourseName);

        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnView = findViewById(R.id.btnView);
        btnFont = findViewById(R.id.btnFont);


        txtTitle = findViewById(R.id.txtTitle);

        // intialize the DBAgent
        myDB = new DatabaseHelper(this);

        // insert operation in the Button insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_Name = courseName.getText().toString();
                String course_Des = courseDes.getText().toString();
                String course_Level = courseLevel.getText().toString();
                String course_Instructor = courseInstructor.getText().toString();

                myDB.insertCourse(course_Name, course_Des, course_Level, course_Instructor);
            }
        });

        // update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String course_Name = courseName.getText().toString();
                String course_Des = courseDes.getText().toString();
                String course_Level = courseLevel.getText().toString();
                String course_Instructor = courseInstructor.getText().toString();

                myDB.updateCourse(course_Name, course_Des, course_Level, course_Instructor);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_Name = courseName.getText().toString();

                myDB.deleteCourse(course_Name);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = myDB.viewCourse();

                if (cursor.getCount() == 0) {
                    // If no data is available, display a message
                    Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // If data is available, build the string to display
                    StringBuffer buffer = new StringBuffer();
                    while (cursor.moveToNext()) {
                        buffer.append("ID: ").append(cursor.getString(0)).append("\n");
                        buffer.append("Name: ").append(cursor.getString(1)).append("\n");
                        buffer.append("Description: ").append(cursor.getString(2)).append("\n");
                        buffer.append("Level: ").append(cursor.getString(3)).append("\n");
                        buffer.append("Instructor: ").append(cursor.getString(4)).append("\n\n");
                    }
                    // Create and show the AlertDialog with the data
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Course Information");
                    builder.setMessage(buffer.toString());
                    builder.show();

                }

            }

        });


    }
}