package com.example.sqlite1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBOpenHelper extends SQLiteOpenHelper {
 
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "SqliteTest1.db", null, 1);//向系统申请一个SqliteTest.db文件存这个数据库，其中1是数据库版本。
	}
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		String sql=
				"create table if not exists t_user("+
						"username VARCHAR(255) NOT NULL PRIMARY KEY,"+
						"password VARCHAR(255),"+
						"isDel INTEGER DEFAULT 0"+
				")";
		sqliteDatabase.execSQL(sql);
		String sql2 = "insert into t_user(username,password,isDel) values (1,1,0)";
		sqliteDatabase.execSQL(sql2);
	}
    
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//这里是更新数据库版本时所触发的方法
	}
	
	public void add(){
		
	}
}
