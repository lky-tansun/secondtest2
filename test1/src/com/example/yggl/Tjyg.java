package com.example.yggl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.login.PublicWay;
import com.example.login.Zhuye2;
import com.example.sqlite2.Bm;
import com.example.sqlite2.DBHelper2;
import com.example.sqlite2.Zw;
import com.example.sqlite3.DBHelper3;
import com.example.test1.R;






import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



public class Tjyg extends Activity{
     private Button btn1,btn2;
     private RadioButton btn3,btn4,btn5,btn6;
	 private EditText et1,et2,et3,et4,et5;
	 private TextView tv1,tv2;
	 private Spinner bm,zw;
	 DBHelper2 db;
	 private String[] str,str2;
	 private String bmname,zwname;
	 private TextView in_manage_date;
	 private DBHelper3 helper2;
	 private ArrayList<String> arraylist = new ArrayList<String>();
	 @SuppressLint("SimpleDateFormat") @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tjyg);
		PublicWay.activityList.add(this);
		in_manage_date = (TextView)findViewById(R.id.dateview);
		btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
		btn3=(RadioButton)findViewById(R.id.male);
		btn4=(RadioButton)findViewById(R.id.weihun);
		btn5=(RadioButton)findViewById(R.id.dazhuan);
		btn6=(RadioButton)findViewById(R.id.benke);
		tv1=(TextView)findViewById(R.id.tv1);
		tv2=(TextView)findViewById(R.id.tv2);
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		et3=(EditText)findViewById(R.id.et3);
		et4=(EditText)findViewById(R.id.et4);
		et5=(EditText)findViewById(R.id.et5);
		bm=(Spinner) findViewById(R.id.bm);
		zw=(Spinner) findViewById(R.id.zw);
		helper2 =new DBHelper3(this);
		db=new DBHelper2(this);
		if(in_manage_date.getText().equals("")){
	    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	//获取当前时间
	    	Date date = new Date(System.currentTimeMillis());
	    	in_manage_date.setText(simpleDateFormat.format(date));
	     }
		ArrayAdapter<String> adapter2;
		int Size =new DBHelper2(Tjyg.this).dbGetUserSize();
		List<Bm> list=new DBHelper2(Tjyg.this).dbQueryAll();
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
	   			 int Size2=new DBHelper2(Tjyg.this).dbGetUserSize2(bmname);
	   			 List<Zw> list2 = db.dbSelectzw(bmname);
	   			 for(int i=0;i<Size2;i++) {
	   				arraylist2.add(list2.get(i).getZw());
	   		    	}
	   			 str2 = arraylist2.toArray(new String[0]);
	   			 zwAdapter = new ArrayAdapter<String>(Tjyg.this,android.R.layout.simple_dropdown_item_1line,str2);
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
	        		tv1.setText("");
	        		tv2.setText("");
	        		Spinner spzw = (Spinner) parent;
	        		zwname=(String) spzw.getItemAtPosition(pos);
	        		Zw zhiwei = db.dbQueryOneByZw(zwname);
	        		tv1.setText(zhiwei.getGz());
	        		tv2.setText(zhiwei.getBt());
	        	}
	        	@Override
	        	public void onNothingSelected(AdapterView<?> arg0){
	        		
	        	}
			});
	        in_manage_date.setOnClickListener(new OnClickListener() {
		           @Override
		           public void onClick(View v) {
		               AlertDialog.Builder localBuilder = new AlertDialog.Builder(Tjyg.this);
		               localBuilder.setTitle("选择时间");
		               //
		               final LinearLayout layout_alert= (LinearLayout) getLayoutInflater().inflate(R.layout.date, null);
		               localBuilder.setView(layout_alert);
		               localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
		               {
		                   public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
		                   {
		                       DatePicker datepicker1= (DatePicker) layout_alert.findViewById(R.id.in_datepicker);
		                       int y=datepicker1.getYear();
		                       int m=datepicker1.getMonth()+1;
		                       int d=datepicker1.getDayOfMonth();
		                       System.out.println("y:"+y+" m:"+m+" d:"+d);
		                       in_manage_date.setText(y+"-"+m+"-"+d); //  获取时间
		                   }
		               }).setNegativeButton("取消", new DialogInterface.OnClickListener()
		               {
		                   public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
		                   {
		 
		                   }
		               }).create().show();
		           }
		       });
	        btn1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String name = et1.getText().toString()+"";
					String sex;
					String age = et2.getText().toString()+"";
					String nation = et3.getText().toString()+"";
					String department = bmname;
					String position = zwname;
					String date = in_manage_date.getText().toString()+"";
					String idnumber =et4.getText().toString()+"";
					String number=et5.getText().toString()+"";
					String marriage;
					String edu;
					String money=tv1.getText().toString()+"";
					String subsidy=tv2.getText().toString()+"";
					if(btn3.isChecked()){sex="男";}else{sex="女";}
					if(btn4.isChecked()){marriage="未婚";}else{marriage="已婚";}
					if(btn5.isChecked()){edu="大专及以下";}else{if(btn6.isChecked()){edu="本科";}else{edu="硕士及以上";}}
					if (name.equals(null) || name == "") {
						Toast.makeText(Tjyg.this, "姓名不得为空！",
								Toast.LENGTH_SHORT).show();
					} else {if (age.equals(null)||age.length()!=2) {
							Toast.makeText(Tjyg.this, "年龄输入有误，请重新填写！",
									Toast.LENGTH_SHORT).show();
						} else {if (nation.equals(null) || nation == "") {
								Toast.makeText(Tjyg.this, "民族不得为空！",
										Toast.LENGTH_SHORT).show();
							} else {if(position.equals(null)||position==""){							
								Toast.makeText(Tjyg.this, "职位不得为空！",
										Toast.LENGTH_SHORT).show();
							   }else{if(idnumber.length()!=18){
								   Toast.makeText(Tjyg.this, "身份证号码输入有误，请重新填写！！",
											Toast.LENGTH_SHORT).show();
							   }else{if(number.length()!=11){
								   Toast.makeText(Tjyg.this, "手机号码输入有误，请重新填写！！",
											Toast.LENGTH_SHORT).show();
							   }else{if (helper2.dbQueryOneByIdnumber(idnumber) == null) 
							              {if(helper2.dbQueryOneByUsername(name)==null){
							            	  helper2.dbInsert(name,sex,age,nation,department,position,date,idnumber,number,marriage,edu,money,subsidy);
												Toast.makeText(Tjyg.this,"添加成功！", Toast.LENGTH_SHORT).show();
												Intent intent = new Intent(Tjyg.this,Zhuye2.class);
								    			startActivity(intent);
							              }else{
							            	  Toast.makeText(Tjyg.this,"名字重复，请加数字！例如：张三2", Toast.LENGTH_SHORT).show();
							              }
										
									} else {							
										Toast.makeText(Tjyg.this, "该员工已存在",
												Toast.LENGTH_SHORT).show();
									}
							   }
								   
							   }
								   
							   }
								   
							   }
								
							}
						}
					}
	 
				}
			);
	        btn2.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Tjyg.this,Yggl.class);
	    			startActivity(intent);
	    		}
	        });
	 }
	 
	 }
