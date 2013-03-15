package com.klusman.kidkoder;

import java.io.File;
import java.util.Arrays;

import com.parse.ParseObject;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.Parse;


public class ChildDataADD extends Activity {
	String appID;
	String appKey;
	EditText fName;
	EditText lName;
	EditText DoB;
	String first;
	String last;
	String bday;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	
      	File f = new File("/data/data/com.klusman.kidkoder/shared_prefs/myprefs.xml");
			if (f.exists()){
			    Log.d("TAG", "SharedPreferences myprefs : exist");
				loadSettings();
			
			}else{
			    Log.d("TAG", "Setup default preferences");
			    myToast("PLEASE ADJUST USER SETTINGS");
			}
      	
      	Parse.initialize(this, appID, appKey); 
      	
		setContentView(R.layout.child_data_activity);
		
		fName = (EditText)findViewById(R.id.etFName);
		lName = (EditText)findViewById(R.id.etLName);
		DoB = (EditText)findViewById(R.id.etDoB);


		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kid_new_data, menu);
		return true;
	}// END onCreateOptionsMenu

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
		case R.id.menu_home:
			startActivity(new Intent(this, MainActivity.class));
			break;
		case R.id.menu_reset:
			startActivity(new Intent(this, ChildDataADD.class));
			break;
		case R.id.menu_save:
			validateEntries();
			
			break;
		}
	return super.onMenuItemSelected(featureId, item);
	}
	
	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
			appID = prefs.getString("APP_ID", "default Value");
			Log.i("IDnum", appID);
			
			appKey = prefs.getString("APP_Key", "default Value");
			Log.i("KEY", appKey);

	}  //  END loadSettings
	
	public void validateEntries(){
		if(fName.getText().length() > 0){
			first = fName.getText().toString();
			Log.i("first Name", first);
		}else{
			TextView tv = (TextView)findViewById(R.id.tvFNameTitle);
			tv.setTextColor(Color.parseColor("#FF0000"));
		}
		
		if(lName.getText().length() > 0){
			last = lName.getText().toString();
			Log.i("last Name", last);
		}else{
			TextView tv = (TextView)findViewById(R.id.tvLNameTitle);
			tv.setTextColor(Color.parseColor("#FF0000"));
		}
		
		if(DoB.getText().length() > 0){
			bday = DoB.getText().toString();
			Log.i("bday String", bday);
		}else{
			TextView tv = (TextView)findViewById(R.id.tvDoBTitle);
			tv.setTextColor(Color.parseColor("#FF0000"));
			
			//saveData();    //  COMMENTED OUT DURING VALIDATION SETUP
		}
	}
	
	
	private void saveData(){
		Log.i("saveDate", "Function Initialized");
		ParseObject childObject = new ParseObject("CHILDREN");
		childObject.put("fName", first);
		childObject.put("lName", last);
		childObject.put("bday", bday);
		childObject.put("gender", "male");
		childObject.put("enrolled", true);
		childObject.addAllUnique("allergies", Arrays.asList("default1", "default2"));
		childObject.put("checkedIN", true);
		childObject.put("contectNum", "888-888-8888");
		
		
		childObject.saveInBackground();
	}
	
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildDataADD.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
	
}
