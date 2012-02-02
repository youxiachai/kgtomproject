
package com.achai;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.achai.shop.dao.DatabaseHelper;
import com.achai.shop.model.Category;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class CategoryTestActivity extends OrmLiteBaseActivity<DatabaseHelper> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//RuntimeExceptionDao<Category, Integer> categoryDao = getHelper().getCatoryDao();
//		String[] categories = {"拉箱","背囊","手提包","其他"};
//		
//		for(int i=0; i < categories.length; i++){
//			Category category = new Category();
//			category.name = categories[i];
//			Log.d("orm", "name: "+category.name);
//			categoryDao.create(category);
//		}
		
		
		//查询
//		String name = null;
//		List<Category> list = categoryDao.queryForAll();
////		Category categoryUpdate = list.get(0);
////		categoryUpdate.name = "修改";
////		categoryDao.update(categoryUpdate);
//		Category categoryDelete = list.get(0);
//		categoryDao.delete(categoryDelete);
//	
//		List<Category> list2 = categoryDao.queryForAll();
//		for (Category category : list2) {
//			Log.d("orm", category.name);
//			 name = category.name;
//		}
		
		TextView tv = new TextView(this);
//		tv.setText("ok:" + name);
		setContentView(tv);
		
	}
}
