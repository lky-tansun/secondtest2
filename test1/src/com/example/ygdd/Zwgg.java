package com.example.ygdd;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.login.PublicWay;
import com.example.sqlite2.Bm;
import com.example.sqlite2.DBHelper2;
import com.example.sqlite2.Zw;
import com.example.sqlite3.DBHelper3;
import com.example.sqlite3.Yg;
import com.example.sqlite5.DBHelper5;
import com.example.test1.R;

@SuppressLint("SimpleDateFormat") public class Zwgg extends Activity{
	private TextView tv1,tv2,tv3;
	private EditText et1;
	private Spinner bm,zw;
	private Button btn1,btn2;
	DBHelper2 db;
	private String bmname,zwname,gz,bt;
	private DBHelper3 helper3;
	private DBHelper5 helper5;
	private String[] str,str2;
	private ArrayList<String> arraylist = new ArrayList<String>();
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zwgg);
		PublicWay.activityList.add(this);
		tv1=(TextView)findViewById(R.id.tv1);
		tv2=(TextView)findViewById(R.id.tv2);
		tv3=(TextView)findViewById(R.id.tv3);
		et1=(EditText)findViewById(R.id.et1);
		bm=(Spinner)findViewById(R.id.spinner);
		zw=(Spinner)findViewById(R.id.spinner2);
		btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
		db=new DBHelper2(this);
		Intent intent =getIntent();
		final String name=intent.getStringExtra("name");
		helper3=new DBHelper3(this);
		helper5=new DBHelper5(this);
		Yg yg=helper3.dbQueryOneByUsername(name);
		tv1.setText(yg.getName());
		tv2.setText(yg.getDepartment());
		tv3.setText(yg.getPosition());
		ArrayAdapter<String> adapter2;
		int Size =new DBHelper2(Zwgg.this).dbGetUserSize();
		List<Bm> list=new DBHelper2(Zwgg.this).dbQueryAll();
		for(int i=0;i<Size;i++) {
			arraylist.add(list.get(i).getBm());
		}
		str = arraylist.toArray(new String[0]);
	        adapter2=new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, str);
	        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        bm.setAdapter(adapter2);
	        bm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        	@Override
	        	public void onItemSelected(AdapterView<?> parent,View arg1,int pos,long id){
	        		ArrayList<String> arraylist2 = new ArrayList<String>();
	        		Spinner spbm = (Spinner) parent;
	        		bmname = (String) spbm.getItemAtPosition(pos);
	   			 ArrayAdapter<String> zwAdapter;
	   			 int Size2=new DBHelper2(Zwgg.this).dbGetUserSize2(bmname);
	   			 List<Zw> list2 = db.dbSelectzw(bmname);
	   			 for(int i=0;i<Size2;i++) {
	   				arraylist2.add(list2.get(i).getZw());
	   		    	}
	   			 str2 = arraylist2.toArray(new String[0]);
	   			 zwAdapter = new ArrayAdapter<String>(Zwgg.this,android.R.layout.simple_dropdown_item_1line,str2);
	   			 zwAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	   			 zw.setAdapter(zwAdapter);
	   			 
	        	}
	        	@Override
	        	public void onNothingSelected(AdapterView<?> arg0){
	        		
	        	}
			});
	        zw.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        	@Override
	        	public void onItemSelected(AdapterView<?> parent,View arg1,int pos,long id){
	        		Spinner spzw = (Spinner) parent;
	        		zwname=(String) spzw.getItemAtPosition(pos);
	        		Zw zhiwei = db.dbQueryOneByZw(zwname);
	        		gz=zhiwei.getGz();
	        		bt=zhiwei.getBt();
	        	}
	        	@Override
	        	public void onNothingSelected(AdapterView<?> arg0){
	        		
	        	}
			});
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	//获取当前时间
	    	Date date = new Date(System.currentTimeMillis());
	    	final String time=simpleDateFormat.format(date);
	        btn1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if(et1.getText().equals(null)||et1.getText()+""==""){
						Toast.makeText(Zwgg.this,"请输入正确操作人姓名！", Toast.LENGTH_SHORT).show();
					}else{
						helper3.dbUpdateInfo(bmname, zwname, gz, bt, name);
						helper5.dbInsert(name, tv2.getText()+"", tv3.getText()+"", bmname, zwname, time, et1.getText()+"");
						Toast.makeText(Zwgg.this,"修改成功！", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Zwgg.this,Ygdd.class);
		    			startActivity(intent);	
					}
					}
				}
			);
	        btn2.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Zwgg.this,Ygdd.class);
	    			startActivity(intent);
	    		}
	        });
	 }
	}
