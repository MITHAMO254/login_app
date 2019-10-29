package com.example.loginsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "Database";
    public Database(Context context){
        super(context, "users",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Query.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
    }
    public Boolean adduser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email",user.getEmail());
        values.put("password",user.getPassword());
        long ins = database.insert("users",null,values);
        if (ins == -1)return false;
        else return true;
    }
    public  Boolean getusers(){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from users", new String[]{});
        if (cursor.getCount()>0)return true;
        else  return false;
    }


    public  Boolean emailExists(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where email=?",new String[]{email});
        if (cursor.getCount()>0){
            return true;
        }
        else return false;
    }

    public Boolean checkPassword(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select password from users where email=?",new String[]{email});
        cursor.moveToFirst();
        String db_password = cursor.getString(0);

        if (password.equals(db_password)){
            return true;
        }else {
            return false;
        }
    }
   //    public Boolean insert(String email,String password){
    //        SQLiteDatabase db = this.getWritableDatabase();
    //        ContentValues contentValues = new ContentValues();
    //        contentValues.put("email",email);
    //        contentValues.put("password",password);
    //        long ins = db.insert("user",null,contentValues);
    //        if (ins==-1) return false;
    //        else return true;
}
