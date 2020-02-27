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
import android.widget.Toast;


public class Tjbm extends Activity{
     private Button btn1,btn2;
	 private EditText et1;
	 private DBHelper2 helper;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tjbm);
		PublicWay.activityList.add(this);
		btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
		et1=(EditText)findViewById(R.id.et1);
		helper=new DBHelper2(this);
		
		    btn1.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			String a=et1.getText()+"";
	    			if(a==""||a.equals(null)){
	    				Toast.makeText(Tjbm.this,"����Ϊ��", Toast.LENGTH_SHORT).show();
	    			}else{
	    				if (helper.dbQueryOneByUsername(a) == null){
		    				helper.dbInsert(a);
			    			Toast.makeText(Tjbm.this,"��ӳɹ�", Toast.LENGTH_SHORT).show();
			    			Intent intent = new Intent(Tjbm.this,Bmgl.class);
			    			startActivity(intent);
		    			}else{
		    				Toast.makeText(Tjbm.this,"�Ѵ��ڸò���", Toast.LENGTH_SHORT).show();
		    			}
	    			}
	    		}
	    		});
	        btn2.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Tjbm.this,Bmgl.class);
	    			startActivity(intent);
	    		}
	    		});
	 }}
