package com.ninestairs.playsql;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;



/**
 *==============
 * SQLite 이야기
 *==============
 * @주요인물
 *
 * 1. SQLiteOpenHelper
 * --특징 : 부지런하다 (DB가 없으면 알아서 생성하고, DB 스키마가 바뀌면 알아서 업데이트한다. 단 미리 어떻게 할지 알려주어야 함)
 *
 * 2. SQLiteDatabase
 * --특징 : 부끄러움이 많다 (직접 접근하는것이 아니라 SQLiteOpenHelper 를 통해 접근한다.)
 *       : 까탈스럽다 (Table에 정보를 넣을 때는 ContentValue 로 감싸서 넣어야 한다, Table에 select 쿼리 후에는 Cursor 로 감싸서 돌려준다.)
 *
 *
 * @주요아이템
 *
 * 1. MemoContract
 * --특징 : 테이블 형식과 쿼리등을 계약서처럼 미리 적어둔 것이다
 *
 *
 * @줄거리
 *
 * EditText 에 title 과 content 를 받아들여서
 * SQLiteOpenHelper 와 SQLiteDatabase 가 협력하여
 * Memo Table 에서 정보를 넣고 빼고 삭제하는 이야기
 *
 *
 * --------------
 * 또 다른 이야기
 * --------------
 *
 * 1. EditText
 *
 *
 *
 * */
public class MainActivity extends AppCompatActivity {

    DBHelper myDBHelper;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1. db 만든다
        //2. db & 테이블 세팅한다
        //3. (클릭시) 테이블에 정보를 넣는다
        //4. (클릭시) 테이블에서 정보를 가져온다
        //5. (클릭시) 테으블에서 정보를 삭제한다

        //DBHelper 를 통해 db 가져온다
        myDBHelper = new DBHelper(this);


    }



    public void getButtonClicked(View view){

        db = myDBHelper.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + MemoContract.MemoEntry.TABLE_NAME;

        //rawQuery 메소드 할용법 많음 학습요망!
        Cursor c = db.rawQuery(selectQuery,null);

        if (c != null) c.moveToFirst();

        while(c.moveToNext()){

            int memoId = c.getInt(c.getColumnIndex("memoId"));
            String title = c.getString(c.getColumnIndex("title"));
            String content = c.getString(c.getColumnIndex("content"));

            String summary = title + " / " + content + " / " + String.valueOf(memoId);
            Log.d("SQLite", summary);
        }




    }



    public void addButtonClicked(View view){

        db = myDBHelper.getWritableDatabase();
        //memoId, title, content
        EditText mmt;
        EditText mmc;
        mmt = (EditText)findViewById(R.id.memoTitle);
        mmc = (EditText)findViewById(R.id.MemoContent);

        int memoId = 5425;
        String title = mmt.getText().toString();
        String content = mmc.getText().toString();

        //db에 정보를 넣을 때는 ContentValues 이용해서 넣어야 한다
        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_MEMO_ID,memoId);
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE,title);
        values.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENT,content);

        long newRowId;
        newRowId = db.insert(MemoContract.MemoEntry.TABLE_NAME,MemoContract.MemoEntry.TABLE_NULLABLE,values);

        Log.d("DEBUG",String.valueOf(newRowId));

        //다시 세팅
        mmt.setText("");
        mmc.setText("");

    }


    public void deleteButtonClicked(View view){

        //타이틀 이름이 같은 것을 삭제한다
        EditText mmt;
        mmt = (EditText) findViewById(R.id.memoTitle);
        String title = mmt.getText().toString();

        db = myDBHelper.getWritableDatabase();
        db.delete(MemoContract.MemoEntry.TABLE_NAME, MemoContract.MemoEntry.COLUMN_NAME_TITLE + "= ?",new String[]{title});

        mmt.setText("");
    }



    protected void onDestroy(){
        super.onDestroy();
        db.close();

    }
}



