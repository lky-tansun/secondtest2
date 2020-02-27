package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sqlite.DBHelper;
import com.example.test1.R;

public class Zhuce extends Activity{
	private EditText et1,et5,et6;
	private Button btn2,btn3;
	private DBHelper userDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce);
		PublicWay.activityList.add(this);
		userDao = new DBHelper(this);
		et1 = (EditText) findViewById(R.id.et1);
		et5 = (EditText) findViewById(R.id.et5);
		et6 = (EditText) findViewById(R.id.et6);
		btn2=(Button) findViewById(R.id.zhuce);
		btn3=(Button) findViewById(R.id.quit);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String username = et1.getText()+"";
				String password = et5.getText()+"";
				if (username.equals(null) || username == ""
						|| password.equals(null) || password == "") {
					Toast.makeText(Zhuce.this, "用户名或密码不得为空！",
							Toast.LENGTH_SHORT).show();
				} else {
					String confirmPwd = et6.getText() + "";
					if (!password.equals(confirmPwd)) {
						Toast.makeText(Zhuce.this, "两次输入密码不一致！",
								Toast.LENGTH_SHORT).show();
					} else {
						if (userDao.dbQueryOneByUsername(username) == null) {
							userDao.dbInsert(username, password);
							Toast.makeText(
									Zhuce.this,
									"注册成功！用户名：" + username + "，密码：" + password
											+ "，请牢记！", Toast.LENGTH_LONG)
									.show();
							Intent intent = new Intent(Zhuce.this,Login.class);
							startActivity(intent);
						} else {							
							Toast.makeText(Zhuce.this, "该用户已被注册",
									Toast.LENGTH_SHORT).show();
						}
					}
				}
 
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Zhuce.this,Login.class);
				startActivity(intent);
			}
			});
}
}