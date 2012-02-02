package com.achai.shop.services;

import java.util.List;

import com.achai.shop.model.Category;
import com.achai.shop.utils.DialogUtils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

/**
 * @author Tom_achai
 * @date 2012-1-31
 * @time 上午12:48:44
 */
class ListItemListening implements OnItemClickListener,OnItemLongClickListener{
	
	private Activity shopServices;
	private String className;
	public ListItemListening(Activity services) {
		// TODO Auto-generated constructor stub
		this.shopServices = services;
		this.className = shopServices.getClass().getSimpleName();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d("records",position+"id:"+id);
		Log.d("list","className"+className);
		
		
			//根据类型选择操作
//			switch (position) {
//			case DialogUtils.RECORD_ADD:
//				shopServices.showDialog(DialogUtils.RECORD_ADD);
//				break;
//			default:
//				break;
//			}
	
	
		//shopServices.create();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		
		
			TextView tvlong = (TextView) view;
			Log.d("list", "className-->"+CategoryServices.class.getSimpleName());
			
			if(className.equals(CategoryServices.class.getSimpleName())){
				Log.d("list", "long"+tvlong.getText().toString()+"postiontion:"+position+"id:"+id);
				Log.d("list", "position--->"+position);
				CategoryServices cs = (CategoryServices) shopServices;
				List<Category> clist = cs.getCategory_list();
				Category c = clist.get(position);
				Log.d("list", "name"+c.getName()+c.getId());
				
				Bundle bd = new Bundle();
				bd.putInt("position", position);
				shopServices.showDialog(DialogUtils.RECORD_DELETE, bd);
		}
		return true;
	}
	


}
