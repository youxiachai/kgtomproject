/**
 * @copyright : youxiachai
 */
package com.achai.shop.dao;

import java.sql.SQLException;

import com.achai.shop.model.Category;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-1-21
 * @time 下午10:17:11
 */
public class CategoryDao implements IShopDao {
	
	private Dao<Category, Integer> categoryDao ;
	
	
	public CategoryDao(Dao<Category,Integer> category){
		this.categoryDao = category;
	}

	/* (non-Javadoc)
	 * @see com.achai.shop.dao.IShopDao#insert(com.j256.ormlite.dao.Dao)
	 */
	@Override
	public boolean insert(Object obj) throws SQLException {
		if(obj != null){
			categoryDao.create((Category) obj);
			return true;
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.achai.shop.dao.IShopDao#delete(com.j256.ormlite.dao.Dao)
	 */
	@Override
	public boolean delete(Object obj) throws SQLException {
		if(obj != null){
			categoryDao.delete((Category) obj);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Object obj) throws SQLException {
		if(obj != null){
			categoryDao.update((Category) obj);
			return true;
		}
		return false;
	}

}
