package com.example.login;



import com.example.test1.R;
import com.example.sqlite.User;
import com.example.sqlite.DBHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	Button btn1,btn2;
	TextView tv1,tv2;
	EditText e1,e2;
	private DBHelper userDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
        findView();       
	}
	private void findView() {
		userDao = new DBHelper(this);
		btn1=(Button) findViewById(R.id.login);
		btn2=(Button) findViewById(R.id.quit);
		tv1=(TextView) findViewById(R.id.zhuce);
		tv2=(TextView) findViewById(R.id.glms);
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
	        Intent intent = new Intent(Login.this,Zhuce.class);
			startActivity(intent);
	    	}
	    	});
		tv2.setOnClickListener(new Button.OnClickListener(){
	    	@Override
	    	public void onClick(View v) {
	    	// TODO Auto-generated method stub
	        Intent intent = new Intent(Login.this,Login2.class);
			startActivity(intent);
	    	}
	    	});
	}
	   public void onClick(View v) {
	   
		    String username = e1.getText()+"";
			String password = e2.getText()+"";
			if (username.equals(null) || username == ""
					|| password.equals(null) || password == "") {
				Toast.makeText(Login.this, "用户名或密码不得为空！",
						Toast.LENGTH_SHORT).show();
			} else {
				User user = userDao.dbQueryOneByUsername(username);
				if (userDao.dbQueryOneByUsername(username) == null) {
					Toast.makeText(Login.this, "此用户不存在！",
							Toast.LENGTH_SHORT).show();
				} else {
					if (!user.getPassword().equals(password)) {
						Toast.makeText(Login.this, "密码错误！",
								Toast.LENGTH_SHORT).show();
					} else {
						Intent intent = new Intent(Login.this,Zhuye.class);
						startActivity(intent);
					}
				}
			}
			
    }
	   
}