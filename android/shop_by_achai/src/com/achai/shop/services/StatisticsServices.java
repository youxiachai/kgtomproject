package com.achai.shop.services;

import com.achai.shop.chart.BarChartValues;
import com.achai.shop.dao.DatabaseHelper;
import com.j256.ormlite.android.apptools.OrmLiteBaseService;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2012-2-6
 * @time 下午2:11:44
 */
public class StatisticsServices extends OrmLiteBaseService<DatabaseHelper> {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String year = intent.getStringExtra(BarChartValues.STAT_YEAR);
		Log.d("services", "year-->"+ year);		
		return super.onStartCommand(intent, flags, startId);
	}
	


}
