package com.example.sqlite4;



import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper4
{
	private DBOpenHelper dbOpenHelper;// 创建DBOpenHelper对象
	private SQLiteDatabase sqliteDatabase;// 创建SQLiteDatabase对象
 
	public DBHelper4(Context context)// 定义构造函数
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// 初始化DBOpenHelper对象
	}
 
	// 插入用户数据
	public void dbInsert(String name,String b_money,String subsidy,int month,int days,int hours,int t_money,double f_one,double tax,double salary) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
		String sql = "insert into t_gz(name,b_money,subsidy,month,days,hours,t_money,f_one,tax,salary) values (?,?,?,?,?,?,?,?,?,?)";
		// 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
		Object bindArgs[] = new Object[] { name,b_money,subsidy,month,days,hours,t_money,f_one,tax,salary };
		// 执行这条无返回值的sql语句
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Gz dbQueryOneByUsername(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_gz where name=? ";
		String[] selectionArgs = new String[] { name};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
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
			return gz;// 返回一个用户给前台
		}else{
			Gz gz2 = new Gz();
			gz2.setTax(0.0);
			return gz2;
		}
	}
	// 求出表中有多少条数据
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_gz where id>=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getInt(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
	public ArrayList<Gz> dbQueryAll() {
		ArrayList<Gz> userArrayList = new ArrayList<Gz>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_gz where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// 游标从头读到尾
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
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			Gz user = new Gz();
			user.setName(cursor.getString(cursor.getColumnIndex("name")));
			return user;// 返回一个用户给前台
		}
		return null;// 没有返回null
	}
}

