package com.achai.test;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Supplier;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class SupplierTest extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Dao<Supplier, Integer> supplier = null;
		try {
			supplier = getHelper().getSupplierDao();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Supplier sp = new Supplier(System.currentTimeMillis());
		sp.setName("A");
		sp.setPhone("1111111111111");
		try {
			supplier.create(sp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date spDate = null;
		try {
			List<Supplier> spList = supplier.queryForAll();
			for (Supplier supplier2 : spList) {
				Log.d("orm", "date:"+supplier2.getLastSupply()+"");
				spDate = supplier2.getLastSupply();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TextView tv = new TextView(this);
		String dateText;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		if(spDate != null){
			dateText = dateFormatter.format(spDate);
			tv.setText(dateText);
		}else {
			dateText =" date error";
			tv.setText(dateText);
		}
		setContentView(tv);
		
	}
}
