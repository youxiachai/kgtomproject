/**
 * @copyright : youxiachai
 */
package com.achai.test;

import java.sql.SQLException;

import android.os.Bundle;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Product;
import com.achai.shop.model.Sales;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-1-21
 * @time 上午12:42:34
 */
public class SalesTest extends OrmLiteBaseActivity<DatabaseHelper>{

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			try {
				Dao<Sales, Integer> salesDao = getHelper().getSalesDao();
				Dao<Product, Integer> productDao = getHelper().getProductDao();
//				Product product = new Product();
//				product.setId(1001);
//				product.setPrice(100.0);
//				product.setProductId(111);
//				productDao.create(product);
				Product product1 = productDao.queryForId(1001);
				Sales sale = new Sales(System.currentTimeMillis());
				sale.setProduct(product1);
				sale.setPrice(50.2);
				sale.setProfit(60.3);
				salesDao.create(sale);
				TextView tv = new TextView(this);
				tv.setText("ok");
				setContentView(tv);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
