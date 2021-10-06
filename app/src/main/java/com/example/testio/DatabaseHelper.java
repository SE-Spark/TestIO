package com.example.testio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "school.db";
    //private static final String DB_FULL_PATH = DATABASE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students(ID INTEGER PRIMARY KEY AUTOINCREMENT, StudentName TEXT,Code TEXT, level TEXT, ParentName TEXT, ParentPhone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
    }
    public boolean Insert(Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudentName", student.getStudentName());
        contentValues.put("Code",student.getCode());
        contentValues.put("level",student.getLevel());
        contentValues.put("ParentName", student.getParentName());
        contentValues.put("ParentPhone",student.getParentPhone());
        long result = sqLiteDatabase.insert("students", null, contentValues);
        return result != -1;  // if(cursor.getCount() > 0){ return false; }else{return true; }
    }


    public Boolean CheckStudent(Student student){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql ="SELECT * FROM user WHERE username=? AND password=?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{student.getStudentName(), student.getParentName()});
        return cursor.getCount() > 0;//if(cursor.getCount() > 0){return true;}else{return false;}
    }

    public ArrayList<Student> DisplayAllStudents() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "SELECT * FROM user";
        ArrayList<Student> students = new ArrayList<Student>();
        Student student = null;
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null );
        while (cursor.moveToNext()){
            student = new Student();
            student.setStudentName(cursor.getString(2));
            student.setCode(cursor.getString(3));
            student.setLevel(cursor.getString(4));
            student.setParentName(cursor.getString(5));
            student.setParentPhone(cursor.getString(6));
            students.add(student);
        }
        return students;
    }
}


