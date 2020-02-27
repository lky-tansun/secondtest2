package com.example.sqlite2;

import java.util.ArrayList;
import com.example.sqlite2.DBOpenHelper;
import com.example.sqlite2.Bm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper2
{
	private static DBOpenHelper dbOpenHelper;// ����DBOpenHelper����
	private SQLiteDatabase sqliteDatabase;// ����SQLiteDatabase����
 
	public DBHelper2(Context context)// ���幹�캯��
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// ��ʼ��DBOpenHelper����
	}
 
	// �����û�����
	public void dbInsert(String bm) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// �Զ�д���������ݿ⣬��������д��getReadableDatabase()��ֻ��
		String sql = "insert into t_bm(bm) values (?)";
		// ���ݹ�����username��password�ֱ�˳���滻����sql��������?���Զ�ת�����ͣ���ͬ������׸��
		Object bindArgs[] = new Object[] { bm};
		// ִ�������޷���ֵ��sql���
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public void dbInsert2(String bm,String zw,String gz,String bt) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// �Զ�д���������ݿ⣬��������д��getReadableDatabase()��ֻ��
		String sql = "insert into t_zw(bm,zw,gz,bt) values (?,?,?,?)";
		// ���ݹ�����username��password�ֱ�˳���滻����sql��������?���Զ�ת�����ͣ���ͬ������׸��
		Object bindArgs[] = new Object[] { bm,zw,gz,bt};
		// ִ�������޷���ֵ��sql���
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Bm dbQueryOneByUsername(String bm) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_bm where bm=? ";
		String[] selectionArgs = new String[] { bm };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Bm user = new Bm();
			user.setBm(cursor.getString(cursor.getColumnIndex("bm")));
			return user;// ����һ���û���ǰ̨
		}
		return null;// û�з���null
	}
	public Zw dbQueryOneByZw(String zw) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_zw where zw=? ";
		String[] selectionArgs = new String[] { zw };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			Zw user = new Zw();
			user.setBm(cursor.getString(cursor.getColumnIndex("bm")));
			user.setZw(cursor.getString(cursor.getColumnIndex("zw")));
			user.setGz(cursor.getString(cursor.getColumnIndex("gz")));
			user.setBt(cursor.getString(cursor.getColumnIndex("bt")));
			return user;// ����һ���û���ǰ̨
		}
		return null;// û�з���null
	}
	// ��������ж���������
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_bm where id>=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getInt(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
 	
	public void dbDeleteBm(String bm) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from t_bm where bm=?";
		Object bindArgs[] = new Object[] { bm };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public void dbDeleteZw(String zw) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from t_zw where zw=?";
		Object bindArgs[] = new Object[] { zw };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	
	public ArrayList<Bm> dbQueryAll() {
		ArrayList<Bm> userArrayList = new ArrayList<Bm>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_bm where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// �α��ͷ����β
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
				Bm bumen = new Bm();	
				bumen.setBm(cursor.getString(cursor.getColumnIndex("bm")));
				userArrayList.add(bumen);
		}
		return userArrayList;
	}
	public int dbGetUserSize2(String bm) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_zw where bm=?";
		String[] selectionArgs = new String[] { bm };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// �ж�Cursor���Ƿ�������
		{
			return cursor.getInt(0);// �����ܼ�¼��
		}
		return 0;// ���û�����ݣ��򷵻�0
	}
	@SuppressWarnings("finally")
	public ArrayList<Zw> dbSelectzw(String bm) {
		ArrayList<Zw> userArrayList = new ArrayList<Zw>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_zw where bm=?";
		String[] selectionArgs = new String[] { bm };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		// �α��ͷ����β
	    try{	
	    	for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
				Zw zhiwei = new Zw();	
				zhiwei.setZw(cursor.getString(cursor.getColumnIndex("zw")));
				userArrayList.add(zhiwei);
				} 
	    	}catch (Exception ex) {
	    		ex.printStackTrace();
            } finally {
            	if (cursor != null) {
            		cursor.close();
            }
            	if (dbOpenHelper != null) {
            		dbOpenHelper.close();
            }
            	return userArrayList;
        }
	}
	public void dbUpdateInfo(String gz,String bt,String zw ) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "update t_zw set gz=?,bt=? where zw=?";
		Object bindArgs[] = new Object[] { gz,bt,zw };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	
}