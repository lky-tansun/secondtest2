package com.example.bmgl;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.Adapter5;
import com.example.login.PublicWay;
import com.example.sqlite2.DBHelper2;
import com.example.sqlite2.Zw;
import com.example.test1.R;

public class Bmgl2 extends Activity {    
    private Adapter5 adapter;       
    private Button btn1,btn2;
    private TextView tv;
    private DBHelper2 Dao;
    private ArrayList<String> arraylist = new ArrayList<String>();
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.bmgl2);    
        PublicWay.activityList.add(this);
		btn1=(Button)findViewById(R.id.btn1);
		btn2=(Button)findViewById(R.id.btn3);
        ListView list_view = (ListView) findViewById(R.id.list);    
        tv=(TextView)findViewById(R.id.tv1);
        DBHelper2 helper=new DBHelper2(Bmgl2.this);
		Intent intent =getIntent();
		String bm=intent.getStringExtra("bm");
        tv.setText(bm);
        Dao =new DBHelper2(this);
        final int size=Dao.dbGetUserSize2(tv.getText().toString());
        ArrayList<Zw> List=helper.dbSelectzw(tv.getText().toString());
        for(int i = 0; i < size; i++)
        {   
        		arraylist.add(List.get(i).getZw());
        } 
        // 初始化数据结束    
        adapter = new Adapter5(this, arraylist);    
        list_view.setAdapter(adapter);    
        
        btn1.setOnClickListener(new OnClickListener() {
    		@Override
    		public void onClick(View arg0) {
    			Intent intent = new Intent(Bmgl2.this,Tjzw.class);
    	    	intent.putExtra("bm", tv.getText().toString());
    	        startActivity(intent);
    		}
    		});
	
        btn2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(Bmgl2.this,Bmgl.class);
			startActivity(intent);
		}
		});
    }    
    
}
