package com.klusman.kidkoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuickLinksActivity extends Activity {

	String schoolWeb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quick_links);
	
		loadSettings();
	
	Button LocSchool = (Button)findViewById(R.id.BtnLocSch);
	LocSchool.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String url = schoolWeb;
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	});
	
	
	Button PiosCon = (Button)findViewById(R.id.BtnPoiCon);
	PiosCon.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String url = "http://www.aapcc.org";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	});
	
	Button Missing = (Button)findViewById(R.id.BtnMissChild);
	Missing.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String url = "http://www.missingkids.com/home";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	});
	
	Button chldAbuse = (Button)findViewById(R.id.btnChildAbu);
	chldAbuse.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String url = "http://kidshealth.org/parent/positive/talk/child_abuse.html";
			Intent i = new Intent(Intent.ACTION_VIEW);
			i.setData(Uri.parse(url));
			startActivity(i);
		}
	});
	}
	
	private void loadSettings(){
		SharedPreferences prefs = getSharedPreferences("myprefs",Context.MODE_PRIVATE); 

		schoolWeb = prefs.getString("SCHOOL_WEB", "default Value");
			Log.i("LOAD SAVE", schoolWeb);
		
	}  //  END loadSettings
	
}
