package com.klusman.kidkoder;

import java.util.ArrayList;
import java.util.List;


import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ListActivity;
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
	//private Object[] myKidsArray;
	String _id;
	String _firstname;
	String _lastname;
	String _dob;
	String _phnum;
	String _alrgList;
	String _gend;
	
	
	
	private ArrayList<String> kids = new ArrayList<String>();
	private String[] myKidsIdArray = null;
	private String[] KidsArray = {"danna", "louis", "ralph"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	kidlinList = new ArrayList<Kid>();
      	loadSettings();
      	Parse.initialize(this, appID, appKey); 
      	
      	
      	getData();
      	
      	//myKidsArray = ;  // build a kids array STILL NEEDED
      	
		//setContentView(R.layout.child_list_activity);
		
	}
	
	private void setConstructor(){
		setListAdapter(new myArrayAdapter(this, myKidsIdArray));  // works
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kid_list, menu);
		return true;
	}// END onCreateOptionsMenu
	

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
	}  //  END onListItemClicked
	
	
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()){
		case R.id.menu_quick:
			startActivity(new Intent(this, QuickLinksActivity.class));
			break;
		case R.id.menu_add:

			startActivity(new Intent(this, ChildDataADD.class));
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
	
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildList.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
	
	
	private void getObject(){

		Log.i("Init getObj", "Start");
//			ParseQuery query = new ParseQuery("ChildDB");
//			//Log.i("PULLED OBJ", "step 1");
//			query.getInBackground(id, new GetCallback() {  
//				public void done(ParseObject object, ParseException e) {
//					//Log.i("PULLED OBJ", "step 2");
//					if (e == null) {
//						//Log.i("PULLED OBJ", "step 3");
//						String fName = object.getString("fName");
//						String lName = object.getString("lName");
//						Log.i("PULLED OBJ", fName + " " + lName);
//						
//					} else {
//						Log.i("PULLED OBJ", "Something went wrong in GetObj");
//					}
//				}
//			});
		int x = myKidsIdArray.length;
		Log.i("MyKids", String.valueOf(x));
		for( int i = 0; i < x; i++){
			String id = myKidsIdArray[i];
			Log.i("Loop", String.valueOf(i) + " times around");
			Log.i("id", id);
			
			ParseQuery query = new ParseQuery("ChildDB");
			query.getInBackground(id, new GetCallback() {  
				public void done(ParseObject object, ParseException e) {
						Log.i("PULLED OBJ", "step 2");
					if (e == null) {
							Log.i("PULLED OBJ", "step 3");
//						String fName = object.getString("fName");
//						String lName = object.getString("lName");
//							Log.i("PULLED OBJ", fName + " " + lName);
						
							kidlinList.add(new Kid(
									object.getObjectId().toString(), 
									object.getString("fName"), 
									object.getString("lName"), 
									object.getString("bday"), 
									object.getString("gender"), 
									object.getBoolean("enrolledBool"), 
									object.getBoolean("allergiesBool"), 
									object.getString("allergiesList"), 
									object.getString("contactNum")
									));
						

						
						int k = kidlinList.size();
						Log.i("KIDLIN list", String.valueOf(k));
						
						
						
					} else {
						Log.i("PULLED OBJ", "Something went wrong in GetObj");
					}
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
					      		setConstructor();
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
	
}


