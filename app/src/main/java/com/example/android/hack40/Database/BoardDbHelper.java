package com.example.android.hack40.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BoardDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "board.db";
    public static int DATABASE_VERSION = 1;

    public BoardDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_BOARDS_TABLE =  "CREATE TABLE " + BoardContract.BoardEntry.TABLE_NAME + " ("
                + BoardContract.BoardEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BoardContract.BoardEntry.COLUMN_BOARD_CATEGORY + " INTEGER, "
                + BoardContract.BoardEntry.COLUMN_BOARD_NAME + " TEXT NOT NULL " +")";
        sqLiteDatabase.execSQL(SQL_CREATE_BOARDS_TABLE);

        String SQL_CREATE_LISTS_TABLE =  "CREATE TABLE " + BoardContract.ListEntry.TABLE_NAME + " ("
                + BoardContract.ListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BoardContract.ListEntry.COLUMN_LIST_NAME + " TEXT NOT NULL, "
                + BoardContract.ListEntry.COLUMN_BOARD_NAME + " TEXT NOT NULL " +")";
        sqLiteDatabase.execSQL(SQL_CREATE_LISTS_TABLE);

        String SQL_CREATE_CARDS_TABLE =  "CREATE TABLE " + BoardContract.CardEntry.TABLE_NAME + " ("
                + BoardContract.CardEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BoardContract.CardEntry.COLUMN_CARD_NAME + " TEXT NOT NULL, "
                + BoardContract.CardEntry.COLUMN_LIST_NAME + " TEXT NOT NULL, "
                + BoardContract.CardEntry.COLUMN_DUE_DATE + " TEXT NOT NULL, "
                + BoardContract.CardEntry.COLUMN_DESC + " TEXT NOT NULL, "
                + BoardContract.CardEntry.COLUMN_ARCHIVE + " INTEGER DEFAULT 0 , "
                + BoardContract.CardEntry.COLUMN_BOARD_NAME + " TEXT NOT NULL "+")";
        sqLiteDatabase.execSQL(SQL_CREATE_CARDS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
