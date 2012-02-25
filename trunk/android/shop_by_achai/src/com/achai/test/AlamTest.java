package com.achai.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.achai.shop.noitfy.UpdateStatics;

public class AlamTest extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		setContentView(tv);
		AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent();
		intent.setClass(this, UpdateStatics.class);
		PendingIntent pi = PendingIntent.getService(this, 0, intent, 0);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 1);
		Date currentTime = new Date(System.currentTimeMillis());
//		c.setTime(currentTime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		String showCurrent = sdf.format(currentTime);
		Log.d("alam", "1:"+showCurrent);
		//增加2小时
		//c.add(Calendar.HOUR, 2);
		//增加10s
	//	c.add(Calendar.SECOND, 10);
//		c.set(Calendar.HOUR_OF_DAY, 23);
//		c.set(Calendar.MINUTE, 0);
//		c.set(Calendar.SECOND, 0);
		Date twoHours = c.getTime();
		String twoHourss = sdf.format(twoHours);
		Log.d("alam","2:"+ twoHourss);
		
		//2 设置分钟数
//		SimpleDateFormat sdf2 = new SimpleDateFormat("k:mm");
//		String setTime = "14:09";
	
//			Date setTimeDate = sdf2.parse(setTime);
//			String showSettime = sdf.format(setTimeDate);
//			Log.d("alam","3:"+ showSettime);
			am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
	
//		c.setTime();
		
		
		
	}
	
	

}
