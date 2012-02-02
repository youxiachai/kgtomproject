package com.achai.shop.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.model.Category;
import com.achai.shop.services.CategoryServices;
import com.achai.shop.utils.DialogUtils;

public class Records extends Activity {

	
	private Button add_catgroy;
	private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_activity);
		lv = (ListView) findViewById(R.id.category);
		
		String [] records = getResources().getStringArray(R.array.record_all);
		lv.setAdapter(new ArrayAdapter<String>(this, R.layout.items,records));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tv = (TextView) view;
				if(tv.getText().equals("类别")){
					Intent categoryService = new Intent();
					categoryService.setClass(Records.this, CategoryServices.class);
					startActivityForResult(categoryService, position);
				}
			}
		});
		
		
		
//		add_catgroy = (Button) findViewById(R.id.add_category);
//		add_catgroy.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				showDialog(DialogUtils.RECORD_CATEGORY);
//			}
//		});
	}
	String showcategory = null;
	private TextView tv = null;
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DialogUtils.RECORD_CATEGORY:
			//添加一个自定义对话框
			LayoutInflater factory = LayoutInflater.from(this);
			final View categoryView = factory.inflate(R.layout.record_category, null);
			 tv = (TextView) categoryView.findViewById(R.id.edit_category);
			// showcategory = (String) tv.getText();
		//	return DialogUtils.createDialog(this, "", categoryView, tv);
		
		default:
			break;
		}
			
		return super.onCreateDialog(id);
	}
	//控制显示 操纵的类别
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Log.d("records", "requestCode"+requestCode+"resultCode"+resultCode);
		LayoutInflater factory = LayoutInflater.from(this);
		final View categoryView = factory.inflate(R.layout.main, null);
		LinearLayout barchart = (LinearLayout) categoryView.findViewById(R.id.barchart);
		barchart.setVisibility(View.INVISIBLE);
		//获取夫activity ,然后用 setTab 的方法进行切换
		ShopMainActivity sm = (ShopMainActivity) this.getParent();
		sm.chartView();
		Log.d("records", "ok"+this.getParent().getComponentName().getClassName());
	}
	
}
