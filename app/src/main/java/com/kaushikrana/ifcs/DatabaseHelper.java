package com.kaushikrana.ifcs;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;




public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Data_History";

    private static final String TABLE_IFSC = "ifsc";

    private static final String KEY_ID = "id";
    private static final String KEY_BANK = "bank";
    private static final String KEY_STATE = "state";
    private static final String KEY_BRANCH = "branch";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CITY = "city";
    private static final String KEY_DISTRICT = "district";
    private static final String KEY_IFSC = "ifsc";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_IFSC + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_BANK + " TEXT,"+
                KEY_STATE + " TEXT," + KEY_BRANCH + " TEXT," + KEY_ADDRESS + " TEXT,"+
                KEY_CITY + " TEXT," + KEY_DISTRICT + " TEXT," + KEY_IFSC + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IFSC);
        onCreate(db);
    }

    void addContact(Bank bank) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BANK, bank.get_bank());
        values.put(KEY_STATE, bank.get_state());
        values.put(KEY_BRANCH, bank.get_branch());
        values.put(KEY_ADDRESS, bank.get_address());
        values.put(KEY_CITY, bank.get_city());
        values.put(KEY_DISTRICT, bank.get_district());
        values.put(KEY_IFSC, bank.get_ifsc());

        db.insert(TABLE_IFSC, null, values);
        db.close();
    }

    public List<Bank> getAllContacts() {
        List<Bank> contactList = new ArrayList<Bank>();

        String selectQuery = "SELECT  * FROM " + TABLE_IFSC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Bank bank = new Bank();
                bank.set_id(Integer.parseInt(cursor.getString(0)));
                bank.set_bank(cursor.getString(1));
                bank.set_state(cursor.getString(2));
                bank.set_branch(cursor.getString(3));
                bank.set_address(cursor.getString(4));
                bank.set_city(cursor.getString(5));
                bank.set_district(cursor.getString(6));
                bank.set_ifsc(cursor.getString(7));

                contactList.add(bank);
            } while (cursor.moveToNext());
        }

        return contactList;
    }
}

