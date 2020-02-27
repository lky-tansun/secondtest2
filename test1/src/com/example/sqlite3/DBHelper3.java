package com.example.sqlite3;

import java.util.ArrayList;




import com.example.sqlite3.DBOpenHelper;
import com.example.sqlite3.Yg;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper3
{
	private DBOpenHelper dbOpenHelper;// 创建DBOpenHelper对象
	private SQLiteDatabase sqliteDatabase;// 创建SQLiteDatabase对象
 
	public DBHelper3(Context context)// 定义构造函数
	{
		dbOpenHelper = new DBOpenHelper(context, null, null, 0);// 初始化DBOpenHelper对象
	}
 
	// 插入用户数据
	public void dbInsert(String name,String sex,String age,String nation,String department,String position,String date,String idnumber,String number,String marriage,String edu,String money,String subsidy) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();// 以读写方法打开数据库，不仅仅是写，getReadableDatabase()是只读
		String sql = "insert into t_yg(name,sex,age,nation,department,position,date,idnumber,number,marriage,edu,money,subsidy) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		// 传递过来的username与password分别按顺序替换上面sql语句的两个?，自动转换类型，下同，不再赘述
		Object bindArgs[] = new Object[] { name,sex,age,nation,department,position,date,idnumber,number,marriage,edu,money,subsidy };
		// 执行这条无返回值的sql语句
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	public Yg dbQueryOneByUsername(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_yg where name=? ";
		String[] selectionArgs = new String[] { name };
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
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
			return yg;// 返回一个用户给前台
		}
		return null;// 没有返回null
	}
	public Yg dbQueryOneByIdnumber(String idnumber) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_yg where idnumber=?";
		String[] selectionArgs = new String[] { idnumber};
		Cursor cursor = sqliteDatabase.rawQuery(sql, selectionArgs);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			Yg user = new Yg();
			user.setIdnumber(cursor.getString(cursor.getColumnIndex("idnumber")));
			return user;// 返回一个用户给前台
		}
		return null;// 没有返回null
	}
	// 求出表中有多少条数据
	public int dbGetUserSize() {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select count(*) from t_yg where id>=0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		if (cursor.moveToNext())// 判断Cursor中是否有数据
		{
			return cursor.getInt(0);// 返回总记录数
		}
		return 0;// 如果没有数据，则返回0
	}
 	
	public void dbDeleteUser(String name) {
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "delete from t_yg where name=?";
		Object bindArgs[] = new Object[] { name };
		sqliteDatabase.execSQL(sql, bindArgs);
	}
	// 查询所有用户
	public ArrayList<Yg> dbQueryAll() {
		ArrayList<Yg> userArrayList = new ArrayList<Yg>();
		sqliteDatabase = dbOpenHelper.getWritableDatabase();
		String sql = "select * from t_yg where id >= 0";
		Cursor cursor = sqliteDatabase.rawQuery(sql, null);
		// 游标从头读到尾
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

