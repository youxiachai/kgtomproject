package com.achai.shop.dao;

import java.sql.SQLException;

import com.achai.shop.model.Product;
import com.j256.ormlite.dao.Dao;

public class ProductDao implements IShopDao {
	private Dao<Product, Integer> productDao;
	
	public ProductDao(Dao<Product, Integer> productDao){
		this.productDao = productDao;
	}
	
	@Override
	public boolean insert(Object obj) throws SQLException {
		if(obj != null){
			productDao.create((Product) obj);
		}
		
		return false;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		if(obj != null){
			productDao.delete((Product) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Object obj) throws SQLException {
		if(obj != null){
			productDao.update((Product) obj);
			return true;
		}
		return false;
	}

}
