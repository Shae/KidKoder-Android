package com.klusman.kidkoder;


import java.io.File;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import com.parse.Parse;


public class MainActivity extends Activity {

	private final int VIB_NOTE_ID = 1;
	String appID;
	String appKey;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	setContentView(R.layout.activity_main);
      	
      	File f = new File("/data/data/com.klusman.kidkoder/shared_prefs/myprefs.xml");
      			if (f.exists()){
      			    Log.d("TAG", "SharedPreferences myprefs : exist");
      				loadSettings();
      			
      			}else{
      			    Log.d("TAG", "Setup default preferences");
      			}
      	
      	
        Parse.initialize(this, "sF8m2jJo7c3kQoenTq9UG67Rk3pnPEw7prrf4ZfR", "OnuGanjP6xKwQV7JOUC6wUMsmGiUlbxhyitxHS1P"); 
		
		
		Button kidsBtn = (Button)findViewById(R.id.btnChildren);
		kidsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ChildList.class);
		        startActivity(intent);
			}
		});
		
		Button alrgBtn = (Button)findViewById(R.id.btnAllergies);
		alrgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AllergyList.class);
		        startActivity(intent);
			}
		});
		
		Button QLinksBtn = (Button)findViewById(R.id.btnQlinks);
		QLinksBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, QuickLinksActivity.class);
		        startActivity(intent);
			}
		});
		
		Button setBtn = (Button)findViewById(R.id.btnSettings);
		setBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
		        startActivity(intent);
			}
		});
		
		
		
		
		
	}// End onCreate

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}// END onCreateOptionsMenu



	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
			appID = prefs.getString("APP_ID", "");
			appKey = prefs.getString("APP_KEY", "");
			
			Log.i("IDnum", appID);
			Log.i("KEY", appKey);
	}  //  END loadSettings
	
	public void notifyMe(){  
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification note = new Notification();
		note.defaults = Notification.DEFAULT_VIBRATE;
		nm.notify(VIB_NOTE_ID, note);
		
	}
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(MainActivity.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
}
