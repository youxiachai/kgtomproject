/**
 * @copyright : youxiachai
 */
package com.achai.shop.noitfy;

import com.achai.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;

/**
 * @author Administrator
 * @date 2012-2-18
 * @time 下午9:16:42
 */
public class NotificationsFactory {
	
	private Context mContext;
	private NotificationManager nm;
	
	public NotificationsFactory(Context context){
		this.mContext = context;
		this.nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	public void createSuccess(String title, String contextText,PendingIntent pending){
		//1, 初始化提示信息
		Notification noSuccess = new Notification(R.drawable.ic_launcher, title, System.currentTimeMillis());
		//2, 设置提示内容
		noSuccess.setLatestEventInfo(mContext, title, contextText, pending);
		//3, 设置标识
		noSuccess.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONLY_ALERT_ONCE;
		noSuccess.defaults = Notification.DEFAULT_SOUND;
		nm.notify(1234, noSuccess);
	}
	
}
