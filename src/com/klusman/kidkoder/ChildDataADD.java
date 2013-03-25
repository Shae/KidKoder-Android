package com.klusman.kidkoder;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
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
import android.widget.TextView;
import android.widget.Toast;


import com.parse.Parse;


public class ChildDataADD extends Activity {
	Boolean anEdit = false;
	Boolean defaultPhoto = true;
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
	String id;
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
      	setContentView(R.layout.child_data_activity);
      	Parse.initialize(this, appID, appKey); 
      	
      	buildPage();
      	
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			anEdit = true;
		    fName.setText(extras.getString("FNAME"));	
		    lName.setText(extras.getString("LNAME"));
		    DoB.setText( extras.getString("DOB"));
		    emergencyContactNum.setText(extras.getString("PHNUM"));
		    allergiesList.setText(extras.getString("ALLERGIES"));
		    id = extras.getString("ID");
			// NEED photo pull
			pullObject();  // get / set obj photo on open
		   // gender = extras.getString("GENDER");
			if(extras.getString("GENDER").compareTo("Male") == 0){
				radioGend.check(0);
				Log.i("GENDER HIT", "MALE");
			}else{
				radioGend.check(1);
				Log.i("GENDER HIT", "FEMALE");
			}

			
		}else{
			anEdit = false;
		}
		
		
		
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
			resetPage();
			break;
		case R.id.menu_save:
			if(validateEntries() == true){
				saveData(); 
			}
			break;
		case R.id.menu_settings:
			startActivity(new Intent(this, SettingsActivity.class));
			break;
		}
	return super.onMenuItemSelected(featureId, item);
	}
	
	private void resetPage(){
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
		radioGend.check(-1);
		photo.setImageResource(R.drawable.photo3);
	}
	
	private void buildPage(){
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
	
	
	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 
			appID = prefs.getString("APP_ID", "default Value");
			Log.i("IDnum", appID);
			
			appKey = prefs.getString("APP_Key", "default Value");
			Log.i("KEY", appKey);

	}  //  END loadSettings
	
	
	private void updateVars(){
		Log.i("UPDATE", "Variables Updated");
		
		first = fName.getText().toString();
		last = lName.getText().toString();
		bday = DoB.getText().toString();
		allerList = allergiesList.getText().toString();
		emContactNum = emergencyContactNum.getText().toString();
		
		int index = radioGend.indexOfChild(findViewById(radioGend.getCheckedRadioButtonId()));
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
	
	public boolean validateEntries(){
		if(defaultPhoto == true){
			myToast("Please add photo before saving");
			return false;
		}
		
		// check first
		TextView tvf = (TextView)findViewById(R.id.tvFNameTitle);
		if(fName.getText().length() <= 1){
			tvf.setTextColor(Color.parseColor("#FF0000"));
			myToast("Please add a First Name");
			return false;
		}
			tvf.setTextColor(Color.parseColor("#66CD00"));
		
		
		// check last
		TextView tvl = (TextView)findViewById(R.id.tvLNameTitle);
		if(lName.getText().length() <= 1){
			tvl.setTextColor(Color.parseColor("#FF0000"));
			myToast("Please add a Last Name");
			return false;
		}
			tvl.setTextColor(Color.parseColor("#66CD00"));
		
			
		// Validate birthday
		String regEx ="^(0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])[- /.](19|20)\\d{2}$";	
		Matcher matcherObj = Pattern.compile(regEx).matcher(DoB.getText());
		TextView tvbd = (TextView)findViewById(R.id.tvDoBTitle);
		int match = 0;
			if (matcherObj.matches()){
				match = 1;
			}else{
				match = 0;
			}
		if(match == 0){
			tvbd.setTextColor(Color.parseColor("#FF0000"));
			myToast("Please make sure Birthday is formatted correctly mm/dd/yyyy");
			return false;
		}
			tvbd.setTextColor(Color.parseColor("#66CD00"));
		
			
		// Check gender selected
		int index = radioGend.indexOfChild(findViewById(radioGend.getCheckedRadioButtonId()));
		TextView rg = (TextView)findViewById(R.id.tvGenderTitle);
		if(index == -1){
			rg.setTextColor(Color.parseColor("#FF0000"));
			myToast("Please select a gender");
			return false;
		}
			rg.setTextColor(Color.parseColor("#66CD00"));
			
		// MADE IT :)	
		return true;  
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
	    defaultPhoto = false;    
	}
	
	private void saveData(){  
		updateVars();
		Log.i("saveDate", "Function Initialized");

		if(anEdit == false){
			// build ByteArray of bitmap for save
			byte[] data = convertBitmapToByteArray(_context, mImageBitmap);
			Log.i("saveDate", "Photo converted to byte");
			final ParseFile photoFile = new ParseFile("photo.bmp", data);
			Log.i("saveDate", "ParseFile");
			// Send ByteArray to Parse
			photoFile.saveInBackground(new SaveCallback() {
				public void done(ParseException e) {	
					// when done link to the rest of the data and save to parse
					ParseObject childObject = new ParseObject("ChildDB");
					childObject.put("photo", photoFile);				  
					childObject.put("fName", first);			  
					childObject.put("lName", last);			  
					childObject.put("bday", bday);			  
					childObject.put("gender", gender);
					childObject.put("enrolledBool", enrolledBool);				  
					childObject.put("allergiesList", allerList);	  
					childObject.put("contactNum", emContactNum);		  
					childObject.saveInBackground();
				}			
			});

		}// END if anEdit FALSE

		if(anEdit == true){
			// build ByteArray of bitmap for save
			byte[] data = convertBitmapToByteArray(_context, mImageBitmap);
			Log.i("saveDate", "Photo converted to byte");
			final ParseFile photoFile = new ParseFile("photo.bmp", data);
			// Send ByteArray to Parse
			photoFile.saveInBackground(new SaveCallback() {

				public void done(ParseException e) {	
					ParseQuery query = new ParseQuery("ChildDB");
					query.getInBackground(id, new GetCallback() {  
						public void done(ParseObject object, ParseException e) {
							if (e == null) {
								object.put("photo", photoFile);				  
								object.put("fName", first);			  
								object.put("lName", last);			  
								object.put("bday", bday);			  
								object.put("gender", gender);
								object.put("enrolledBool", enrolledBool);				  
								object.put("allergiesList", allerList);	  
								object.put("contactNum", emContactNum);		  
								object.saveInBackground();

							} else {
								Log.i("PULLED OBJ", "Something went wrong with the Update");
							}
						}
					});
				}		
			});
		} // end if anEdit TRUE
		notifyUser("Update Complete", "Returning to main navigation page.");
	}  // END save data
	
	public byte[] convertBitmapToByteArray(Context context, Bitmap bitmap) {
	   //if(defaultPhoto == false){
			ByteArrayOutputStream buffer = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
		    bitmap.compress(CompressFormat.PNG, 100, buffer);
		    return buffer.toByteArray();
	  // }
	    
	}
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildDataADD.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
	
	private void pullObject(){  // prep Child obj and pass for image pull
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
	
	private void pullPhotoFromObj(ParseObject object){  // image pull
		ParseFile ChildPhoto;
		try {
			ChildPhoto = (ParseFile)object.get("photo");
			ChildPhoto.getDataInBackground(new GetDataCallback() {
				
				@Override
				public void done(byte[] data, ParseException e) {
					if (e == null) {
						photo.setImageBitmap(BitmapFactory.decodeByteArray(data,0,data.length) );
						Log.i("Image", "Pulled");
						defaultPhoto = false;
					} else {
						Log.i("failed", "photo didnt work");
						if(gender.compareTo("Male") == 0){
							photo.setBackgroundResource(R.drawable.male2);
						}else{
							photo.setBackgroundResource(R.drawable.female2);
						}
					}
					
				}
			});	
		} catch (Exception e1) {
			Log.i("ChildInfo photo", "No Photo on file");
		}
	}
	
	private void notifyUser(String title, String Msg){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ChildDataADD.this);

		// set title
		alertDialogBuilder.setTitle(title);

		// set dialog message
		alertDialogBuilder
		.setMessage(Msg)
		.setCancelable(false)
		.setPositiveButton("Done",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				Intent intent = new Intent(ChildDataADD.this, MainActivity.class);
		        startActivity(intent);
				ChildDataADD.this.finish();
			}
		});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}
	
}
