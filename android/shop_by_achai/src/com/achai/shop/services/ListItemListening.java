package com.achai.shop.services;

import java.util.List;

import com.achai.shop.model.Category;
import com.achai.shop.model.Product;
import com.achai.shop.model.Supplier;
import com.achai.shop.utils.DialogFactory;
import com.achai.shop.view.Records;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;

/**
 * @author Tom_achai
 * @date 2012-1-31
 * @time 上午12:48:44
 */
public class ListItemListening implements OnItemClickListener,
		OnItemLongClickListener {

	private Activity shopServices;
	private String className;

	public ListItemListening(Activity services) {
		// TODO Auto-generated constructor stub
		this.shopServices = services;
		this.className = shopServices.getClass().getSimpleName();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.d("records", position + "id:" + id);
		Log.d("list", "className" + className);
		TextView tv = (TextView) view;
		if (tv.getText().equals("类别")) {
			Intent categoryService = new Intent();
			categoryService.setClass(shopServices, CategoryServices.class);
			shopServices.startActivityForResult(categoryService, position);
		} else if (tv.getText().equals("供应商")) {
			Intent supplierService = new Intent();
			supplierService.setClass(shopServices, SupplierServices.class);
			shopServices.startActivityForResult(supplierService, position);
		}else if (tv.getText().equals("货物")){
			Intent productService = new Intent();
			productService.setClass(shopServices, ProductServices.class);
			shopServices.startActivityForResult(productService, position);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		TextView tvlong = (TextView) view;
		Log.d("list", "className-->" + CategoryServices.class.getSimpleName());
		Bundle bd = null;
		if (className.equals(CategoryServices.class.getSimpleName())) {
		
			Log.d("list", "long" + tvlong.getText().toString() + "postiontion:"
					+ position + "id:" + id);
			Log.d("list", "position--->" + position);
			CategoryServices cs = (CategoryServices) shopServices;
			List<Category> clist = cs.getCategory_list();
			Category c = clist.get(position);
			Log.d("list", "name" + c.getName() + c.getId());

			 bd = new Bundle();
			bd.putInt("position", position);
			shopServices.showDialog(DialogFactory.RECORD_UPDATE_DELETE, bd);
			return true;
		}else if(className.equals(SupplierServices.class.getSimpleName())){
			SupplierServices ss = (SupplierServices) shopServices;
			List<Supplier> slist = ss.supplier_list;
			Supplier supplier = slist.get(position);
			Log.d("list", "position-->"+position);
			bd = new Bundle();
			bd.putInt(DialogFactory.POSITION, position);
			shopServices.showDialog(DialogFactory.RECORD_UPDATE_DELETE, bd);
			return true;
		}else if(className.equals(ProductServices.class.getSimpleName())){
			ProductServices ps = (ProductServices) shopServices;
			List<Product> plist = ps.product_list;
			bd = new Bundle();
			bd.putInt(DialogFactory.POSITION, position);
			shopServices.showDialog(DialogFactory.RECORD_UPDATE_DELETE, bd);
			return true;
		}
		return false;
	}

}
