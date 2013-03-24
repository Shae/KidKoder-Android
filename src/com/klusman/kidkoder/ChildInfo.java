package com.klusman.kidkoder;


import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChildInfo extends Activity {
	
	String appID;
	String appKey;
	String fname;
	String lname;
	String dob;
	String contact;
	String allergies;
	String id;
	String gender;
	//byte[] photo;
	TextView TVname;
	TextView TVdob;
	TextView TVgender;
	TextView TValrgy;
	TextView TVem_num;
	ImageView mImage;
	ImageButton call;
	Context _context;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//_context = this;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	Log.i("ChildInfo", "Start");
      	loadSettings();
		setContentView(R.layout.child_info_activity);
		Parse.initialize(this, appID , appKey); 
		TVname = (TextView) findViewById(R.id.tvNAME);
		TVdob = (TextView) findViewById(R.id.tvBDAY);
		TVgender = (TextView) findViewById(R.id.tvGENDER);
		TValrgy = (TextView) findViewById(R.id.tvALLERGIES);
		TVem_num = (TextView) findViewById(R.id.tvEM_NUM);
		mImage = (ImageView)findViewById(R.id.imageChildPhoto);
		call = (ImageButton)findViewById(R.id.callBtn);
		
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    fname = extras.getString("FNAME");	
		    lname = extras.getString("LNAME");
		    dob = extras.getString("DOB");
		    contact = extras.getString("PHNUM");
		    allergies = extras.getString("ALLERGIES");
		    id = extras.getString("ID");
		    gender = extras.getString("GENDER");
		    //photo = extras.getByteArray("PHOTO");
		    setText();
		    
		    pullObject();
		}
		
		
	}  // END onCreate
	
	
	private void setText(){
		TVname.setText(fname + " " + lname);
		TVdob.setText(dob);
		TVgender.setText(gender);
		
		if (allergies != null){
			TValrgy.setText("Allergies: " + allergies);
		}else{
			TValrgy.setText("Allergies: NKA");
		}
		

		TVem_num.setText("Emergency #: " + contact);
		if(contact != null){
				call.setOnClickListener(new OnClickListener() {
		           
					@Override
					public void onClick(View arg0) {
						
						askUser();
					}
		        }); 
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
			myToast("EDIT FEATURE CURRENTLY UNDER CONSTRUCTION");
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
	
	
	private void askUser(){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				ChildInfo.this);

		// set title
		alertDialogBuilder.setTitle("Call Emergency Contact?");

		// set dialog message
		alertDialogBuilder
		.setMessage("Call: " + contact)
		.setCancelable(false)
		.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				try {
					myToast("Calling: " + contact);
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel:" + contact));
					_context.startActivity(intent);
				} catch (Exception e) {
					myToast("Calling: " + "Failed");
					e.printStackTrace();
				}
				ChildInfo.this.finish();
			}
		})
		.setNegativeButton("No",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// the dialog box closes and does nothing
				dialog.cancel();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
	
	
	private void pullObject(){
		ParseQuery query = new ParseQuery("ChildDB");
		query.getInBackground(id, new GetCallback() {  
			public void done(ParseObject object, ParseException e) {
				
				if (e == null) {
					Log.i("PULLED OBJ", "Obj pull worked");	
					pullPhotoFromObj(object);
				} else {
					Log.i("PULLED OBJ", "Something went wrong in GetObj");
				}
				
			}
		});
	}
	
	private void pullPhotoFromObj(ParseObject object){
		ParseFile ChildPhoto;
		try {
			ChildPhoto = (ParseFile)object.get("photo");
			ChildPhoto.getDataInBackground(new GetDataCallback() {
				
				@Override
				public void done(byte[] data, ParseException e) {
					if (e == null) {
						mImage.setImageBitmap(BitmapFactory.decodeByteArray(data,0,data.length) );
						Log.i("Image", "Pulled");
					} else {
						Log.i("failed", "photo didnt work");
						if(gender.compareTo("Male") == 0){
							mImage.setBackgroundResource(R.drawable.male2);
						}else{
							mImage.setBackgroundResource(R.drawable.female2);
						}
					}
					
				}
			});	
		} catch (Exception e1) {
			Log.i("ChildInfo photo", "No Photo on file");
		}
	}
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildInfo.this, textIN, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	};// end myToast
	
	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
		appID = prefs.getString("APP_ID", "default Value");
		Log.i("IDnum", appID);
		
		appKey = prefs.getString("APP_Key", "default Value");
		Log.i("KEY", appKey);
		
	}  //  END loadSettings
}
