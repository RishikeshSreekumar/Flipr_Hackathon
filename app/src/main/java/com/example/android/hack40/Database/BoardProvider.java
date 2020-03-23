package com.example.android.hack40.Database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class BoardProvider extends ContentProvider {

    public static final String LOG_TAG = BoardProvider.class.getSimpleName();
    private BoardDbHelper dbHelper;

    private static final int BOARD = 100;

    private static final int LIST = 200;

    private static final int CARD = 300;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(BoardContract.CONTENT_AUTHORITY, BoardContract.PATH_BOARDS, BOARD);
        sUriMatcher.addURI(BoardContract.CONTENT_AUTHORITY, BoardContract.PATH_LISTS, LIST);
        sUriMatcher.addURI(BoardContract.CONTENT_AUTHORITY, BoardContract.PATH_CARDS, CARD);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new BoardDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case BOARD:
                cursor = database.query(BoardContract.BoardEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            case LIST:
                cursor = database.query(BoardContract.ListEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            case CARD:
                cursor = database.query(BoardContract.CardEntry.TABLE_NAME,projection,selection,selectionArgs,
                        null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BOARD:
                return insertBoard(uri, contentValues);
            case LIST:
                return insertList(uri, contentValues);
            case CARD:
                return insertCard(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertBoard(Uri uri, ContentValues values) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long id = database.insert(BoardContract.BoardEntry.TABLE_NAME,null,values);
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertList(Uri uri, ContentValues values) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long id = database.insert(BoardContract.ListEntry.TABLE_NAME,null,values);
        return ContentUris.withAppendedId(uri, id);
    }

    private Uri insertCard(Uri uri, ContentValues values) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        long id = database.insert(BoardContract.CardEntry.TABLE_NAME,null,values);
        return ContentUris.withAppendedId(uri, id);
    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case CARD:
                return database.update(BoardContract.CardEntry.TABLE_NAME,contentValues,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Updating is not supported for " + uri);
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//
//        final int match = sUriMatcher.match(uri);
//        switch (match) {
//            case BOARD:
//                return database.delete(BoardContract.BoardEntry.TABLE_NAME, selection, selectionArgs);
//            default:
//                throw new IllegalArgumentException("Deletion is not supported for " + uri);
//        }
        return 0;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case BOARD:
                return BoardContract.BoardEntry.CONTENT_LIST_TYPE;
            case LIST:
                return BoardContract.ListEntry.CONTENT_LIST_TYPE;
            case CARD:
                return BoardContract.CardEntry.CONTENT_LIST_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}