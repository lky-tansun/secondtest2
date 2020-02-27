package com.example.ygcx;

import com.example.login.PublicWay;
import com.example.sqlite3.DBHelper3;
import com.example.sqlite3.Yg;
import com.example.test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class Ygxx2 extends Activity{
     private Button btn1;
	 private TextView et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13;
	 private DBHelper3 helper2;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ygxx2);
		PublicWay.activityList.add(this);
		btn1=(Button)findViewById(R.id.no);
		et1=(TextView)findViewById(R.id.et1);
		et2=(TextView)findViewById(R.id.et2);
		et3=(TextView)findViewById(R.id.et3);
		et4=(TextView)findViewById(R.id.et4);
		et5=(TextView)findViewById(R.id.et5);
		et6=(TextView)findViewById(R.id.et6);
		et7=(TextView)findViewById(R.id.et7);
		et8=(TextView)findViewById(R.id.et8);
		et9=(TextView)findViewById(R.id.et9);
		et10=(TextView)findViewById(R.id.et10);
		et11=(TextView)findViewById(R.id.et11);
		et12=(TextView)findViewById(R.id.et12);
		et13=(TextView)findViewById(R.id.et13);
		Intent intent =getIntent();
		String name=intent.getStringExtra("name");
		helper2=new DBHelper3(this);
		Yg yg=helper2.dbQueryOneByUsername(name);
		et1.setText(yg.getName());
		et2.setText(yg.getSex());
		et3.setText(yg.getAge());
		et4.setText(yg.getNation());
		et5.setText(yg.getDepartment());
		et6.setText(yg.getPosition());
		et7.setText(yg.getDate());
		et8.setText(yg.getIdnumber());
		et9.setText(yg.getNumber());
		et10.setText(yg.getMarriage());
		et11.setText(yg.getEdu());
		et12.setText(yg.getMoney());
	    et13.setText(yg.getSubsidy());
	        btn1.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Ygxx2.this,Ygcx.class);
	    			startActivity(intent);
	    		}
	    		});
	 }}
