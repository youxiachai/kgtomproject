package com.achai.shop.dao;

import java.sql.SQLException;

import com.achai.shop.model.Statistics;
import com.j256.ormlite.dao.Dao;

public class StatisticsDao implements IShopDao {
	
	Dao<Statistics, Integer> statDao = null;
	
	public StatisticsDao(Dao<Statistics, Integer> dao){
		this.statDao = dao;
	}

	@Override
	public boolean insert(Object obj) throws SQLException {
		if(obj != null){
			statDao.create((Statistics) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		if(obj != null){
			statDao.delete((Statistics) obj);
			return true;
		}		
		return false;
	}

	@Override
	public boolean update(Object obj) throws SQLException {
		if(obj != null){
			statDao.update((Statistics) obj);
			return true;
		}
		return false;
	}

}
