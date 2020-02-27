package com.example.bmgl;

import com.example.login.PublicWay;
import com.example.sqlite2.DBHelper2;
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


public class Tjzw extends Activity{
     private Button btn1,btn2;
	 private EditText et1,et2,et3;
	 private TextView tv1;
	 private DBHelper2 helper;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tjzw);
		PublicWay.activityList.add(this);
		btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
		tv1=(TextView)findViewById(R.id.tv1);
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		et3=(EditText)findViewById(R.id.et3);
		helper=new DBHelper2(this);
		Intent intent =getIntent();
		String bm=intent.getStringExtra("bm");
		tv1.setText(bm);
		
		    btn1.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			String a=tv1.getText()+"";
	    			String b=et1.getText()+"";
	    			String c=et2.getText()+"";
	    			String d=et3.getText()+"";
	    			if(b==""){
	    				Toast.makeText(Tjzw.this,"职位不得为空", Toast.LENGTH_SHORT).show();
	    			}else if(c==""){
	    				Toast.makeText(Tjzw.this,"工资不得为空", Toast.LENGTH_SHORT).show();
	    			}else if(d==""){
	    				Toast.makeText(Tjzw.this,"补贴不得为空", Toast.LENGTH_SHORT).show();
	    			}else{
	    				if(helper.dbQueryOneByZw(b) == null){
		    				helper.dbInsert2(a,b,c,d);
			    			Toast.makeText(Tjzw.this,"添加成功", Toast.LENGTH_SHORT).show();
			    			Intent intent = new Intent(Tjzw.this,Bmgl2.class);
			    			intent.putExtra("bm", tv1.getText().toString());
			    			startActivity(intent);
		    			}else{
		    				Toast.makeText(Tjzw.this,"已存在该职位", Toast.LENGTH_SHORT).show();
		    			}
	    			}
	    		}
	    		});
	        btn2.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Tjzw.this,Bmgl2.class);
	    	    	intent.putExtra("bm", tv1.getText().toString());
	    			startActivity(intent);
	    		}
	    		});
	 }}
