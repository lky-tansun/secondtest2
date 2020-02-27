package com.example.sqlite4;



import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper4
{
	private DBOpenHelper dbOpenHelper;// ����DBOpenHelper����
	private SQLiteDatabase sqliteDatabase;// ����SQLiteDatabase����
 
	public DBHelper4(Context context)// ���幹�캯��
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// ��ʼ��DBOpenHelper����
	}
 
	// �����û�����
	public void dbInsert(String name,String b_money,String subsidy,int month,int days,int hours,int t_money,double f_one,double tax,double salary) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// �Զ�д���������ݿ⣬��������д��getReadableDatabase()��ֻ��
		String sql = "insert into t_gz(name,b_money,subsidy,month,days,hours,t_money,f_one,tax,salary) values (?,?,?,?,?,?,?,?,?,?)";
		// ���ݹ�����username��password�ֱ�˳���滻����sql��������?���Զ�ת�����ͣ���ͬ������׸��
		Object bindArgs[] = new Object[] { name,b_money,subsidy,month,days,hours,t_money,f_one,tax,salary };
		// ִ�������޷���ֵ��sql���
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Gz dbQueryOneByUsername(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_gz where name=? ";
		String[] selectionArgs = new String[] { name};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Gz gz = new Gz();
			gz.setName(cursor.getString(cursor.getColumnIndex("name")));
			gz.setB_money(cursor.getString(cursor.getColumnIndex("b_money")));
			gz.setSubsidy(cursor.getString(cursor.getColumnIndex("subsidy")));
			gz.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
			gz.setDays(cursor.getInt(cursor.getColumnIndex("days")));
			gz.setHours(cursor.getInt(cursor.getColumnIndex("hours")));
			gz.setT_money(cursor.getInt(cursor.getColumnIndex("t_money")));
			gz.setF_one(cursor.getDouble(cursor.getColumnIndex("f_one")));
			gz.setTax(cursor.getDouble(cursor.getColumnIndex("tax")));
			gz.setSalary(cursor.getDouble(cursor.getColumnIndex("salary")));
			return gz;// ����һ���û���ǰ̨
		}else{
			Gz gz2 = new Gz();
			gz2.setTax(0.0);
			return gz2;
		}
	}
	// ��������ж���������
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_gz where id>=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getInt(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
	public ArrayList<Gz> dbQueryAll() {
		ArrayList<Gz> userArrayList = new ArrayList<Gz>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_gz where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// �α��ͷ����β
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
				Gz gz = new Gz();	
				gz.setName(cursor.getString(cursor.getColumnIndex("name")));
				gz.setB_money(cursor.getString(cursor.getColumnIndex("b_money")));
				gz.setSubsidy(cursor.getString(cursor.getColumnIndex("subsidy")));
				gz.setMonth(cursor.getInt(cursor.getColumnIndex("month")));
				gz.setDays(cursor.getInt(cursor.getColumnIndex("days")));
				gz.setHours(cursor.getInt(cursor.getColumnIndex("hours")));
				gz.setT_money(cursor.getInt(cursor.getColumnIndex("t_money")));
				gz.setF_one(cursor.getDouble(cursor.getColumnIndex("f_one")));
				gz.setTax(cursor.getDouble(cursor.getColumnIndex("tax")));
				gz.setSalary(cursor.getDouble(cursor.getColumnIndex("salary")));
				userArrayList.add(gz);
		}
		return userArrayList;
	}
	public Gz dbQueryOneByNameAndMonth(String name,int month) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_gz where name=? and month=?";
		String[] selectionArgs = new String[] { name,month+""};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Gz user = new Gz();
			user.setName(cursor.getString(cursor.getColumnIndex("name")));
			return user;// ����һ���û���ǰ̨
		}
		return null;// û�з���null
	}
}

