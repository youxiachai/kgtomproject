package com.achai.shop.view;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Statistics extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		
		
		tv.setText("ok");
		setContentView(tv);
		
	}
}
