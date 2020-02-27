package com.example.sqlite2;

import java.util.ArrayList;
import com.example.sqlite2.DBOpenHelper;
import com.example.sqlite2.Bm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper2
{
	private static DBOpenHelper dbOpenHelper;// 创建DBOpenHelper对象
	private SQLiteDatabase sqliteDatabase;// 创建SQLiteDatabase对象
 
	public DBHelper2(Context context)// 定义构造函数
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// 初始化DBOpenHelper对象
	}
 
	// 插入用户数据
	public void dbInsert(String bm) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
		String sql = "insert into t_bm(bm) values (?)";
		// 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
		Object bindArgs[] = new Object[] { bm};
		// 执行这条无返回值的sql语句
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public void dbInsert2(String bm,String zw,String gz,String bt) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
		String sql = "insert into t_zw(bm,zw,gz,bt) values (?,?,?,?)";
		// 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
		Object bindArgs[] = new Object[] { bm,zw,gz,bt};
		// 执行这条无返回值的sql语句
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Bm dbQueryOneByUsername(String bm) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_bm where bm=? ";
		String[] selectionArgs = new String[] { bm };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			Bm user = new Bm();
			user.setBm(cursor.getString(cursor.getColumnIndex("bm")));
			return user;// 返回一个用户给前台
		}
		return null;// 没有返回null
	}
	public Zw dbQueryOneByZw(String zw) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_zw where zw=? ";
		String[] selectionArgs = new String[] { zw };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			Zw user = new Zw();
			user.setBm(cursor.getString(cursor.getColumnIndex("bm")));
			user.setZw(cursor.getString(cursor.getColumnIndex("zw")));
			user.setGz(cursor.getString(cursor.getColumnIndex("gz")));
			user.setBt(cursor.getString(cursor.getColumnIndex("bt")));
			return user;// 返回一个用户给前台
		}
		return null;// 没有返回null
	}
	// 求出表中有多少条数据
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_bm where id>=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getInt(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
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
		// 游标从头读到尾
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
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getInt(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
	@SuppressWarnings("finally")
	public ArrayList<Zw> dbSelectzw(String bm) {
		ArrayList<Zw> userArrayList = new ArrayList<Zw>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_zw where bm=?";
		String[] selectionArgs = new String[] { bm };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		// 游标从头读到尾
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