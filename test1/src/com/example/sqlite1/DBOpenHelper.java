package com.example.sqlite1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DBOpenHelper extends SQLiteOpenHelper {
 
	public DBOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "SqliteTest1.db", null, 1);//��ϵͳ����һ��SqliteTest.db�ļ���������ݿ⣬����1�����ݿ�汾��
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
		//�����Ǹ������ݿ�汾ʱ�������ķ���
	}
	
	public void add(){
		
	}
}
