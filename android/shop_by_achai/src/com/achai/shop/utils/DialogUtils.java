package com.achai.shop.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.model.Category;
import com.achai.shop.services.CategoryServices;
import com.achai.shop.services.IServices;

public class DialogUtils {
	public static final int RECORD_CATEGORY = 1;
	public static final int RECORD_SUPPLIER = 2;
	public static final int RECORD_PRODUCT = 3;
	public static final int RECORD_STOCK = 4;
	public static final int RECORD_SALES = 5;
	
	public static final int RECORD_ADD = 0;
	public static final int RECORD_UPDATE =1;
	public static final int RECORD_DELETE = 2;
	
	public static final String CATEGORY = "CategoryServices";
	//对textView 用List<TextView> 来保存
	public static Dialog createDialog(Activity shopServices,String title,View view,final ArrayList<TextView> tv,final int type){
		final IServices is = (IServices) shopServices;
		final String className = shopServices.getClass().getSimpleName();
		Log.d("orm", "添加类别"+is.getClass().getName());
		Log.d("dialog","TextView"+ tv.size());
		return new AlertDialog.Builder(shopServices)
		.setTitle("添加类别")
		.setView(view)
		.setPositiveButton(R.string.record_confirm, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				switch (type) {
				case DialogUtils.RECORD_ADD:
					ArrayList<String> as = new ArrayList<String>();
					
					for (TextView textView : tv) {
						String text = textView.getText().toString();
						as.add(text);
					}
					selectServices(className,as, is);
					break;

				default:
					break;
				}
				
				
			}
		})
		.setNegativeButton(R.string.record_cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.create();
	}
	
	public static boolean selectServices(String className,ArrayList<String> text,IServices is){
		Log.d("dialog", "ok-------");
		if(className.equals(DialogUtils.CATEGORY)){
			String name = text.get(0);
			Log.d("dialog", "name2-------"+name);
		//	String text = tv.getText().toString();
			Category cg = new Category();
			if(name.equals("") != true){
				Log.d("dialog", "add-------"+name);
				cg.setName(name.trim());
				is.create(cg);
			}
		}
		return true;
	}
}
