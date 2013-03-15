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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_activity);
		
		EditText appID = (EditText)findViewById(R.id.etAppID);
		_appID = appID.getText().toString();
		
		EditText appKey = (EditText)findViewById(R.id.etClientKey);
		_appKey = appKey.getText().toString();
		
	}
	
	
	
	private void saveSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE);
		SharedPreferences.Editor localStorage = prefs.edit(); 
		
		localStorage.putString("APP_ID", _appID);
		localStorage.putString("APP_Key", _appKey);

		localStorage.commit(); 
			Log.i("LocalStorage", "Successfully saved settings");
			Log.i("ID", _appID);
			Log.i("KEY", _appKey);
	}  // END saveSettings



	@Override
	protected void onDestroy() {
			saveSettings();
		super.onDestroy();
	}
	
	
	
	
}
