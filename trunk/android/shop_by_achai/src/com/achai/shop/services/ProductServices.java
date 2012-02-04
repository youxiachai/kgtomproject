package com.achai.shop.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.dao.ProductDao;
import com.achai.shop.model.Category;
import com.achai.shop.model.Product;
import com.achai.shop.model.Supplier;
import com.achai.shop.utils.DialogFactory;
import com.j256.ormlite.dao.Dao;

/**
 * @author Tom_achai
 * @date 2012-1-31
 * @time 上午1:01:13
 */
public class ProductServices extends AbstractServices {

	Dao<Product, Integer> productDao = null;
	public List<Product> product_list = null;
	ListView productLv;
	Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.records_list);
		productLv = getListView();
		addButton = (Button) findViewById(R.id.record_add);
		addButton.setText(R.string.add_product);
		addButton.setOnClickListener(new AddRecordListeing(this));
		showlist();

	}

	@Override
	public boolean create(Object obj) {
		
		try {
			ProductDao productdao = new ProductDao(getHelper().getProductDao());
			productdao.insert(obj);
			showlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object obj) {
		try {
			ProductDao productDao = new ProductDao(getHelper().getProductDao());
			productDao.update(obj);
			showlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	private void productDao(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public List<Category> getCategorylist(){
		
		try {
			Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
			List<Category> category_list = categoryDao.queryForAll();
			return category_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Supplier> getSupplierlist(){
	
		try {
			Dao<Supplier, Integer> supplierDao = getHelper().getSupplierDao();
			List<Supplier> supplier_list = supplierDao.queryForAll();
		
			return supplier_list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void showlist() {
		List<String> products = new ArrayList<String>();
		try {
			productDao = getHelper().getProductDao();
			product_list = productDao.queryForAll();
			for (Product product : product_list) {
				Dao<Category, Integer> categoryDao = getHelper().getCategoryDao();
				Dao<Supplier, Integer> supplierDao = getHelper().getSupplierDao();
				categoryDao.refresh(product.getCategory());
				supplierDao.refresh(product.getSupplier());
				products.add(product.getProductName() + ":"
						+ product.getCategory().getName() + ":"
						+ product.getPrice()+":"+product.getSupplier().getName());
			}
			productLv.setAdapter(new ArrayAdapter<String>(this, R.layout.items,
					R.id.tv, products));
			productLv.setOnItemLongClickListener(new ListItemListening(this));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	LayoutInflater factory = null;
	View productDialogView = null;
	TextView productName;
	TextView productPrice;
	Spinner productCategory;
	Spinner productSupplier;

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DialogFactory.RECORD_ADD:
			if(factory == null){
				factory = LayoutInflater.from(this);
			}

			if(productDialogView == null){
				productDialogView = factory.inflate(R.layout.dialog_products, null);
			}

			return DialogFactory.createProductDialog(this, "",
					productDialogView, DialogFactory.RECORD_ADD, null);

		default:
			break;
		}
		return super.onCreateDialog(id);
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		// TODO Auto-generated method stub
		
		switch (id) {
		case DialogFactory.RECORD_UPDATE_DELETE:
		
				factory = LayoutInflater.from(this);
		
			
		
				productDialogView = factory.inflate(R.layout.dialog_products, null);
		
			Product product = product_list.get(args.getInt(DialogFactory.POSITION));
			return DialogFactory.createProductDialog(this, "",
					productDialogView, DialogFactory.RECORD_UPDATE_DELETE, product);

		default:
			break;
		}
		
		return super.onCreateDialog(id, args);
	}
}
