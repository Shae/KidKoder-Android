package com.klusman.kidkoder;

import java.util.List;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myArrayAdapter extends ArrayAdapter<String>{

	private final Context context;
	private final String[] values;
	String appID;
	String appKey;
	TextView textView;

	
	public myArrayAdapter(Context context, String[] values) {
		super(context, R.layout.cell, values);
		this.context = context;
		this.values = values;
		Parse.initialize(context, appID, appKey); 
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.cell, parent, false);
		textView = (TextView) rowView.findViewById(R.id.tvChildName);
		textView.setText(values[position]);
		
//		ParseQuery query = new ParseQuery("ChildDB");
//		query.getInBackground(values[position], new GetCallback() {  
//			public void done(ParseObject object, ParseException e) {
//					Log.i("PULL", "step 2");
//				if (e == null) {
//						Log.i("PULL", "step 3");
//					String fName = object.getString("fName");
//					String lName = object.getString("lName");
//						Log.i("PULL", fName + " " + lName);
//						
//					String name = fName + " " + lName;
//						
//						String dob = object.getString("bday");
//						String phnum = object.getString("contactNum");
//						String alrgList = object.getString("allergiesList");
//						String gend = object.getString("gender");
//					Log.i("ALL", dob + ", " + phnum + ", " + alrgList + ", " + gend );
//					
//					//textView.setText(name);
//					
//				} else {
//					Log.i("PULLED OBJ", "Something went wrong in GetObj");
//				}
//			}
//		});
		//textView.setText(values[position]);
		
		
		//textView.setText(text);
		
		ImageView gendIcon = (ImageView) rowView.findViewById(R.id.imageGenderIcon);
		// build if check on gender to set appropiate icon
 
		// Change icon based on name
		//String s = values[position];
		//Log.i("SONG", s);
		return rowView;
	}
	
	
	private void setRowText(String name){
		textView.setText(name);
	}
	
}
