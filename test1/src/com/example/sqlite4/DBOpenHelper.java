package com.example.sqlite4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBOpenHelper extends SQLiteOpenHelper {
 

    
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "SqliteTest4.db", null, 1);
	}
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		String sql=
				"create table if not exists t_gz("+
						"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
						"name VARCHAR(255),"+
						"b_money VARCHAR(5),"+
						"subsidy VARCHAR(3),"+
						"month VARCHAR(2),"+
						"days VARCHAR(2),"+
						"hours VARCHAR(2),"+
						"t_money VARCHAR(10),"+
						"f_one VARCHAR(10),"+
						"tax VARCHAR(10),"+
						"salary VARCHAR(10)"+
				")";
		sqliteDatabase.execSQL(sql);
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//这里是更新数据库版本时所触发的方法
	}

}