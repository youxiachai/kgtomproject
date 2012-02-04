package com.achai.shop.dao;

import java.sql.SQLException;

import com.achai.shop.model.Supplier;
import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-2-3
 * @time 下午4:00:44
 */
public class SupplierDao implements IShopDao {

	private Dao<Supplier, Integer> supplierDao;

	public SupplierDao(Dao<Supplier, Integer> supplier) {
		this.supplierDao = supplier;
	}

	@Override
	public boolean insert(Object obj) throws SQLException {
		if (obj != null) {
			supplierDao.create((Supplier) obj);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		if(obj != null){
			supplierDao.delete((Supplier) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Object obj) throws SQLException {
		if(obj!=null){
			supplierDao.update((Supplier) obj);
			return true;
		}
		return false;
	}

}
