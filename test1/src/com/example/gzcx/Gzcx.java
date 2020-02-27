package com.example.gzcx;



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
import com.example.sqlite4.DBHelper4;
import com.example.sqlite4.Gz;
import com.example.test1.R;

public class Gzcx extends Activity {    
    private Button btn1,btn2;
    private CheckBox ckb1,ckb2;
    private Spinner sp1,sp2;
	private String[] str;
	private String name;
	private ListView lv;
	int month;
	private ArrayList<String> arraylist = new ArrayList<String>();
	private ArrayList<String> arraylist2 = new ArrayList<String>();
    @Override    
    public void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.gzcx);    
        PublicWay.activityList.add(this);
        btn1=(Button)findViewById(R.id.yes);
		btn2=(Button)findViewById(R.id.no);
        ckb1=(CheckBox)findViewById(R.id.ckb1);
        ckb2=(CheckBox)findViewById(R.id.ckb2);
        sp1=(Spinner)findViewById(R.id.spinner);
        sp2=(Spinner)findViewById(R.id.spinner2);
        lv = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter;
        final int Size = new DBHelper4(Gzcx.this).dbGetUserSize();
        List<Gz> list=new DBHelper4(Gzcx.this).dbQueryAll();
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
		sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				month = Integer.parseInt(parent.getItemAtPosition(pos).toString());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		btn1.setOnClickListener(new View.OnClickListener() {
			List<Gz> list2=new DBHelper4(Gzcx.this).dbQueryAll();
			@Override
			public void onClick(View arg0) {
				arraylist2.clear();
				if (ckb1.isChecked()) {
					if (ckb2.isChecked()) {
						for (int i = 0; i < Size; i++) {
							if (list2.get(i).getName().equals(name)&& list2.get(i).getMonth()==month){
					arraylist2.add(
						"姓名："+list2.get(i).getName()+"\n"+"基本工资："+list2.get(i).getB_money()+"\n"+
						"岗位补贴："+list2.get(i).getSubsidy()+"\n"+"月份："+list2.get(i).getMonth()+"\n"+
						"工作天数："+list2.get(i).getDays()+"\n"+"加班时数："+list2.get(i).getHours()+"\n"+
						"总工资："+list2.get(i).getT_money()+"\n"+"五险一金："+list2.get(i).getF_one()+"\n"+
						"累计预扣预缴应纳税所得额："+list2.get(i).getTax()+"\n"+"实发工资："+list2.get(i).getSalary()+"\n");
							}
						}
						
						Toast.makeText(Gzcx.this, "查询结果如下", Toast.LENGTH_SHORT)
								.show();
					} else {
						for (int i = 0; i < Size; i++) {
							if (list2.get(i).getName().equals(name)) {
								arraylist2.add(
										"姓名："+list2.get(i).getName()+"\n"+"基本工资："+list2.get(i).getB_money()+"\n"+
										"岗位补贴："+list2.get(i).getSubsidy()+"\n"+"月份："+list2.get(i).getMonth()+"\n"+
										"工作天数："+list2.get(i).getDays()+"\n"+"加班时数："+list2.get(i).getHours()+"\n"+
										"总工资："+list2.get(i).getT_money()+"\n"+"五险一金："+list2.get(i).getF_one()+"\n"+
										"累计预扣预缴应纳税所得额："+list2.get(i).getTax()+"\n"+"实发工资："+list2.get(i).getSalary()+"\n");
							}
						}
						Toast.makeText(Gzcx.this, "查询结果如下", Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					if (ckb2.isChecked()) {
						for (int i = 0; i < Size; i++) {
							if (list2.get(i).getMonth()==month) {
								arraylist2.add(
										"姓名："+list2.get(i).getName()+"\n"+"基本工资："+list2.get(i).getB_money()+"\n"+
										"岗位补贴："+list2.get(i).getSubsidy()+"\n"+"月份："+list2.get(i).getMonth()+"\n"+
										"工作天数："+list2.get(i).getDays()+"\n"+"加班时数："+list2.get(i).getHours()+"\n"+
										"总工资："+list2.get(i).getT_money()+"\n"+"五险一金："+list2.get(i).getF_one()+"\n"+
										"累计预扣预缴应纳税所得额："+list2.get(i).getTax()+"\n"+"实发工资："+list2.get(i).getSalary()+"\n");
							}
						}
						Toast.makeText(Gzcx.this, "查询结果如下", Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(Gzcx.this, "都不选我怎么查？",
								Toast.LENGTH_SHORT).show();
					}
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(Gzcx.this,
						android.R.layout.simple_list_item_1,arraylist2);
				lv.setAdapter(adapter);
			}
		}

		);
        
        btn2.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(Gzcx.this,Zhuye.class);
			startActivity(intent);
		}
		});
    }    
    
}
