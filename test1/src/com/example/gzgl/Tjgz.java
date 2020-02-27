package com.example.gzgl;

import java.util.Calendar;

import com.example.login.PublicWay;
import com.example.sqlite3.DBHelper3;
import com.example.sqlite3.Yg;
import com.example.sqlite4.DBHelper4;
import com.example.sqlite4.Gz;
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


public class Tjgz extends Activity{
	 private Button btn1,btn2;
	 private EditText et1,et2;
	 private TextView tv1,tv2,tv3;
	 private DBHelper3 helper3;
	 private DBHelper4 helper4;
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tjgz);
		PublicWay.activityList.add(this);
		btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
		tv1=(TextView)findViewById(R.id.tv1);
		tv2=(TextView)findViewById(R.id.tv2);
		tv3=(TextView)findViewById(R.id.tv3);
		et1=(EditText)findViewById(R.id.et1);
		et2=(EditText)findViewById(R.id.et2);
		Intent intent =getIntent();
		String name=intent.getStringExtra("name");
		helper3=new DBHelper3(this);
		helper4=new DBHelper4(this);
		Yg yg=helper3.dbQueryOneByUsername(name);
		Gz gz=helper4.dbQueryOneByUsername(name);
		tv1.setText(yg.getName());
		tv2.setText(yg.getMoney());
		tv3.setText(yg.getSubsidy());
		final double tax=gz.getTax();
		Calendar c = Calendar.getInstance();
		final int month=c.get(Calendar.MONTH)+1;
		c.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    c.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    final int maxDate = c.get(Calendar.DATE);  

	        btn1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					String name=tv1.getText()+"";
					String b_money=tv2.getText()+"";
					String subsidy=tv3.getText()+"";
					Integer days=Integer.valueOf(et1.getText()+"");      
					Integer hours=Integer.valueOf(et2.getText()+"");
					if(days>maxDate){
						Toast.makeText(Tjgz.this, month+"月共"+maxDate+"天,不得大于当前天数", Toast.LENGTH_SHORT).show();
					}
					int a;
					if(maxDate-days<=4){
						a=300;
					}else{
						a=0;
					}
					double b = 0;
					int t_money=Integer.valueOf(b_money)+Integer.valueOf(subsidy)*days+50*hours+a;
					double f_one=t_money*0.225;
					double tax2=t_money-f_one-5000;
					double tax3=tax2+tax;
					if(tax3<=36000){
						b=0.03*tax2;
					}else if(tax3>36000&&tax3<=144000){
						b=(tax2-2520)*0.1;
					}else if(tax3>144000&&tax3<=300000){
						b=(tax2-16920)*0.2;
					}else if(tax3>300000&&tax3<=420000){
						b=(tax2-31920)*0.25;
					}else if(tax3>420000&&tax3<=660000){
						b=(tax2-52920)*0.3;
					}else if(tax3>660000&&tax3<=960000){
						b=(tax2-85920)*0.35;
					}else if(tax3>960000){
						b=(tax2-181920)*0.45;
					}
					double salary=t_money-b-f_one;
					if(helper4.dbQueryOneByNameAndMonth(name, month)==null){
						helper4.dbInsert(name, b_money, subsidy, month, days, hours, t_money, f_one, tax3, salary);		
						Toast.makeText(Tjgz.this, "添加成功", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Tjgz.this,Gzgl.class);
		    			startActivity(intent);
					}else{
						Toast.makeText(Tjgz.this, "该员工已添加工资数据", Toast.LENGTH_SHORT).show();
					}
					}
				}
			);
	        btn2.setOnClickListener(new OnClickListener() {
	    		@Override
	    		public void onClick(View arg0) {
	    			Intent intent = new Intent(Tjgz.this,Gzgl.class);
	    			startActivity(intent);
	    		}
	        });
	 }
}
