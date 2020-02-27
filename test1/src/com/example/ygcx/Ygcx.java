package com.example.ygcx;

import java.util.ArrayList;
import java.util.List;

import com.example.adapter.Adapter2;
import com.example.login.PublicWay;
import com.example.login.Zhuye;
import com.example.sqlite2.Bm;
import com.example.sqlite2.DBHelper2;
import com.example.sqlite3.DBHelper3;
import com.example.sqlite3.Yg;
import com.example.test1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Ygcx extends Activity {
	private CheckBox ckb1, ckb2;
	private Button btn1, btn2;
	private Spinner sp;
	private RadioButton btn3;
	private ArrayList<String> arraylist = new ArrayList<String>();
	private ArrayList<String> arraylist2 = new ArrayList<String>();
	private String[] str;
	private String TypeResult;
	private ListView lv;
	private Adapter2 adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ygcx);
		PublicWay.activityList.add(this);
		btn1 = (Button) findViewById(R.id.login);
		btn2 = (Button) findViewById(R.id.quit);
		btn3 = (RadioButton) findViewById(R.id.male);
		sp = (Spinner) findViewById(R.id.spinner);
		ckb1 = (CheckBox) findViewById(R.id.ckb1);
		ckb2 = (CheckBox) findViewById(R.id.ckb2);
		lv = (ListView) findViewById(R.id.list);
		
		ArrayAdapter<String> adapter2;
		int Size = new DBHelper2(Ygcx.this).dbGetUserSize();
		List<Bm> list = new DBHelper2(Ygcx.this).dbQueryAll();
		for (int i = 0; i < Size; i++) {
			arraylist.add(list.get(i).getBm());
		}
		str = arraylist.toArray(new String[0]);
		adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line, str);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter2);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long id) {
				TypeResult = parent.getItemAtPosition(pos).toString();

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		btn1.setOnClickListener(new View.OnClickListener() {
			int Size = new DBHelper3(Ygcx.this).dbGetUserSize();
			
			List<Yg> list = new DBHelper3(Ygcx.this).dbQueryAll();
			String sex;

			@Override
			public void onClick(View arg0) {
				arraylist2.clear();
				if (btn3.isChecked()) {
					sex = "男";
				} else {
					sex = "女";
				}
				if (ckb1.isChecked()) {
					if (ckb2.isChecked()) {
						for (int i = 0; i < Size; i++) {
							if (list.get(i).getDepartment().equals(TypeResult)
									&& list.get(i).getSex().equals(sex)) {
								
								arraylist2.add(list.get(i).getName());
							}
						}
						
						Toast.makeText(Ygcx.this, "查询结果如下", Toast.LENGTH_SHORT)
								.show();
					} else {
						for (int i = 0; i < Size; i++) {
							if (list.get(i).getDepartment().equals(TypeResult)) {
								
								arraylist2.add(list.get(i).getName());
							}
						}
						Toast.makeText(Ygcx.this, "查询结果如下", Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					if (ckb2.isChecked()) {
						for (int i = 0; i < Size; i++) {
							if (list.get(i).getSex().equals(sex)) {
								
								arraylist2.add(list.get(i).getName());
							}
						}
						Toast.makeText(Ygcx.this, "查询结果如下", Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(Ygcx.this, "都不选我怎么查？",
								Toast.LENGTH_SHORT).show();
					}
				}
				adapter = new Adapter2(Ygcx.this,
						arraylist2);
				lv.setAdapter(adapter);
			}
		}

		);
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Ygcx.this, Zhuye.class);
				startActivity(intent);
			}
		});
	}

}