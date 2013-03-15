package com.klusman.kidkoder;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ChildInfo extends Activity {

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.kid_data_menu, menu);
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
		case R.id.menu_edit:
			startActivity(new Intent(this, ChildDataADD.class));
			break;
		}
	return super.onMenuItemSelected(featureId, item);
	}
}
