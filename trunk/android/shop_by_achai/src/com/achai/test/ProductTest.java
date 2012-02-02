package com.achai.test;

import java.sql.SQLException;

import android.os.Bundle;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Product;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class ProductTest extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			Dao<Product, Integer> proDao = getHelper().getProductDao();
			Product product = new Product();
			product.setProductId(111);
			proDao.create(product);
			TextView tv = new TextView(this);
			tv.setText("ok");
			setContentView(tv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
