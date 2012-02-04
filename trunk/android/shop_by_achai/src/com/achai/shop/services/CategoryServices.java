package com.achai.shop.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.dao.CategoryDao;
import com.achai.shop.model.Category;
import com.achai.shop.utils.DialogFactory;
import com.j256.ormlite.dao.Dao;

public class CategoryServices extends AbstractServices {


	Dao<Category, Integer> categoryDao = null;
	List<Category> category_list = null;
	ListView categoryLv;
	Button addButton;

	public List<Category> getCategory_list() {
		return category_list;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.records_list);
		categoryLv = getListView();
		addButton = (Button) findViewById(R.id.record_add);
		addButton.setText(R.string.add_category);
		addButton.setOnClickListener(new AddRecordListeing(this));
		showlist();

	}

	private TextView tv = null;
	String showcategory = null;

	@Override
	protected Dialog onCreateDialog(int id) {

		LayoutInflater factory = null;
		View categoryView = null;
		switch (id) {
		case DialogFactory.RECORD_ADD:
			// 添加一个自定义对话框
			factory = LayoutInflater.from(this);
			categoryView = factory.inflate(R.layout.record_category, null);

			tv = (TextView) categoryView.findViewById(R.id.edit_category);
			ArrayList<TextView> categoryTextView = new ArrayList<TextView>();
			categoryTextView.add(tv);
			// showcategory = (String) tv.getText();

			return DialogFactory.createDialog(this, "", categoryView,
					categoryTextView, DialogFactory.RECORD_ADD,null);
		default:
			break;
		}

		return super.onCreateDialog(id);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		LayoutInflater factory = null;
		Category c = null;
		View categoryView = null;
		switch (id) {
		case DialogFactory.RECORD_UPDATE_DELETE:
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
			// 添加一个自定义对话框
			factory = LayoutInflater.from(this);
			categoryView = factory.inflate(R.layout.record_category, null);
			ArrayList<TextView> categoryTextView = new ArrayList<TextView>();
			
			
			
			try {
			Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
			
			 c = category_list.get(args.getInt("position"));
			tv = (TextView) categoryView.findViewById(R.id.edit_category);
			tv.setText(c.getName());
			categoryTextView.add(tv);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return DialogFactory.createDialog(this, "修改", categoryView, categoryTextView, DialogFactory.RECORD_UPDATE_DELETE,c);

		default:
			break;
		}

		return super.onCreateDialog(id, args);
	}

	@Override
	public boolean create(Object obj) {
		Log.d("orm", obj.getClass().getName());
		try {
			categoryDao = getHelper().getCategoryDao();
			CategoryDao cDao = new CategoryDao(categoryDao);
			cDao.insert(obj);
			showlist();
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
			showlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Object obj) {
		try {
			Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
			CategoryDao cDao = new CategoryDao(categoryDao);
			cDao.update(obj);		
			showlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
