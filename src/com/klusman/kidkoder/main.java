package com.klusman.kidkoder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class main extends FragmentActivity {
	
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        if (container == null) {
	            return null;
	        }
	        return (LinearLayout)inflater.inflate(R.layout.tabs_fragment, container, false);
	    }
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//		super.onCreateView(inflater, container, savedInstanceState);
//		
//		LinearLayout view = (LinearLayout) inflater.inflate(R.layout.activity_main, container, false);
//		
//		return view;
//	}
}
