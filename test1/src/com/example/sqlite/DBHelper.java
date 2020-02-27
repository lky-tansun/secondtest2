package com.example.sqlite;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

 
public class DBHelper {
	private DBOpenHelper dbOpenHelper;// 创建DBOpenHelper对象
	private SQLiteDatabase sqliteDatabase;// 创建SQLiteDatabase对象
 
	public DBHelper(Context context)// 定义构造函数
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// 初始化DBOpenHelper对象
	}
 
	// 插入用户数据
	public void dbInsert(String username,String password) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
		String sql = "insert into t_user(username,password,isDel) values (?,?,0)";
		// 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
		Object bindArgs[] = new Object[] { username, password };
		// 执行这条无返回值的sql语句
		sqliteDatabase.execSQL(sql, bindArgs);
	}
 
	// 求出表中有多少条数据
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_user where isDel=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getInt(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
 
	// 根据用户名查阅用户
	public User dbQueryOneByUsername(String username) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_user where username=? and isDel=0";
		String[] selectionArgs = new String[] { username };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			User user = new User();
			user.setUsername(cursor.getString(cursor.getColumnIndex("username")));
			user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
			return user;// 返回一个用户给前台
		}
		return null;// 没有返回null
	}
 
	// 根据用户名，修改密码
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
	// 查询所有用户
	public ArrayList<User> dbQueryAll() {
		ArrayList<User> userArrayList = new ArrayList<User>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_user where isDel = 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// 游标从头读到尾
		for (cursor.moveToFirst(); !(cursor.isAfterLast()); cursor.moveToNext()) {
			if (cursor.getInt(cursor.getColumnIndex("isDel")) ==0) {
				User user = new User();
				
				user.setUsername(cursor.getString(cursor
						.getColumnIndex("username")));
				userArrayList.add(user);
			}
		}
		return userArrayList;
	}
 
}