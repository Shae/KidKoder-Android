package com.klusman.kidkoder;

import java.util.ArrayList;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class myArrayAdapter extends ArrayAdapter<Kid>{

	private final Context _context;
	//private final String[] values;
	String appID;
	String appKey;
	TextView textView;
	private final ArrayList<Kid> _kidlinList ;

	
	public myArrayAdapter(Context context, ArrayList<Kid> kidlinList) {
		super(context, R.layout.cell, kidlinList);
		
		
		this._context = context;
		this._kidlinList = kidlinList;
		Log.i("Array Adapter", String.valueOf(kidlinList.size()));
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.cell, parent, false);
		textView = (TextView) rowView.findViewById(R.id.tvALRGYChildName);

		String name = _kidlinList.get(position).getFirstname() + " " + _kidlinList.get(position).getLastName();
		String gender = _kidlinList.get(position).getGender();
		textView.setText(name);
		
		ImageView gendIcon = (ImageView) rowView.findViewById(R.id.imageGenderIcon);

		if(gender.compareTo("Male")  == 0){
			gendIcon.setImageResource(R.drawable.male2);
		}
		

		return rowView;
	}
	
	
	
}
