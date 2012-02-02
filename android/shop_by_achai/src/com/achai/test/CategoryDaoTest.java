package com.achai.test;

import java.sql.SQLException;

import android.os.Bundle;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Category;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;

public class CategoryDaoTest extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try {
			// CategoryDao categoryDao = new
			// CategoryDao(getHelper().getCategoryDao());
			Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
			Category category = new Category();
			category.setName("ok");
			categoryDao.create(category);
			
			TextView tv = new TextView(this);
			tv.setText("ok");
			setContentView(tv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			TextView tv = new TextView(this);
			tv.setText("null");
			setContentView(tv);
			e.printStackTrace();
		}

	}
}
