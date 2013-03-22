package com.klusman.kidkoder;


import com.klusman.kidkoder.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ChildInfo extends Activity {
	String fname;
	String lname;
	String dob;
	String contact;
	String allergies;
	String id;
	String gender;
	TextView TVname;
	TextView TVdob;
	TextView TVgender;
	TextView TValrgy;
	TextView TVem_num;
	ImageView mImage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	Log.i("ChildInfo", "Start");
		setContentView(R.layout.child_info_activity);
		
		TVname = (TextView) findViewById(R.id.tvNAME);
		TVdob = (TextView) findViewById(R.id.tvBDAY);
		TVgender = (TextView) findViewById(R.id.tvGENDER);
		TValrgy = (TextView) findViewById(R.id.tvALLERGIES);
		TVem_num = (TextView) findViewById(R.id.tvEM_NUM);
		mImage = (ImageView)findViewById(R.id.imageChildPhoto);
		
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    fname = extras.getString("FNAME");	
		    lname = extras.getString("LNAME");
		    dob = extras.getString("DOB");
		    contact = extras.getString("PHNUM");
		    allergies = extras.getString("ALLERGIES");
		    id = extras.getString("ID");
		    gender = extras.getString("GENDER");
		    
		    setText();
		    
		}
		
		
	}
	
	
	private void setText(){
		TVname.setText(fname + " " + lname);
		TVdob.setText(dob);
		TVgender.setText(gender);
		if (allergies != null){
			TValrgy.setText("Allergies: " + allergies);
		}else{
			TValrgy.setText("Allergies: NKA");
		}
		TVem_num.setText("Emergency Contact #: " + contact);
		if(gender.compareTo("Male") == 0){
			mImage.setBackgroundResource(R.drawable.male2);
			Log.i("IMAGE", "YES");
		}else{
			mImage.setBackgroundResource(R.drawable.female2);
			//mImage.setBackground(R.drawable.female2);
			Log.i("IMAGE", "NO");
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kid_data_menu, menu);
		return true;
	}// END onCreateOptionsMenu

	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
		case R.id.menu_quick:
			startActivity(new Intent(this, QuickLinksActivity.class));
			break;
		case R.id.menu_add:
			startActivity(new Intent(this, ChildDataADD.class));
			break;
		case R.id.menu_edit:
			startActivity(new Intent(this, ChildDataADD.class));
			break;
		case R.id.menu_settings:
			startActivity(new Intent(this, SettingsActivity.class));
			break;
		case R.id.menu_home:
			startActivity(new Intent(this, MainActivity.class));
			break;
		}
	return super.onMenuItemSelected(featureId, item);
	}
}
