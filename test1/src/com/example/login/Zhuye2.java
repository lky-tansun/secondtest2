package com.example.login;

import com.example.bmgl.Bmgl;
import com.example.ddcx.Ddcx2;
import com.example.gzcx.Gzcx2;
import com.example.gzgl.Gzgl;
import com.example.test1.R;
import com.example.ygcx.Ygcx2;
import com.example.ygdd.Ygdd;
import com.example.yggl.Yggl;
import com.example.yhgl.Yhgl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Zhuye2 extends Activity {
	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuye2);
		PublicWay.activityList.add(this);
		btn1=(Button) findViewById(R.id.btn1);
		btn2=(Button) findViewById(R.id.btn2);
		btn3=(Button) findViewById(R.id.btn3);
		btn4=(Button) findViewById(R.id.btn4);
		btn5=(Button) findViewById(R.id.btn5);
		btn6=(Button) findViewById(R.id.btn6);
		btn7=(Button) findViewById(R.id.btn7);
		btn8=(Button) findViewById(R.id.btn8);
		btn9=(Button) findViewById(R.id.btn9);
		MyListener listener = new MyListener();
        btn1.setOnClickListener(listener);
		btn2.setOnClickListener(listener);
		btn3.setOnClickListener(listener);
		btn4.setOnClickListener(listener);
		btn5.setOnClickListener(listener);
		btn6.setOnClickListener(listener);
		btn7.setOnClickListener(listener);
		btn8.setOnClickListener(listener);
		btn9.setOnClickListener(listener);
	}
	public class MyListener implements View.OnClickListener{
		@Override
		public void onClick(View v){
			switch(v.getId()){
			case R.id.btn1:
				Intent intent1 = new Intent(Zhuye2.this,Gzcx2.class);
				startActivity(intent1);
			    break;
            case R.id.btn2:
            	Intent intent2 = new Intent(Zhuye2.this,Yggl.class);
				startActivity(intent2);
			    break;
            case R.id.btn3:
            	Intent intent3 = new Intent(Zhuye2.this,Gzgl.class);
				startActivity(intent3);
			    break;
            case R.id.btn4:
            	Intent intent4 = new Intent(Zhuye2.this,Bmgl.class);
				startActivity(intent4);
                break;
            case R.id.btn5:
            	Intent intent5 = new Intent(Zhuye2.this,Ygcx2.class);
				startActivity(intent5);
                break;
            case R.id.btn6:
            	Intent intent6 = new Intent(Zhuye2.this,Ygdd.class);
				startActivity(intent6);
                break;
            case R.id.btn7:
            	Intent intent7 = new Intent(Zhuye2.this,Ddcx2.class);
				startActivity(intent7);
                break;
			case R.id.btn8:
				Intent intent8 = new Intent(Zhuye2.this,Yhgl.class);
				startActivity(intent8);
				break;
			case R.id.btn9:
				for(int i=0;i<PublicWay.activityList.size();i++){
				if (null != PublicWay.activityList.get(i)) {
				PublicWay.activityList.get(i).finish();
			}
		}
	}
	}}
}
