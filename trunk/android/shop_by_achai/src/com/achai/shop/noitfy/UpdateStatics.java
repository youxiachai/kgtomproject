package com.achai.shop.noitfy;

import java.sql.SQLException;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.achai.R;
import com.achai.shop.backup.BackupTask;
import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Statistics;
import com.achai.shop.view.StatisticsAcitvity;
import com.j256.ormlite.android.apptools.OrmLiteBaseService;
import com.j256.ormlite.dao.Dao;

public class UpdateStatics extends OrmLiteBaseService<DatabaseHelper> {
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("stat", "ok");
//		try {
//			Dao<Statistics, Integer> statDao = getHelper().getDao(Statistics.class);
//			List<Statistics> statList = statDao.queryForAll();
//			for (Statistics statistics : statList) {
//				Log.d("stat",statistics.getId());
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		BackupTask bt = new BackupTask(this);
		bt.execute(BackupTask.COMMAND_BACKUP);
		
		
		
		return START_NOT_STICKY;
	}
	

	

}
