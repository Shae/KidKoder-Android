package com.klusman.kidkoder;

import java.util.ArrayList;
import java.util.List;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

public class ChildList extends ListActivity {

	String appID;
	String appKey;
	List<ParseObject> kidObjects;
	ArrayList<Kid> kidlinList ;
	String _id;
	String _firstname;
	String _lastname;
	String _dob;
	String _phnum;
	String _alrgList;
	String _gend;
	private final int VIB_NOTE_ID = 1;
	
	
	
	private ArrayList<String> kids = new ArrayList<String>();
	private String[] myKidsIdArray = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	loadSettings();
      	
      	kidlinList = new ArrayList<Kid>();
      	Parse.initialize(this, appID, appKey); 
      	
      	
      	getData();
		
	}
	
	private void setConstructor(){
		if(kidlinList.size() > 0){
			setListAdapter(new myArrayAdapter(this, kidlinList));  // works
		}else{
			notifyMe();
			myToast("Please add Children");
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kid_list, menu);
		return true;
	}// END onCreateOptionsMenu
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, ChildInfo.class);
			//intent.putExtra("PHOTO", kidlinList.get(position).getPhoto());
			intent.putExtra("FNAME", kidlinList.get(position).getFirstname());
			intent.putExtra("LNAME", kidlinList.get(position).getLastName());
			intent.putExtra("DOB", kidlinList.get(position).getBDay());
			intent.putExtra("GENDER", kidlinList.get(position).getGender());
			intent.putExtra("PHNUM", kidlinList.get(position).getEmergencyContact());
			intent.putExtra("ALLERGIES", kidlinList.get(position).getAllergiesList());
			intent.putExtra("ID", kidlinList.get(position).getChildID());
			intent.putExtra("INOUT", kidlinList.get(position).getInOut());
		
			Log.i("Checked in?", String.valueOf(kidlinList.get(position).getInOut()));
		Log.i("CLICKED", String.valueOf(position));
	
		startActivity(intent);
		
	}  //  END onListItemClicked
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
		case R.id.menu_quick:
			Log.i("CLICKED","Go to Quick Links");
			startActivity(new Intent(this, QuickLinksActivity.class));
			break;
		case R.id.menu_add:
			Log.i("CLICKED","Go to ADD Child");
			startActivity(new Intent(this, ChildDataADD.class));
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

		
	private void getObject(){

		Log.i("Init getObj", "Start");
//			
		int x = myKidsIdArray.length;
		Log.i("MyKids", String.valueOf(x));
		for( int i = 0; i < x; i++){
			String id = myKidsIdArray[i];
			Log.i("Loop", String.valueOf(i) + " times around");
			Log.i("id", id);
			
			ParseQuery query = new ParseQuery("ChildDB");
			query.getInBackground(id, new GetCallback() {  
				public void done(ParseObject object, ParseException e) {
						//Log.i("PULLED OBJ", "step 2");
					if (e == null) {
							//Log.i("PULLED OBJ", "step 3");
							kidlinList.add(new Kid(
									//object.getBytes("photo"),
									object.getObjectId().toString(),
									object.getString("fName"), 
									object.getString("lName"), 
									object.getString("bday"), 
									object.getString("gender"), 
									object.getBoolean("enrolledBool"), 
									object.getBoolean("allergiesBool"), 
									object.getString("allergiesList"), 
									object.getString("contactNum"),
									object.getBoolean("checkIN")
									
									));
							
						//int k = kidlinList.size();
						//Log.i("KIDLIN list", String.valueOf(k));			
					} else {
						Log.i("PULLED OBJ", "Something went wrong in GetObj");
					}
					setConstructor();
				}
			});
		}		
	}
	

	
	private void getData(){
		ParseQuery query = new ParseQuery("ChildDB");
		query.findInBackground(
				new FindCallback() {
					@Override
					public void done(List<ParseObject> objects, ParseException e) {
						if (e == null) {
							int x = objects.size();

							for ( int i = 0; i < x; i++){  // Sending Object ID's to kids list
								String o = objects.get(i).getObjectId().toString();
								Log.i("id", o);
								kids.add(o);	
								Log.i("kids Size", String.valueOf( kids.size()));
							}

							if (kids.size() > 0){  // turning the list into a string array
								myKidsIdArray = kids.toArray(new String[kids.size()]);
								getObject();  //works
							}else{
								Log.i("Kids List Size", String.valueOf(kids.size()));
							}	
						} else {
							String ee = e.toString();
							Log.i("ERROR from PARSE", ee);
						}

					}
				});
	}
	
	public void notifyMe(){ 
		//private final int VIB_NOTE_ID = 1;  // add this line to global variables up top
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification note = new Notification();
		note.defaults = Notification.DEFAULT_VIBRATE;
		nm.notify(VIB_NOTE_ID, note);
	}
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildList.this, textIN, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	};// end myToast
	
}


