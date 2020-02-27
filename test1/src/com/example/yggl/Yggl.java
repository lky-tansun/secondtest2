package com.example.yggl;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.Adapter3;
import com.example.login.PublicWay;
import com.example.login.Zhuye2;
import com.example.sqlite3.Yg;
import com.example.sqlite3.DBHelper3;
import com.example.test1.R;


public class Yggl extends Activity {    
    private Adapter3 adapter;       
    private DBHelper3 Dao;
    private TextView tv;
    private Button bt;
    private ArrayList<String> arraylist = new ArrayList<String>();
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.yggl);    
        PublicWay.activityList.add(this);
		Dao =new DBHelper3(this);
		final int size=Dao.dbGetUserSize();
		tv=(TextView)findViewById(R.id.zhuce);
		bt=(Button)findViewById(R.id.bt1);
        ListView list_view = (ListView) findViewById(R.id.list);    
    
        DBHelper3 helper=new DBHelper3(Yggl.this);
        ArrayList<Yg> List=helper.dbQueryAll();
        for(int i = 0; i < size; i++)
        {   
        	arraylist.add(List.get(i).getName());
        } 
        // 初始化数据结束    
        adapter = new Adapter3(this, arraylist);    
        list_view.setAdapter(adapter);    
        
    
        tv.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    	// TODO Auto-generated method stub
	        Intent intent = new Intent(Yggl.this,Tjyg.class);
			startActivity(intent);
	    	}
	    	});
	
        bt.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(Yggl.this,Zhuye2.class);
			startActivity(intent);
		}
		});
    }    
    
}
