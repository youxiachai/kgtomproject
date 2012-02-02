package com.achai.shop.utils;

import android.content.Context;
import android.content.Intent;
import android.widget.TabHost;

public class TabsUtils {
	public static TabHost.TabSpec buildTabSpec(TabHost tabHost,Context context, String tag, int resLabel, int resIcon, final Intent intent){
		return tabHost.newTabSpec(tag)
				.setIndicator(context.getString(resLabel),context.getResources().getDrawable(resIcon))
				.setContent(intent);
	}
	
	public static TabHost.TabSpec buildInvisibleTabSpec(TabHost tabHost,String tag, final Intent intent){
		return tabHost.newTabSpec(tag).setIndicator("").setContent(intent);
	}
	//初始化intent
	public static Intent setIntentClass(Context context,Class<?> className){
		Intent intent = new Intent().setClass(context, className);
		return intent;
	}


}
