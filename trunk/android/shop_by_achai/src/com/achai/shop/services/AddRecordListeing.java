package com.achai.shop.services;

import com.achai.shop.utils.DialogFactory;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class AddRecordListeing implements OnClickListener {
	
	private Activity shopServices;
	
	
	public AddRecordListeing(Activity services){
		this.shopServices = services;
	}
	
	@Override
	public void onClick(View v) {
		Log.d("add", "name"+shopServices.getClass().getSimpleName());
		shopServices.showDialog(DialogFactory.RECORD_ADD);
	}

}
