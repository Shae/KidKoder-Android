package com.klusman.kidkoder;

import java.io.ByteArrayOutputStream;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;


import com.parse.Parse;


public class ChildDataADD extends Activity {
	String appID;
	String appKey;
	
	EditText fName;
	EditText lName;
	EditText DoB;
	EditText allergiesList;
	EditText emergencyContactNum;
	
	//CheckBox allergies;
	CheckBox enrolled;
	
	RadioGroup radioGend;
	
	ImageView photo;
	
	String first;
	String last;
	String bday;
	String allerList;
	String emContactNum;
	String gender;
	Boolean enrolledBool;
	//Boolean allergiesBool;
	
	Context _context;
	byte[] dataPhoto;
	Bitmap mImageBitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	_context = this;
      	loadSettings();
      	Parse.initialize(this, appID, appKey); 
		setContentView(R.layout.child_data_activity);
		
		// connect views with variables
		fName = (EditText)findViewById(R.id.etFName);
		fName.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
		lName = (EditText)findViewById(R.id.etLName);
		lName.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
		DoB = (EditText)findViewById(R.id.etDoB);
		DoB.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
		allergiesList = (EditText)findViewById(R.id.etAllergiesField);
		allergiesList.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
		emergencyContactNum = (EditText)findViewById(R.id.etEmergencyNum);	
		emergencyContactNum.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
//		allergies = (CheckBox)findViewById(R.id.boxAllergies);
//		allergies.setOnFocusChangeListener(new OnFocusChangeListener() {          
//
//	        public void onFocusChange(View v, boolean hasFocus) {
//	            if(!hasFocus){
//	            	updateVars();
//	            }
//	               
//	        }
//	    });  // Removed from Activity for now
		
		enrolled = (CheckBox)findViewById(R.id.boxEnrolled);
		enrolled.setOnFocusChangeListener(new OnFocusChangeListener() {          

	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
		radioGend = (RadioGroup)findViewById(R.id.RadioGrp);
		radioGend.setOnFocusChangeListener(new OnFocusChangeListener() {          
			
	        public void onFocusChange(View v, boolean hasFocus) {
	            if(!hasFocus){
	            	updateVars();
	            }
	               
	        }
	    });
		
		photo = (ImageView)findViewById(R.id.imageKidAddData);
		photo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dispatchTakePictureIntent();
			
			}
		});

		
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
			first = null;
			last = null;
			bday = null;
			allerList = null;
			emContactNum = null;
			gender = null;
			enrolledBool = true;
			//allergiesBool = false;
			
			fName.setText("");
			lName.setText("");
			DoB.setText("");
			allergiesList.setText("");
			emergencyContactNum.setText("");
			break;
		case R.id.menu_save:
			validateEntries();
			break;
		case R.id.menu_settings:
			startActivity(new Intent(this, SettingsActivity.class));
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
	
	
	private void updateVars(){
		Log.i("UPDATE", "vars updated");
		int index = radioGend.indexOfChild(findViewById(radioGend.getCheckedRadioButtonId()));
		Log.i("RADIO GRP", String.valueOf(index));
		
		first = fName.getText().toString();
		last = lName.getText().toString();
		bday = DoB.getText().toString();
		allerList = allergiesList.getText().toString();
		emContactNum = emergencyContactNum.getText().toString();
		
		if(index == 0){
			gender = "Male";
		}else if(index == 1){
			gender = "Female";
		}else{
			gender = "Male";
		}
		
		enrolledBool = true;  // hardcoded until needed
		//allergiesBool = false;  // hardcoded until needed

	}
	
	public void validateEntries(){
//		if(fName.getText().length() > 0){
//			first = fName.getText().toString();
		
//		}else{
//			TextView tv = (TextView)findViewById(R.id.tvFNameTitle);
//			tv.setTextColor(Color.parseColor("#FF0000"));
//		}
//		
//		if(lName.getText().length() > 0){
//			last = lName.getText().toString();
//			
//		}else{
//			TextView tv = (TextView)findViewById(R.id.tvLNameTitle);
//			tv.setTextColor(Color.parseColor("#FF0000"));
//		}
//		
//		if(DoB.getText().length() > 0){
//			bday = DoB.getText().toString();
//			
//		}else{
//			TextView tv = (TextView)findViewById(R.id.tvDoBTitle);
//			tv.setTextColor(Color.parseColor("#FF0000"));
//			
//		}
		
		saveData();    
	}
	
	
	private void dispatchTakePictureIntent() {
	    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(takePicture, 0);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{   
	    switch( resultCode )
	    {
	        case 0:
	            break;
	        case -1:
	            onPhotoTaken();
	            handleSmallCameraPhoto(data);
	            break;
	    }
	}

	protected void onPhotoTaken()
	{
		Log.i("PHOTO", "photo taken");
	    ///write code here what you want to done after capture the image using device camera
	}
	
	private void handleSmallCameraPhoto(Intent intent) {
	    Bundle extras = intent.getExtras();
	    mImageBitmap = (Bitmap) extras.get("data");
	    photo.setImageBitmap(mImageBitmap);
	    
	}
	
	private void saveData(){  
		updateVars();
		
		Log.i("saveDate", "Function Initialized");
//		byte[] data = convertBitmapToByteArray(_context, mImageBitmap);
//		Log.i("saveDate", "Photo converted to byte");
//		final ParseFile photoFile = new ParseFile("photo.bmp", data);
//		Log.i("saveDate", "ParseFile");
//		photoFile.saveInBackground(new SaveCallback() {
//			  public void done(ParseException e) {	  
//				  }			
//				});
		ParseObject childObject = new ParseObject("ChildDB");
		childObject.put("fName", first);
			Log.i("save", first);
			
		childObject.put("lName", last);
			Log.i("save", last);
			
		childObject.put("bday", bday);
			Log.i("save", bday);
			
		childObject.put("gender", gender);
			Log.i("save", gender);
			
		childObject.put("enrolledBool", enrolledBool);
			Log.i("save", enrolledBool.toString());
			
		childObject.put("allergiesList", allerList);
			Log.i("save", allerList);
			
		childObject.put("contactNum", emContactNum);
			Log.i("save", emContactNum);
		
		childObject.saveInBackground();
		
	}
	
	public byte[] convertBitmapToByteArray(Context context, Bitmap bitmap) {
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
	    bitmap.compress(CompressFormat.PNG, 100, buffer);
	    return buffer.toByteArray();
	}

	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildDataADD.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
	
}
