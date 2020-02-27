package com.example.sqlite5;



import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper5
{
	private DBOpenHelper dbOpenHelper;// 创建DBOpenHelper对象
	private SQLiteDatabase sqliteDatabase;// 创建SQLiteDatabase对象
 
	public DBHelper5(Context context)// 定义构造函数
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// 初始化DBOpenHelper对象
	}
 
	// 插入用户数据
	public void dbInsert(String name,String old_d,String old_p,String new_d,String new_p,String time,String yh) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
		String sql = "insert into t_dd(name,old_d,old_p,new_d,new_p,time,yh) values (?,?,?,?,?,?,?)";
		// 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
		Object bindArgs[] = new Object[] { name,old_d,old_p,new_d,new_p,time,yh };
		// 执行这条无返回值的sql语句
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Dd dbQueryOneByName(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_dd where name=? ";
		String[] selectionArgs = new String[] { name};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			Dd dd = new Dd();
			dd.setName(cursor.getString(cursor.getColumnIndex("name")));
			dd.setOld_d(cursor.getString(cursor.getColumnIndex("old_d")));
			dd.setOld_p(cursor.getString(cursor.getColumnIndex("old_p")));
			dd.setNew_d(cursor.getString(cursor.getColumnIndex("new_d")));
			dd.setNew_p(cursor.getString(cursor.getColumnIndex("new_p")));
			dd.setTime(cursor.getString(cursor.getColumnIndex("time")));
			dd.setYh(cursor.getString(cursor.getColumnIndex("yh")));
			return dd;// 返回一个用户给前台
		}return null;
	}
	// 求出表中有多少条数据
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_dd where id >= 0 ";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getInt(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
	public ArrayList<Dd> dbQueryAll() {
		ArrayList<Dd> userArrayList = new ArrayList<Dd>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_dd where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// 游标从头读到尾
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

