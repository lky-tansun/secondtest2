package com.example.bmgl;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.adapter.Adapter4;
import com.example.login.PublicWay;
import com.example.login.Zhuye2;
import com.example.sqlite2.Bm;
import com.example.sqlite2.DBHelper2;
import com.example.test1.R;

public class Bmgl extends Activity {    
    private Adapter4 adapter;       
    private DBHelper2 Dao;
    private Button bt1,bt2;
    private ArrayList<String> arraylist = new ArrayList<String>();
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.bmgl);    
        PublicWay.activityList.add(this);
		Dao =new DBHelper2(this);
		final int size=Dao.dbGetUserSize();
		bt1=(Button)findViewById(R.id.btn1);
		bt2=(Button)findViewById(R.id.btn3);
        ListView list_view = (ListView) findViewById(R.id.list);    
        DBHelper2 helper=new DBHelper2(Bmgl.this);
        ArrayList<Bm> List=helper.dbQueryAll();
        for(int i = 0; i < size; i++)
        {   
        		arraylist.add(List.get(i).getBm());
        } 
        // 初始化数据结束    
        adapter = new Adapter4(this, arraylist);    
        list_view.setAdapter(adapter); 
        bt1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Bmgl.this,Tjbm.class);
				startActivity(intent);
			}
		});
        bt2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Bmgl.this,Zhuye2.class);
				startActivity(intent);
			}
			});
    }
    
}