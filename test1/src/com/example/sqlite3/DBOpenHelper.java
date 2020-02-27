package com.example.sqlite3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBOpenHelper extends SQLiteOpenHelper {
 

    
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "SqliteTest3.db", null, 1);//向系统申请一个SqliteTest.db文件存这个数据库，其中1是数据库版本。
	}
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		String sql=
				"create table if not exists t_yg("+
						"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
						"name VARCHAR(255),"+
						"sex VARCHAR(255),"+
						"age VARCHAR(2),"+
						"nation VARCHAR(255),"+
						"department VARCHAR(255),"+
						"position VARCHAR(255),"+
						"date VARCHAR(255),"+
						"idnumber VARCHAR(18),"+
						"number VARCHAR(11),"+
						"marriage VARCHAR(255),"+
						"edu VARCHAR(255),"+
						"money VARCHAR(5),"+
						"subsidy VARCHAR(3)"+
				")";//如果初次运行，建立一张t_user表，建表的时候注意，自增是AUTOINCREMENT，而不是mysql的AUTO_INCREMENT
		sqliteDatabase.execSQL(sql);
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//这里是更新数据库版本时所触发的方法
	}

}