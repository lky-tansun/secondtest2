package com.example.yhgl;

import java.util.ArrayList;

import com.example.adapter.Adapter;
import com.example.login.PublicWay;
import com.example.login.Zhuye2;
import com.example.sqlite1.HR;
import com.example.sqlite1.DBHelper;
import com.example.test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Yhgl extends Activity {    
    private Adapter adapter;       
    private DBHelper Dao;
    private TextView tv;
    private Button bt;
    private ArrayList<String> arraylist = new ArrayList<String>();
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.yhgl);    
        PublicWay.activityList.add(this);
		Dao =new DBHelper(this);
		final int size=Dao.dbGetUserSize();
		tv=(TextView)findViewById(R.id.zhuce);
		bt=(Button)findViewById(R.id.bt1);
        ListView list_view = (ListView) findViewById(R.id.list);    
    
        DBHelper helper=new DBHelper(Yhgl.this);
        ArrayList<HR> List=helper.dbQueryAll();
        for(int i = 0; i < size; i++)
        {   
        	arraylist.add(List.get(i).getUsername());
        } 
        // 初始化数据结束    
        adapter = new Adapter(this, arraylist);    
        list_view.setAdapter(adapter);    
        
    
        tv.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    	// TODO Auto-generated method stub
	        Intent intent = new Intent(Yhgl.this,Tjyh.class);
			startActivity(intent);
	    	}
	    	});
	
        bt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(Yhgl.this,Zhuye2.class);
			startActivity(intent);
		}
		});
    }    
    
}