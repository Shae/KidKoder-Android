package com.klusman.kidkoder;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class ChildList extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.child_list_activity);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kid_list, menu);
		return true;
	}// END onCreateOptionsMenu
	

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
	
	public void myToast(String text){  
		CharSequence textIN = text;
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(ChildList.this, textIN, duration);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
	};// end myToast
	
}
