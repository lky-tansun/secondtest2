package com.example.sqlite1;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class DBHelper {
	private DBOpenHelper dbOpenHelper;// ����DBOpenHelper����
	private SQLiteDatabase sqliteDatabase;// ����SQLiteDatabase����
 
	public DBHelper(Context context)// ���幹�캯��
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// ��ʼ��DBOpenHelper����
	}
    
	// �����û�����
	public void dbInsert(String username,String password) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// �Զ�д���������ݿ⣬��������д��getReadableDatabase()��ֻ��
		String sql = "insert into t_user(username,password,isDel) values (?,?,0)";
		// ���ݹ�����username��password�ֱ�˳���滻����sql��������?���Զ�ת�����ͣ���ͬ������׸��
		Object bindArgs[] = new Object[] { username, password };
		// ִ�������޷���ֵ��sql���
		sqliteDatabase.execSQL(sql, bindArgs);
	}
 
	// ��������ж���������
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_user where isDel=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getInt(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
 
	// �����û��������û�
	public HR dbQueryOneByUsername(String username) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_user where username=? and isDel=0";
		String[] selectionArgs = new String[] { username };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			HR user = new HR();
			user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			return user;// ����һ���û���ǰ̨
		}
		return null;// û�з���null
	}
 
	// �����û������޸�����
	public void dbUpdatePassword(String username, String newPassword) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "update t_user set password=? where username=? and isDel=0";
		Object bindArgs[] = new Object[] { newPassword, username };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	
	public void dbDeleteUser(String username) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from t_user where username=?";
		Object bindArgs[] = new Object[] { username };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	// ��ѯ�����û�
	public ArrayList<HR> dbQueryAll() {
		ArrayList<HR> userArrayList = new ArrayList<HR>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_user where isDel = 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// �α��ͷ����β
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			if (cursor.getInt(cursor.getColumnIndex("isDel")) ==0) {
				HR user = new HR();
				
				user.setUsername(cursor.getString(cursor
						.getColumnIndex("username")));
				userArrayList.add(user);
			}
		}
		return userArrayList;
	}
 
}