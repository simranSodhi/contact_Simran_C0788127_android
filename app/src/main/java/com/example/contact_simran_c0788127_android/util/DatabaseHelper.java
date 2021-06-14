package com.example.contact_simran_c0788127_android.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_database";

    private static final String TABLE_NAME = "contact";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_ADDRESS = "address";


    public DatabaseHelper(@Nullable Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER NOT NULL CONSTRAINT contact_pk PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_LASTNAME + " VARCHAR(20) NOT NULL, " +
                COLUMN_EMAIL + " VARCHAR(20) NOT NULL, " +
                COLUMN_PHONE + " VARCHAR(20) NOT NULL, " +
                COLUMN_ADDRESS + " VARCHAR(20) NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table and then create it
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);
    }

    // insert
    public boolean addContact(String fName, String lName, String email, String phone, String address) {
        // we need a writeable instance of SQLite database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        // we need to define a content values in order to insert data into our database
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, fName);
        contentValues.put(COLUMN_LASTNAME, lName);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE,phone);
        contentValues.put(COLUMN_ADDRESS,address);


        // the insert method associated to SQLite database instance returns -1 if nothing is inserted
        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues) != -1;
    }


    public Cursor getAllContacts() {
        // we need a readable instance of database
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateContact(int id, String fName, String lName, String email, String phone, String address) {
        // we need a writeable instance of database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FIRSTNAME, fName);
        contentValues.put(COLUMN_LASTNAME, lName);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PHONE, phone);
        contentValues.put(COLUMN_ADDRESS, address);


        // update method associated to SQLite database instance returns number of rows affected
        return sqLiteDatabase.update(TABLE_NAME,
                contentValues,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }


    public boolean deleteContact(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        // the delete method associated to the SQLite database instance returns the number of rows affected
        return sqLiteDatabase.delete(TABLE_NAME,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}) > 0;
    }

}
