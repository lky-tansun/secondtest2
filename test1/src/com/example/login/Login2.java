package com.example.login;

import com.example.test1.R;
import com.example.sqlite1.HR;
import com.example.sqlite1.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login2 extends Activity implements OnClickListener{
	Button btn1,btn2;
	TextView tv1;
	EditText e1,e2;
	private DBHelper userDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login2);
        findView();       
	}
	private void findView() {
		userDao = new DBHelper(this);
		btn1=(Button) findViewById(R.id.login);
		btn2=(Button) findViewById(R.id.quit);
		tv1=(TextView) findViewById(R.id.ygms);
	    btn1.setOnClickListener(this);
	    e1=(EditText) findViewById(R.id.name);
		e2=(EditText) findViewById(R.id.password);
		PublicWay.activityList.add(this);
		btn2.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    	// TODO Auto-generated method stub
	    		for(int i=0;i<PublicWay.activityList.size();i++){
					if (null != PublicWay.activityList.get(i)) {
					PublicWay.activityList.get(i).finish();
					}}}
	    	});
		tv1.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    	// TODO Auto-generated method stub
	        Intent intent = new Intent(Login2.this,Login.class);
			startActivity(intent);
	    	}
	    	});
	}
	   public void onClick(View v) {
	   
		    String username = e1.getText()+"";
			String password = e2.getText()+"";
			if (username.equals(null) || username == ""
					|| password.equals(null) || password == "") {
				Toast.makeText(Login2.this, "用户名或密码不得为空！",
						Toast.LENGTH_SHORT).show();
			} else {
				HR user = userDao.dbQueryOneByUsername(username);
				if (userDao.dbQueryOneByUsername(username) == null) {
					Toast.makeText(Login2.this, "此用户不存在！",
							Toast.LENGTH_SHORT).show();
				} else {
					if (!user.getPassword().equals(password)) {
						Toast.makeText(Login2.this, "密码错误！",
								Toast.LENGTH_SHORT).show();
					} else {
						Intent intent = new Intent(Login2.this,Zhuye2.class);
						startActivity(intent);
					}
				}
			}
			
    }
	   
}