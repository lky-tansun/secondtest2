package com.example.ddcx;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.login.PublicWay;
import com.example.login.Zhuye;
import com.example.sqlite3.DBHelper3;
import com.example.sqlite3.Yg;
import com.example.sqlite5.DBHelper5;
import com.example.sqlite5.Dd;
import com.example.test1.R;

public class Ddcx extends Activity {    
    private Button btn1,btn2;
    private CheckBox ckb1;
    private Spinner sp1;
	private String[] str;
	private String name;
	private ListView lv;
	private ArrayList<String> arraylist = new ArrayList<String>();
	private ArrayList<String> arraylist2 = new ArrayList<String>();
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.ddcx);    
        PublicWay.activityList.add(this);
        btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
        ckb1=(CheckBox)findViewById(R.id.ckb1);
        sp1=(Spinner)findViewById(R.id.spinner);
        lv = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter;
        int Size = new DBHelper3(Ddcx.this).dbGetUserSize();
        List<Yg> list=new DBHelper3(Ddcx.this).dbQueryAll();
        for (int i = 0; i < Size; i++) {
			arraylist.add(list.get(i).getName());
		}
        str = arraylist.toArray(new String[0]);
        adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, str);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter);
		sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
			    name = parent.getItemAtPosition(pos).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		btn1.setOnClickListener(new View.OnClickListener() {
			List<Dd> list2=new DBHelper5(Ddcx.this).dbQueryAll();
			int Size2 = new DBHelper5(Ddcx.this).dbGetUserSize();
			@Override
			public void onClick(View arg0) {
				arraylist2.clear();
				if (ckb1.isChecked()) {
						for (int i = 0; i < Size2; i++) {
							if (list2.get(i).getName().equals(name)){
					arraylist2.add(
						"姓名："+list2.get(i).getName()+"\n"+"原先部门："+list2.get(i).getOld_d()+"\n"+
						"原先岗位："+list2.get(i).getOld_p()+"\n"+"现任部门："+list2.get(i).getNew_d()+"\n"+
						"现任职位："+list2.get(i).getNew_p()+"\n"+"操作时间："+list2.get(i).getTime()+"\n"+
						"操作人："+list2.get(i).getYh()+"\n");
							}
						}
					} else {
						for (int i = 0; i < Size2; i++) {
							arraylist2.add(
									"姓名："+list2.get(i).getName()+"\n"+"原先部门："+list2.get(i).getOld_d()+"\n"+
									"原先岗位："+list2.get(i).getOld_p()+"\n"+"现任部门："+list2.get(i).getNew_d()+"\n"+
									"现任职位："+list2.get(i).getNew_p()+"\n"+"操作时间："+list2.get(i).getTime()+"\n"+
									"操作人："+list2.get(i).getYh()+"\n");
						}
					}
				Toast.makeText(Ddcx.this, "查询结果如下", Toast.LENGTH_SHORT).show();
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(Ddcx.this,
						android.R.layout.simple_list_item_1,arraylist2);
				lv.setAdapter(adapter);
			}
		}

		);
        
        btn2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(Ddcx.this,Zhuye.class);
			startActivity(intent);
		}
		});
    } 
}