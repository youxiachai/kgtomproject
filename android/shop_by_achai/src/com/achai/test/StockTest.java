package com.achai.test;

import java.sql.SQLException;

import android.os.Bundle;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Product;
import com.achai.shop.model.Stock;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class StockTest extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Product product = new Product();
		// product.setProductId(001);
		// product.setProductName("测试用包");

		// stock.setProduct(product);

		try {
			Dao<Stock, Integer> stockDao = getHelper().getStockDao();
			if (stockDao != null) {
				Stock stock = new Stock();
				Product product = new Product();
				product.setProductId(001);
				product.setProductName("测试用包");
				stock.setInstock(10);
				stock.setProduct(product);
				stockDao.create(stock);
				TextView tv = new TextView(this);
				tv.setText("ok");
				setContentView(tv);
			} else {
				TextView tv = new TextView(this);
				tv.setText("null");
				setContentView(tv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
