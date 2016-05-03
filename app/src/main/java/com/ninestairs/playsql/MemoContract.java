package com.ninestairs.playsql;

import android.provider.BaseColumns;

/**
 * Created by kangt2000 on 2016. 5. 2..
 */
public final class MemoContract {
    public MemoContract(){}

    public static abstract class MemoEntry implements BaseColumns {
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_NAME_MEMO_ID = "memoId";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENT = "content";
        public static final String TABLE_NULLABLE = "title";

        private static final String TEXT_TYPE = " TEXT";
        private static final String INT_TYPE = " INT";
        private static final String COMMA_SEP = ",";
        public static final String SQL_CREATE_MEMO =
                "CREATE TABLE "+ MemoEntry.TABLE_NAME +
                 " (" +
                 MemoEntry._ID + " INTEGER PRIMATY KEY AUTO INCREMENT," +
                 MemoEntry.COLUMN_NAME_MEMO_ID + INT_TYPE + COMMA_SEP +
                 MemoEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                 MemoEntry.COLUMN_NAME_CONTENT + TEXT_TYPE +
                 ")" ;

        public static final String SQL_DELETE_MEMO = "DROP TABLE IF EXISTS " + MemoEntry.TABLE_NAME;
    }
}
