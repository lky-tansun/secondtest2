package com.example.sqlite5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBOpenHelper extends SQLiteOpenHelper {
 

    
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "SqliteTest5.db", null, 1);
	}
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		String sql=
				"create table if not exists t_dd("+
						"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
						"name VARCHAR(255),"+
						"old_d VARCHAR(255),"+
						"old_p VARCHAR(255),"+
						"new_d VARCHAR(255),"+
						"new_p VARCHAR(255),"+
						"time VARCHAR(255),"+
						"yh VARCHAR(255)"+						
				")";
		sqliteDatabase.execSQL(sql);
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//这里是更新数据库版本时所触发的方法
	}

}