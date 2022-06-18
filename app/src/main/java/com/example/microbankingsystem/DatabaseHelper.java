package com.example.microbankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TRANSACTIONS = "TRANSACTIONS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_ACC_NO = "ACC_NO";
    public static final String COLUMN_AMOUNT = "AMOUNT";
    public static final String COLUMN_TYPE = "TYPE";
    public static final String COLUMN_TRANS_DATE = "TRANS_DATE";
    public static final String TABLE_ACCOUNT = "ACCOUNT";
    public static final String COLUMN_ACCOUNT_NO = "ACC_NO";
    public static final String COLUMN_BALANCE = "BALANCE";
    public static final String COLUMN_JOINT = "JOINT";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "transactions.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTransactionTable = "CREATE TABLE " + TRANSACTIONS + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + COLUMN_ACC_NO + " VARCHAR(10) NOT NULL, " + COLUMN_AMOUNT + " DOUBLE NOT NULL, " + COLUMN_TYPE + " VARCHAR(10) NOT NULL, " + COLUMN_TRANS_DATE + " VARCHAR(10) NOT NULL)";
        sqLiteDatabase.execSQL(createTransactionTable);
        String createAccountTable = "CREATE TABLE " + TABLE_ACCOUNT + "(" + COLUMN_ACCOUNT_NO + "STRING PRIMARY KEY NOT NULL" + "," + COLUMN_BALANCE + "DOUBLE NOT NULL" + "," + COLUMN_JOINT + "BOOL NOT NULL" + ")";
        sqLiteDatabase.execSQL(createAccountTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean record_transaction(TransactionModel transactionModel){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ACC_NO, transactionModel.getAccNo());
        cv.put(COLUMN_AMOUNT, transactionModel.getAmount());
        cv.put(COLUMN_TYPE, transactionModel.getType());
        cv.put(COLUMN_TRANS_DATE, transactionModel.getDate().toString());

        long insert = db.insert(TRANSACTIONS, null, cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }
}
