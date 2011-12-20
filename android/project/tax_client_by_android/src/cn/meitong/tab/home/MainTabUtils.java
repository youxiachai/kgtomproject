package cn.meitong.tab.home;

import android.content.Context;
import android.content.Intent;
import android.widget.TabHost;
import android.widget.TextView;

public class MainTabUtils {

	
	//返回从intent获得的值
	public static String getIntentString(Intent intent,String key){
		String result = intent.getStringExtra(key);
		return result;
	}
	//初始化intent
	public static Intent setIntentClass(Context context,Class<?> className){
		Intent intent = new Intent().setClass(context, className);
		return intent;
	}
	
	/**
	 * 
	 *  控制更改标题文字
	 *  切换 
	 * Tab
	 * @param tabhost
	 * @param tag
	 * @param title
	 * @param titleName
	 */
	public static void changeTabAndTitle(TabHost tabhost,String tag,TextView title,int titleName){
		tabhost.setCurrentTabByTag(tag);
		title.setText(titleName);
	}
	
	
}
