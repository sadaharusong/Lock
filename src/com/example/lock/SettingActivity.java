package com.example.lock;

import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SettingActivity extends Activity {
	
	List<Integer> passList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		final GestureLock lock = (GestureLock) findViewById(R.id.view);
		Button btn_reset = (Button) findViewById(R.id.btn_reset);
		Button btn_save = (Button) findViewById(R.id.btn_save);
		
		lock.setOnDrawFinishedListener(new GestureLock.OnDrawFinishedListener() {
			
			@Override
			public boolean OnDrawFinished(List<Integer> passList) {
				// TODO Auto-generated method stub
				if (passList.size() < 3) {
					Toast.makeText(SettingActivity.this, "密码不能这么短啊~~", Toast.LENGTH_SHORT).show();
					return false;
				}
				else {
					SettingActivity.this.passList = passList ;
					return true;
				}
			}
		});
		btn_reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				 lock.resetPoints();
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (passList != null) {
					StringBuilder sb = new StringBuilder();
					for(Integer i : passList)
					{
						sb.append(i);
					}
					SharedPreferences sp = SettingActivity.this.getSharedPreferences("password", SettingActivity.this.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("password", sb.toString());
					editor.commit();
					
					Toast.makeText(SettingActivity.this, "保存成功~~~", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
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
