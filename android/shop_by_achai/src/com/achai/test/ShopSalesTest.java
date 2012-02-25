/**
 * @copyright : youxiachai
 */
package com.achai.test;

import java.sql.SQLException;
import java.util.List;

import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings.System;
import android.util.Log;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Product;
import com.achai.shop.model.Sales;
import com.achai.shop.model.SalesTest;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-1-21
 * @time 上午12:42:34
 */
public class ShopSalesTest extends OrmLiteBaseActivity<DatabaseHelper>{

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			try {
				Dao<SalesTest, Integer> salesDao = getHelper().getDao(SalesTest.class);
				long before = java.lang.System.currentTimeMillis();
				Log.d("test", "before" + before);
				List<SalesTest> sales_list = salesDao.queryForAll();
				long after = java.lang.System.currentTimeMillis();
				Log.d("test", "after" +(after - before));
				for (SalesTest salesTest : sales_list) {
					Log.d("orm", "productName-->"+salesTest.getProduct_name());
				}
			//	Dao<Product, Integer> productDao = getHelper().getProductDao();
//				Product product = new Product();
//				product.setId(1001);
//				product.setPrice(100.0);
//				product.setProductId(111);
//				productDao.create(product);
			//	Product product1 = productDao.queryForId(1001);
			//	Sales sale = new Sales(System.currentTimeMillis());
//				sale.setProduct(product1);
//				sale.setPrice(50.2);
//				sale.setProfit(60.3);
//				salesDao.create(sale);
				TextView tv = new TextView(this);
				tv.setText("ok");
				setContentView(tv);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
