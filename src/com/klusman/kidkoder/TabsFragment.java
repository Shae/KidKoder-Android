package com.klusman.kidkoder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TabsFragment extends Fragment implements OnTabChangeListener {

	private static final String TAG = "FragmentTabs";
    public static final String TAB_MAIN = "Main";
    public static final String TAB_KIDS = "Kids";
    public static final String TAB_ALLERGIES = "Allergies";
    public static final String TAB_LINKS = "Links";
 
    private View _v;
    private TabHost _TabHost;
    private int _CurrentTab;
 
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        _v = inflater.inflate(R.layout.tabs_fragment, null);
        _TabHost = (TabHost) _v.findViewById(android.R.id.tabhost);
        setupTabs();
        return _v;
    }
 
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
 
        _TabHost.setOnTabChangedListener(this);
        _TabHost.setCurrentTab(_CurrentTab);
        // manually start loading stuff in the first tab
        updateTab(TAB_MAIN, R.id.tab_1);
    }
 
    private void setupTabs() {
        _TabHost.setup(); // you must call this before adding your tabs!
//        _TabHost.addTab(newTab(TAB_MAIN, R.string.tab1_main, R.id.tab_1));
//        _TabHost.addTab(newTab(TAB_KIDS, R.string.tab2_kids, R.id.tab_2));
//        _TabHost.addTab(newTab(TAB_KIDS, R.string.tab3_allergies, R.id.tab_3));
//        _TabHost.addTab(newTab(TAB_KIDS, R.string.tab4_quick_links, R.id.tab_4));
    }
 
    private TabSpec newTab(String tag, int labelId, int tabContentId) {
        Log.d(TAG, "buildTab(): tag=" + tag);
 
        View indicator = LayoutInflater.from(getActivity()).inflate(
                R.layout.tab,
                (ViewGroup) _v.findViewById(android.R.id.tabs), false);
        ((TextView) indicator.findViewById(R.id.tvTab)).setText(labelId);
 
        TabSpec tabSpec = _TabHost.newTabSpec(tag);
        tabSpec.setIndicator(indicator);
        tabSpec.setContent(tabContentId);
        return tabSpec;
    }
 
    @Override
    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged(): tabId=" + tabId);
        FragmentManager fm = getFragmentManager();
//        switch (_TabHost.getId()){
//		case R.id.tab_1:
////			startActivity(new Intent(this, MainActivity.class));
//	        if (fm.findFragmentByTag(tabId) == null) {
//	            fm.beginTransaction()
//	                    .replace(R.id.tab_1, new main() , tabId)
//	                    .commit();
//	        }
//			break;
//		case R.id.tab_2:
//			if (fm.findFragmentByTag(tabId) == null) {
//	            fm.beginTransaction()
//	                    .replace(R.id.tab_2, new main() , tabId)
//	                    .commit();
//	        }
//			break;
//		case R.id.tab_3:
//			if (fm.findFragmentByTag(tabId) == null) {
//	            fm.beginTransaction()
//	                    .replace(R.id.tab_3, new main() , tabId)
//	                    .commit();
//	        }
//			break;
//		case R.id.tab_4:
//			if (fm.findFragmentByTag(tabId) == null) {
//	            fm.beginTransaction()
//	                    .replace(R.id.tab_4, new main() , tabId)
//	                    .commit();
//	        }
//			break;
//		}
//        if (TAB_MAIN.equals(tabId)) {
//            updateTab(tabId, R.id.tab_1);
//            _CurrentTab = 0;
//            return;
//        }
//        if (TAB_KIDS.equals(tabId)) {
//            updateTab(tabId, R.id.tab_2);
//            _CurrentTab = 1;
//            return;
//        }
//        if (TAB_ALLERGIES.equals(tabId)) {
//            updateTab(tabId, R.id.tab_3);
//            _CurrentTab = 2;
//            return;
//        }
//        if (TAB_LINKS.equals(tabId)) {
//            updateTab(tabId, R.id.tab_4);
//            _CurrentTab = 3;
//            return;
//        }
    }
 
    private void updateTab(String tabId, int placeholder) {
        FragmentManager fm = getFragmentManager();
//        if (fm.findFragmentByTag(tabId) == null) {
//            fm.beginTransaction()
//                    .replace(placeholder, , tabId)
//                    .commit();
    
//		if (fm.findFragmentByTag(tabId) == null) {
//            fm.beginTransaction()
//                    .replace(placeholder, new main() , tabId)
//                    .commit();
//        }
        }
//    }
 

}
