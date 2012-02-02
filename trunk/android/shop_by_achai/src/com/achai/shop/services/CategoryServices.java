package com.achai.shop.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.dao.CategoryDao;
import com.achai.shop.model.Category;
import com.achai.shop.utils.DialogUtils;
import com.j256.ormlite.dao.Dao;

public class CategoryServices extends AbstractServices {

	ArrayList<TextView> categoryTextView = new ArrayList<TextView>();
	Dao<Category, Integer> categoryDao = null;
	List<Category> category_list = null;
	ListView categoryLv;

	public List<Category> getCategory_list() {
		return category_list;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		categoryLv = getListView();
		showlist();

	}

	private TextView tv = null;
	String showcategory = null;

	@Override
	protected Dialog onCreateDialog(int id) {

		LayoutInflater factory = null;
		View categoryView = null;
		switch (id) {
		case DialogUtils.RECORD_ADD:
			// 添加一个自定义对话框
			factory = LayoutInflater.from(this);
			categoryView = factory.inflate(R.layout.record_category, null);

			tv = (TextView) categoryView.findViewById(R.id.edit_category);
			categoryTextView.add(tv);
			// showcategory = (String) tv.getText();

			return DialogUtils.createDialog(this, "", categoryView,
					categoryTextView, DialogUtils.RECORD_ADD);
		default:
			break;
		}

		return super.onCreateDialog(id);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		switch (id) {
		case DialogUtils.RECORD_DELETE:
			Log.d("dialog", "dialog" + args.getInt("position"));
			//测试删除
			
//			try {
//				Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
//				CategoryDao cDao = new CategoryDao(categoryDao);
//				Category c = category_list.get(args.getInt("position"));
//				cDao.delete(c);
//				showlist();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//测试更新
			try {
			Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
			CategoryDao cDao = new CategoryDao(categoryDao);
			Category c = category_list.get(args.getInt("position"));
			c.setName("更新成功");
			cDao.update(c);
			showlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			break;

		default:
			break;
		}

		return super.onCreateDialog(id, args);
	}

	@Override
	public boolean create(Object obj) {
		Log.d("orm", obj.getClass().getName());
		try {

			CategoryDao cDao = new CategoryDao(categoryDao);
			cDao.insert(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		Log.d("orm", obj.getClass().getName());
		try {
			Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
			CategoryDao cDao = new CategoryDao(categoryDao);
			cDao.delete(obj);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void showlist() {
		List<String> categorys = new ArrayList<String>();
		// categorys.add("添加");
		Dao<Category, Integer> categoryDao;
		try {
			categoryDao = getHelper().getCategoryDao();
			category_list = categoryDao.queryForAll();
			for (Category category : category_list) {
				Log.d("orm",
						"name" + category.getName() + "id" + category.getId());
				categorys.add(category.getId() + ":" + category.getName());
			}

			categoryLv.setAdapter(new ArrayAdapter<String>(this,
					R.layout.items, R.id.tv, categorys));
			categoryLv.setOnItemClickListener(new ListItemListening(this));
			categoryLv.setOnItemLongClickListener(new ListItemListening(this));

			// categoryLv.setOnLongClickListener(new OnLongClickListener() {
			//
			// @Override
			// public boolean onLongClick(View v) {
			//
			//
			// return true;
			// }
			// });

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
