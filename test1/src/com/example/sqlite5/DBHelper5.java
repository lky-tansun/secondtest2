package com.example.sqlite5;



import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper5
{
	private DBOpenHelper dbOpenHelper;// ����DBOpenHelper����
	private SQLiteDatabase sqliteDatabase;// ����SQLiteDatabase����
 
	public DBHelper5(Context context)// ���幹�캯��
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// ��ʼ��DBOpenHelper����
	}
 
	// �����û�����
	public void dbInsert(String name,String old_d,String old_p,String new_d,String new_p,String time,String yh) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// �Զ�д���������ݿ⣬��������д��getReadableDatabase()��ֻ��
		String sql = "insert into t_dd(name,old_d,old_p,new_d,new_p,time,yh) values (?,?,?,?,?,?,?)";
		// ���ݹ�����username��password�ֱ�˳���滻����sql��������?���Զ�ת�����ͣ���ͬ������׸��
		Object bindArgs[] = new Object[] { name,old_d,old_p,new_d,new_p,time,yh };
		// ִ�������޷���ֵ��sql���
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Dd dbQueryOneByName(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_dd where name=? ";
		String[] selectionArgs = new String[] { name};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Dd dd = new Dd();
			dd.setName(cursor.getString(cursor.getColumnIndex("name")));
			dd.setOld_d(cursor.getString(cursor.getColumnIndex("old_d")));
			dd.setOld_p(cursor.getString(cursor.getColumnIndex("old_p")));
			dd.setNew_d(cursor.getString(cursor.getColumnIndex("new_d")));
			dd.setNew_p(cursor.getString(cursor.getColumnIndex("new_p")));
			dd.setTime(cursor.getString(cursor.getColumnIndex("time")));
			dd.setYh(cursor.getString(cursor.getColumnIndex("yh")));
			return dd;// ����һ���û���ǰ̨
		}return null;
	}
	// ��������ж���������
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_dd where id >= 0 ";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getInt(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
	public ArrayList<Dd> dbQueryAll() {
		ArrayList<Dd> userArrayList = new ArrayList<Dd>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_dd where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// �α��ͷ����β
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {;	
				Dd dd = new Dd();
				dd.setName(cursor.getString(cursor.getColumnIndex("name")));
				dd.setOld_d(cursor.getString(cursor.getColumnIndex("old_d")));
				dd.setOld_p(cursor.getString(cursor.getColumnIndex("old_p")));
				dd.setNew_d(cursor.getString(cursor.getColumnIndex("new_d")));
				dd.setNew_p(cursor.getString(cursor.getColumnIndex("new_p")));
				dd.setTime(cursor.getString(cursor.getColumnIndex("time")));
				dd.setYh(cursor.getString(cursor.getColumnIndex("yh")));
				userArrayList.add(dd);
		}
		return userArrayList;
	}
	
}

