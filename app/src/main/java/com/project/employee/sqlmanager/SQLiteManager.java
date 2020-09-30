package com.project.employee.sqlmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project.employee.response.EmployeeResponse;

import java.util.ArrayList;

public class SQLiteManager extends SQLiteOpenHelper {


    public SQLiteManager(Context context) {
        super(context, "employee_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS employee_table("+
                "id TEXT NOT NULL," +
                "name TEXT NOT NULL,age TEXT NOT NULL," +
                "salary TEXT NOT NULL,pic TEXT)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE employee_table");
        onCreate(db);

    }

    public void addEmployeeData(EmployeeResponse.data arrayList){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id",arrayList.getId());
        contentValues.put("name",arrayList.getEmployee_name());
        contentValues.put("age",arrayList.getEmployee_age());
        contentValues.put("salary",arrayList.getEmployee_salary());
        contentValues.put("pic",arrayList.getProfile_image());

        sqLiteDatabase.insert("employee_table",null,contentValues);
        sqLiteDatabase.close();

    }

    public ArrayList<EmployeeResponse.data> getSavedEmployee(){

        ArrayList<EmployeeResponse.data> data = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM employee_table",null);

        if(cursor.moveToFirst()){
            do{
                EmployeeResponse.data employee = new EmployeeResponse.data();
                employee.setId(cursor.getString(0));
                employee.setEmployee_name(cursor.getString(1));
                employee.setEmployee_age(cursor.getString(2));
                employee.setEmployee_salary(cursor.getString(3));
                employee.setProfile_image(cursor.getString(4));

               data.add(employee);

            }while (cursor.moveToNext());
        }
        return data;
    }

    public boolean deleteAllEmployee() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete("employee_table",null,null)>0;

    }

}
