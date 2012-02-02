package com.achai.shop.utils;

import android.app.Activity;
import android.app.TabActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TabHost;

import com.achai.R;

public class TabMessageListener implements OnClickListener, OnCheckedChangeListener{
	
	private TabActivity tabActivity;
	private TabHost tabHost;
	
	public TabMessageListener(TabHost tabHost, TabActivity activity){
		this.tabHost = tabHost;
		this.tabActivity = activity;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	//	Log.d("orm", "ischecked");
		if(isChecked){
			switch (buttonView.getId()) {
			case R.id.statistics:
				Log.d("orm", "ischecked");
				tabHost.setCurrentTabByTag(TabValues.STATISTICS);
				break;
			case R.id.analysis:
				Log.d("orm", "ischecked");
				tabHost.setCurrentTabByTag(TabValues.ANALYSIS);
				break;
			case R.id.records:
				Log.d("orm", "ischecked");
				tabHost.setCurrentTabByTag(TabValues.RECORDS);
				break;
			default:
				break;
			}
		}
		
	}
	
	
}
