/**
 * @copyright : youxiachai
 */
package com.achai.shop.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-1-21
 * @time 下午10:06:46
 */
public interface IShopDao {
	
	boolean insert(Object obj) throws SQLException;
	
	boolean delete(Object obj) throws SQLException;
	
	boolean update(Object obj) throws SQLException;

}
