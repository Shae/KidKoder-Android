package com.klusman.kidkoder;

import java.util.Arrays;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;


import com.parse.Parse;


public class ChildDataADD extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	Parse.initialize(this, "sF8m2jJo7c3kQoenTq9UG67Rk3pnPEw7prrf4ZfR", "OnuGanjP6xKwQV7JOUC6wUMsmGiUlbxhyitxHS1P"); 
		setContentView(R.layout.child_data_activity);
		
		//EditText fName = (EditText)findViewById(R.id.)
		

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
				saveData();
			break;
		}
	return super.onMenuItemSelected(featureId, item);
	}
	
	
	private void saveData(){
		
		
		
		
		
		
		
		Log.i("saveDate", "Function Initialized");
		ParseObject childObject = new ParseObject("CHILDREN");
		childObject.put("fName", "JOHN");
		childObject.put("lName", "DOE");
		childObject.put("bday", "12-12-2011");
		childObject.put("gender", "male");
		childObject.put("enrolled", true);
		childObject.addAllUnique("skills", Arrays.asList("flying", "kungfu"));
		childObject.put("checkedIN", true);
		childObject.put("contectNum", "888-888-8888");
		
		
		childObject.saveInBackground();
	}
	
}
