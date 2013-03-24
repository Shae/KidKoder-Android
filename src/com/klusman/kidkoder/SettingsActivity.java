package com.klusman.kidkoder;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class SettingsActivity extends Activity {
	String _appID;
	String _appKey;
	String _schoolWeb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		
		EditText appID = (EditText)findViewById(R.id.etAppID);
		_appID = appID.getText().toString();
		
		EditText appKey = (EditText)findViewById(R.id.etClientKey);
		_appKey = appKey.getText().toString();
		
		EditText schoolWeb = (EditText)findViewById(R.id.etSchool);
		_schoolWeb = schoolWeb.getText().toString();
		
	}
	
	
	
	private void saveSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE);
		SharedPreferences.Editor localStorage = prefs.edit(); 
		
		localStorage.putString("APP_ID", _appID);
		localStorage.putString("APP_Key", _appKey);
		localStorage.putString("SCHOOL_WEB", _schoolWeb);

		localStorage.commit(); 
			Log.i("LocalStorage", "Successfully saved settings");
			Log.i("SAVED ID", _appID);
			Log.i("SAVED KEY", _appKey);
			Log.i("SAVED SCHOOL_WEB", _schoolWeb);
	}  // END saveSettings



	@Override
	protected void onDestroy() {
			saveSettings();
		super.onDestroy();
	}
	
	
	
	
}
