
package com.achai.shop.dao;

import java.sql.SQLException;

import android.R.integer;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.achai.shop.model.Category;
import com.achai.shop.model.Product;
import com.achai.shop.model.Sales;
import com.achai.shop.model.Stock;
import com.achai.shop.model.Supplier;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	private static final String DATABASE_NAME = "shop.db";
	private static final int DATABASE_VERSION =2;
	
	//创建操作用表用的dao
	private Dao<Category, Integer> categoryDao = null;
	private Dao<Supplier, Integer> supplierDao = null;
	private Dao<Product, Integer> productDao = null;
	private Dao<Stock, Integer> stockDao = null;
	private Dao<Sales, Integer> salesDao = null;
	
	//private RuntimeExceptionDao<Category, Integer> categoryRuntimeDao = null;
	
	
	public Dao<Sales, Integer> getSalesDao() throws SQLException {
		if(salesDao == null){
			salesDao = getDao(Sales.class);
		}
		
		return salesDao;
	}

	public Dao<Category, Integer> getCategoryDao() throws SQLException {
		if(categoryDao == null){
			categoryDao = getDao(Category.class);
		}
		
		return categoryDao;
	}

	public Dao<Stock, Integer> getStockDao() throws SQLException {
		Log.d("orm", "getStockDao");
		if(stockDao == null){
			stockDao = getDao(Stock.class);
		}
		return stockDao;
	}

	public Dao<Product, Integer> getProductDao() throws SQLException {
		Log.d("orm", "getProductDao");
		if(productDao == null){
			productDao = getDao(Product.class);
		}
		
		return productDao;
	}

	public Dao<Supplier, Integer> getSupplierDao() throws SQLException {
		if(supplierDao == null){
			supplierDao = getDao(Supplier.class);
		}
		
		return supplierDao;
	}

	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	//第一次创建时调用
	@Override
	public void onCreate(SQLiteDatabase database,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, Product.class);
			TableUtils.createTable(connectionSource, Category.class);
			TableUtils.createTable(connectionSource, Stock.class);
			TableUtils.createTable(connectionSource, Sales.class);
			TableUtils.createTable(connectionSource, Supplier.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
//			TableUtils.dropTable(connectionSource, Category.class, true);
//			TableUtils.dropTable(connectionSource, Product.class, true);
//			TableUtils.dropTable(connectionSource, Stock.class, true);
//			TableUtils.dropTable(connectionSource, Supplier.class, true);
			Log.d("orm", "update");
			TableUtils.createTable(connectionSource, Product.class);
			TableUtils.createTable(connectionSource, Category.class);
			TableUtils.createTable(connectionSource, Stock.class);
			TableUtils.createTable(connectionSource, Supplier.class);
			TableUtils.createTable(connectionSource, Sales.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//返回一个可操作的DAO
	public Dao<Category, Integer> getDao() throws SQLException {
		if(categoryDao == null){
			categoryDao = getDao(Category.class);
		}
		return categoryDao;
	}
	//忽略所有异常的
//	public RuntimeExceptionDao<Category, Integer> getCatoryDao(){
//		if(categoryRuntimeDao == null){
//			categoryRuntimeDao = getRuntimeExceptionDao(Category.class);
//		}
//		return categoryRuntimeDao;
//		
//	}
//	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		super.close();
		
	}
	

}
