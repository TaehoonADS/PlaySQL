package com.ninestairs.playsql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kangt2000 on 2016. 5. 2..
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MemoBox.db";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //DB가 없다면 onCreate 호출함
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MemoContract.MemoEntry.SQL_CREATE_MEMO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MemoContract.MemoEntry.SQL_DELETE_MEMO);
        onCreate(db);
    }
}
