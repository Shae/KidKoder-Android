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

public class myArrayAdapterAllergy extends ArrayAdapter<Kid>{

	private final Context _context;
	//private final String[] values;
	String appID;
	String appKey;
	TextView textView;
	TextView algry;
	
	private final ArrayList<Kid> _kidlinList ;
	//private final Kid[] _myKidsArray;

	
	public myArrayAdapterAllergy(Context context, ArrayList<Kid> kidlinList) {
		super(context, R.layout.cell_allergy, kidlinList);
		
		Log.i("Array Adapter", "Initiated");
		this._context = context;
		this._kidlinList = kidlinList;
		//this._myKidsArray = myKidsArray;
		Log.i("Array Adapter", String.valueOf(kidlinList.size()));
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.cell_allergy, parent, false);
		
		textView = (TextView) rowView.findViewById(R.id.tvALRGYChildName);
		String name = _kidlinList.get(position).getFirstname() + " " + _kidlinList.get(position).getLastName();
		textView.setText(name);
		
		algry = (TextView) rowView.findViewById(R.id.tvALRGYs);
		String allergies = _kidlinList.get(position).getAllergiesList();
		algry.setText(allergies);
	
		

		return rowView;
	}
	
	
	
}
