package com.klusman.kidkoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Toast;

public class NoNetwork extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
      	setContentView(R.layout.no_connection_activity);
      	
      	if(isNetworkConnectionAvailable() == true){
      		Intent intent = new Intent(NoNetwork.this, MainActivity.class);
	        startActivity(intent);
      		Log.i("CONNECTION", "Network Connection Detected, Starting MainActivity");
      	}else{
      		myToast("Still No Connection Detected");
      		Log.i("CONNECTION", "Network Connection NOT Detected");
      	}
      	
      	
	
	}// End onCreate
	
	boolean isNetworkConnectionAvailable(){
		 
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo info = cm.getActiveNetworkInfo();     
	    if (info == null){
	    	return false;
	    }else{
	    	State network = info.getState();
	    	return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
	    }
	}    
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(NoNetwork.this, textIN, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	};// end myToast
}
