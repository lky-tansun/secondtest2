package com.example.bmgl;

import com.example.login.PublicWay;
import com.example.sqlite2.DBHelper2;
import com.example.sqlite2.Zw;
import com.example.test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Zwxx extends Activity{
     private Button btn1,btn2;
	 private EditText et1,et2;
	 private TextView tv1;
	 private DBHelper2 helper;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwxx);
		PublicWay.activityList.add(this);
		btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
		tv1=(TextView)findViewById(R.id.tv1);
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		helper=new DBHelper2(this);
		Intent intent =getIntent();
		String zw=intent.getStringExtra("zw");
		tv1.setText(zw);
		Zw zhiwei=helper.dbQueryOneByZw(zw);
		et1.setText(zhiwei.getGz());
		et2.setText(zhiwei.getBt());
		final String bm=zhiwei.getBm().toString();
		
		    btn1.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			String a=tv1.getText()+"";
	    			String b=et1.getText()+"";
	    			String c=et2.getText()+"";
	    			helper.dbUpdateInfo(b, c, a);
	    			Toast.makeText(Zwxx.this,"ÐÞ¸Ä³É¹¦", Toast.LENGTH_SHORT).show();
	    			Intent intent = new Intent(Zwxx.this,Bmgl2.class);
	    			intent.putExtra("bm", bm);
	    			startActivity(intent);
	    		}
	    		});
	        btn2.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Zwxx.this,Bmgl2.class);
	    	    	intent.putExtra("bm", bm);
	    			startActivity(intent);
	    		}
	    		});
	 }}
