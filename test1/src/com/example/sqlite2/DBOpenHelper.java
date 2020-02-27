package com.example.sqlite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBOpenHelper extends SQLiteOpenHelper {
 

    
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "SqliteTest2.db", null, 1);//��ϵͳ����һ��SqliteTest.db�ļ���������ݿ⣬����1�����ݿ�汾��
	}
 
	@Override
	public void onCreate(SQLiteDatabase sqliteDatabase) {
		String sql=
				"create table if not exists t_bm("+
						"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
						"bm VARCHAR(255)"+
				")";
		String sql2=
				"create table if not exists t_zw("+
						"id INTEGER PRIMARY KEY AUTOINCREMENT,"+
						"bm VARCHAR(255),"+
						"zw VARCHAR(255),"+
						"gz VARCHAR(5),"+
						"bt VARCHAR(4)"+
				")";
		sqliteDatabase.execSQL(sql);
		sqliteDatabase.execSQL(sql2);
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		//�����Ǹ������ݿ�汾ʱ�������ķ���
	}

}