package com.example.admin.password;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/*
 * 文件名：MySQLiteHelper
 * 作者：created by admin on 2018 十一月
 * 描述：
 */public class MySQLiteHelper extends SQLiteOpenHelper {

     private static final int DATABASE_VERSION = 1;
     private static final String DATABASE_NAME = "PasswordDB";

     private static final String TABLE_PASSWORDS = "passwords";

     private static final String KEY_ID = "id";
     private static final String KEY_APPNAME = "appname";
     private static final String KEY_PASSWORD = "password";

     private static final String[] columns = {KEY_ID, KEY_APPNAME, KEY_PASSWORD};

     // MySQLiteHelper constructor must call the super class constructor
     public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Override onCreate() method to create the table(s)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PASSWORD_TABLE = "create table passwords (" +
                                    "id     integer primary key autoincrement," +
                                    "appname  text," +
                                    "password text )";
        db.execSQL(CREATE_PASSWORD_TABLE);
    }

    // Override onUpgrade() to drop old tables and create new ones.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists passwords");

        this.onCreate(db);
    }

    public void addPassword(Pwd password) {
        Log.d("addPassword", password.toString());

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_APPNAME, password.getAppname());
        values.put(KEY_PASSWORD, password.getPassword());

        // 3. insert
        db.insert(TABLE_PASSWORDS, null, values);

        // 4.close
        db.close();
    }

    public Pwd getPassword(int id) {
         SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PASSWORDS, columns, "id = ?",
                new String[] {String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor != null)
            cursor.moveToFirst();

        Pwd pwd = new Pwd();
        pwd.setId(Integer.parseInt(cursor.getString(0)));
        pwd.setAppname(cursor.getString(1));
        pwd.setPassword(cursor.getString(2));

        Log.d("getPassword("+id+")", pwd.toString());

        return pwd;
    }

    public List<Pwd> getAllPasswords() {
         List<Pwd> passwords = new LinkedList<Pwd>();

         String query = "select * from " + TABLE_PASSWORDS;

         SQLiteDatabase db = this.getWritableDatabase();
         Cursor cursor = db.rawQuery(query, null);

         Pwd password = null;
         if (cursor.moveToFirst()) {
             do {
                 password = new Pwd();
                 password.setId(Integer.parseInt(cursor.getString(0)));
                 password.setAppname(cursor.getString(1));
                 password.setPassword(cursor.getString(2));

                 passwords.add(password);
             } while (cursor.moveToNext());
         }

         Log.d("getAllPasswords()", passwords.toString());

         return passwords;
    }

    public int updatePassword(Pwd password) {
         SQLiteDatabase db = this.getWritableDatabase();

         ContentValues values = new ContentValues();
         values.put("appname", password.getAppname());
         values.put("password", password.getPassword());

         int i = db.update(TABLE_PASSWORDS, values, KEY_ID+" = ?", new String[] {String.valueOf(password.getId())});

         db.close();

         return i;
    }

    public void deletePassword(Pwd password) {
         SQLiteDatabase db = this.getWritableDatabase();

         db.delete(TABLE_PASSWORDS, KEY_ID+" = ?", new String[] {String.valueOf(password.getId())});

         db.close();

         Log.d("deletePwd", password.toString());
    }
}
