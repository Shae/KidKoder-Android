package com.klusman.kidkoder;

import java.io.ByteArrayOutputStream;

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
	Boolean anEdit = false;
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
			validateEntries();
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
			Log.i("saveDate", "ParseFile 1");
			// Send ByteArray to Parse
			photoFile.saveInBackground(new SaveCallback() {

				public void done(ParseException e) {	
					Log.i("saveDate", "ParseFile 2");
					ParseQuery query = new ParseQuery("ChildDB");
					query.getInBackground(id, new GetCallback() {  
						public void done(ParseObject object, ParseException e) {
							if (e == null) {
								Log.i("saveDate", "ParseFile 3");

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
