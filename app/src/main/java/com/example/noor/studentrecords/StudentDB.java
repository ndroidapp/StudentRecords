package com.example.noor.studentrecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Noor on 2/5/2018.
 */

public class StudentDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "studentdb";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "student";

    private static final String ROLL_NO="roll_no";
    private static final String NAME="name";
    private static final String PHONE_NO="phone_no";
    private static final String EMAIL="email";
    private static final String GENDER="gender";
    Context context;



    public StudentDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME
                + "("
                + ROLL_NO + " INTEGER PRIMARY KEY,"
                + NAME + " TEXT,"
                + PHONE_NO + " TEXT,"
                + EMAIL + " TEXT,"
                + GENDER + " TEXT"
                + ")";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void addRecord(Record record) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ROLL_NO, record.getRoll());
        values.put(NAME, record.getName());
        values.put(PHONE_NO, record.getPhone());
        values.put(EMAIL, record.getEmail());
        values.put(GENDER, record.getGender());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Record getRecord(int roll) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{ROLL_NO, NAME, PHONE_NO, EMAIL, GENDER},
                ROLL_NO + "=?",
                new String[]{String.valueOf(roll)},
                null,
                null,
                null);
        if (cursor != null)
            cursor.moveToFirst();
        Record record = new Record(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        return record;
    }
}
