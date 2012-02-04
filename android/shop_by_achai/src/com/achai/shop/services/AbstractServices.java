package com.achai.shop.services;



import com.achai.shop.dao.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.app.ListActivity;

public abstract class AbstractServices extends ListActivity implements IServices {
	//1.创建一个ORM 的helper
	private DatabaseHelper databaseHelper = null;
	@Override
	protected void onDestroy() {
		// 清除 helper
		super.onDestroy();
		if(databaseHelper != null){
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
	protected DatabaseHelper getHelper() {
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		return databaseHelper;
	}
	
	
}
