package com.example.sqlite3;

import java.util.ArrayList;




import com.example.sqlite3.DBOpenHelper;
import com.example.sqlite3.Yg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper3
{
	private DBOpenHelper dbOpenHelper;// ����DBOpenHelper����
	private SQLiteDatabase sqliteDatabase;// ����SQLiteDatabase����
 
	public DBHelper3(Context context)// ���幹�캯��
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// ��ʼ��DBOpenHelper����
	}
 
	// �����û�����
	public void dbInsert(String name,String sex,String age,String nation,String department,String position,String date,String idnumber,String number,String marriage,String edu,String money,String subsidy) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// �Զ�д���������ݿ⣬��������д��getReadableDatabase()��ֻ��
		String sql = "insert into t_yg(name,sex,age,nation,department,position,date,idnumber,number,marriage,edu,money,subsidy) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// ���ݹ�����username��password�ֱ�˳���滻����sql��������?���Զ�ת�����ͣ���ͬ������׸��
		Object bindArgs[] = new Object[] { name,sex,age,nation,department,position,date,idnumber,number,marriage,edu,money,subsidy };
		// ִ�������޷���ֵ��sql���
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Yg dbQueryOneByUsername(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_yg where name=? ";
		String[] selectionArgs = new String[] { name };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Yg yg = new Yg();
			yg.setName(cursor.getString(cursor.getColumnIndex("name")));
			yg.setSex(cursor.getString(cursor.getColumnIndex("sex")));
			yg.setAge(cursor.getString(cursor.getColumnIndex("age")));
			yg.setNation(cursor.getString(cursor.getColumnIndex("nation")));
			yg.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
			yg.setPosition(cursor.getString(cursor.getColumnIndex("position")));
			yg.setDate(cursor.getString(cursor.getColumnIndex("date")));
			yg.setIdnumber(cursor.getString(cursor.getColumnIndex("idnumber")));
			yg.setNumber(cursor.getString(cursor.getColumnIndex("number")));
			yg.setMarriage(cursor.getString(cursor.getColumnIndex("marriage")));
			yg.setEdu(cursor.getString(cursor.getColumnIndex("edu")));
			yg.setMoney(cursor.getString(cursor.getColumnIndex("money")));
			yg.setSubsidy(cursor.getString(cursor.getColumnIndex("subsidy")));
			return yg;// ����һ���û���ǰ̨
		}
		return null;// û�з���null
	}
	public Yg dbQueryOneByIdnumber(String idnumber) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_yg where idnumber=?";
		String[] selectionArgs = new String[] { idnumber};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Yg user = new Yg();
			user.setIdnumber(cursor.getString(cursor.getColumnIndex("idnumber")));
			return user;// ����һ���û���ǰ̨
		}
		return null;// û�з���null
	}
	// ��������ж���������
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_yg where id>=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getInt(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
 	
	public void dbDeleteUser(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from t_yg where name=?";
		Object bindArgs[] = new Object[] { name };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	// ��ѯ�����û�
	public ArrayList<Yg> dbQueryAll() {
		ArrayList<Yg> userArrayList = new ArrayList<Yg>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_yg where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// �α��ͷ����β
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			if (cursor.getInt(cursor.getColumnIndex("id")) >=0) {
				Yg yuangong = new Yg();
				yuangong.setName(cursor.getString(cursor.getColumnIndex("name")));
				yuangong.setDepartment(cursor.getString(cursor.getColumnIndex("department")));
				yuangong.setSex(cursor.getString(cursor.getColumnIndex("sex")));
				userArrayList.add(yuangong);
			}
		}
		return userArrayList;
	}
	
	public void dbUpdateInfo(String number,String marriage,String edu,String name ) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "update t_yg set number=?,marriage=?,edu=? where name=?";
		Object bindArgs[] = new Object[] { number,marriage,edu,name };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public void dbUpdateInfo(String bm,String zw,String gz,String bt,String name ) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "update t_yg set department=?,position=?,money=?,subsidy=? where name=?";
		Object bindArgs[] = new Object[] { bm,zw,gz,bt,name };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
}

