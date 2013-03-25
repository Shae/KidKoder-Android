package com.klusman.kidkoder;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
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
//i<have>noidea=itryed(ILoveYouMore)!!!!!!  // Code written by Andrea A.K.A the Dark Haired Overlord
//Leaving it in so she thinks shes cool LOL.  Think of it like an Easter Egg
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
      	
      	if(isNetworkConnectionAvailable() == true){
      		onCreateIfConnected();
      		Log.i("CONNECTION", "Network Connection Detected");
      	}else{
      		notifyMe();
      		Log.i("CONNECTION", "Network Connection NOT Detected");
      	}
      	
      	
	
	}// End onCreate

	private void onCreateIfConnected(){
		settingsCheck();

      	
        //Parse.initialize(this, "sF8m2jJo7c3kQoenTq9UG67Rk3pnPEw7prrf4ZfR", "OnuGanjP6xKwQV7JOUC6wUMsmGiUlbxhyitxHS1P"); 
        Parse.initialize(this, appID , appKey); 
		
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
	}// END onCreateIfConnected
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}// END onCreateOptionsMenu

	
	private void settingsCheck(){
		SharedPreferences preferences = this.getSharedPreferences("myprefs", 0);
      	String value1 = preferences.getString("APP_ID",null);
      	String value2 = preferences.getString("APP_Key",null);
      	
      	if ((value1 == null) || (value2 == null)) { 
      	    Log.d("TAG", "Setup default preferences");  //LOG
		    myToast("PLEASE SET PARSE.COM VALUES UNDER SETTINGS");  //TOAST
		    notifyMe();  //VIBRATE
      	} else {
      		Log.d("TAG", "SharedPreferences myprefs : exist");  // LOG
      		loadSettings();  //LOAD 
      	}
	}
	
	
	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
		appID = prefs.getString("APP_ID", "default Value");
		Log.i("IDnum", appID);
		
		appKey = prefs.getString("APP_Key", "default Value");
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
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	};// end myToast
	
	boolean isNetworkConnectionAvailable(){
		 
		    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		    NetworkInfo info = cm.getActiveNetworkInfo();     
		    if (info == null){
		    	return false;
		    }else{
		    	State network = info.getState();
		    	return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
		    }
		}     
	
	
	
}
