package com.example.android.hack40.Database;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class BoardContract implements BaseColumns {

    public static final String CONTENT_AUTHORITY = "com.example.android.hack40";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BOARDS = "boards";
    public static final String PATH_LISTS = "lists";
    public static final String PATH_CARDS = "cards";

    public static abstract class BoardEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOARDS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOARDS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOARDS;


        static final String TABLE_NAME = "boards";
        public static final String _ID = "_id";
        public static final String COLUMN_BOARD_NAME = "name";
        public static final String COLUMN_BOARD_CATEGORY = "category";

        public static final int CAT_PUBLIC = 1;
        public static final int CAT_PRIVATE = 0;

        static boolean isValidCat(int cat){
            return cat == CAT_PUBLIC || cat == CAT_PRIVATE ;
        }
    }

    public static abstract class ListEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LISTS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LISTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LISTS;

        static final String TABLE_NAME = "lists";
        public static final String _ID = "_id";
        public static final String COLUMN_LIST_NAME = "list_name";
        public static final String COLUMN_BOARD_NAME = "board_name";

    }

    public static abstract class CardEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_CARDS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARDS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARDS;

        static final String TABLE_NAME = "cards";
        public static final String _ID = "_id";
        public static final String COLUMN_LIST_NAME = "list_name";
        public static final String COLUMN_BOARD_NAME = "board_name";
        public static final String COLUMN_CARD_NAME = "card_name";
        public static final String COLUMN_DUE_DATE = "due_date";
        public static final String COLUMN_DESC = "description";
        public static final String COLUMN_ARCHIVE = "is_archived";


    }


}
