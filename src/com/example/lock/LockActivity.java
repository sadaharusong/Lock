package com.example.lock;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class LockActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lock);
		SharedPreferences sp = getSharedPreferences("password", this.MODE_PRIVATE);
		final String password = sp.getString("password", "");
		GestureLock lock = (GestureLock) findViewById(R.id.LockView);
		
		lock.setOnDrawFinishedListener(new GestureLock.OnDrawFinishedListener() {
			
			@Override
			public boolean OnDrawFinished(List<Integer> passList) {
				// TODO Auto-generated method stub
				StringBuilder sb = new StringBuilder();
				for(Integer i : passList)
				{
					sb.append(i);
				}
				if(sb.toString().equals(password))  //如果密码正确！！！！！！！！！！！！做后续操作
				{
					Toast.makeText(LockActivity.this, "密码正确", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LockActivity.this , TianqiActivity.class);
					startActivity(intent);
					return true;
				}else {
					Toast.makeText(LockActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
					return false;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lock, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
