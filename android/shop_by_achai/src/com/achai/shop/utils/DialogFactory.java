package com.achai.shop.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.model.Category;
import com.achai.shop.model.Product;
import com.achai.shop.model.Supplier;
import com.achai.shop.services.IServices;
import com.achai.shop.services.ProductServices;
import com.achai.shop.services.SalesServices;
import com.achai.shop.services.StockServices;
import com.achai.shop.services.SupplierServices;

/**
 * @author Tom_achai
 * @date 2012-2-3
 * @time 下午5:53:27
 */
public class DialogFactory {
	public static final int RECORD_CATEGORY = 1;
	public static final int RECORD_SUPPLIER = 2;
	public static final int RECORD_PRODUCT = 3;
	public static final int RECORD_STOCK = 4;
	public static final int RECORD_SALES = 5;

	public static final int RECORD_ADD = 0;

	public static final int RECORD_UPDATE_DELETE = 3;

	public static final String POSITION = "position";

	public static final String CATEGORY = "CategoryServices";
	static int mCategoryPos = 0;
	static int mSupplierPos = 0;

	public static Dialog createProductDialog(final Activity shopServices,
			String title, View view, final int type,final Product product) {
		final IServices is = (IServices) shopServices;
		final TextView productName = (TextView) view
				.findViewById(R.id.dialog_product_name);
		final TextView productPrice = (TextView) view
				.findViewById(R.id.dialog_product_price);
		final Spinner productCategory = (Spinner) view
				.findViewById(R.id.dialog_product_category);
		List<Category> categorys = ((ProductServices) shopServices)
				.getCategorylist();
		ArrayAdapter<Category> productCategoryAdapter = new ArrayAdapter<Category>(
				shopServices, R.layout.items, R.id.tv, categorys);

		productCategory.setAdapter(productCategoryAdapter);

		List<Supplier> suppliers = ((ProductServices) shopServices)
				.getSupplierlist();
		final Spinner productSupplier = (Spinner) view
				.findViewById(R.id.dialog_product_supplier);
		ArrayAdapter<Supplier> productSupplierAdapter = new ArrayAdapter<Supplier>(
				shopServices, R.layout.items, R.id.tv, suppliers);
		productSupplier.setAdapter(productSupplierAdapter);

		if (product != null) {
			productName.setText(product.getProductName());
			productPrice.setText(String.valueOf(product.getPrice()));
			int i = 0;
			Log.d("dialog", "productCategoryName"+product.getCategory().getName());
			Log.d("dialog", "productCategoryName"+product.getSupplier().getName());
			for (Category category : categorys) {
				if (category.getName().equals(product.getCategory().getName())) {
					productCategory.setSelection(i);
				}
				i++;
			}
			int j = 0;
			for (Supplier supplier : suppliers) {
				if (supplier.getName().equals(product.getSupplier().getName())) {
					productSupplier.setSelection(j);
				}
				j++;
			}
		}

		// -------设置监听器
		productSupplier.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				mSupplierPos = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});
		productCategory.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				mCategoryPos = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
		return new AlertDialog.Builder(shopServices)
				.setTitle(title)
				.setView(view)
				.setPositiveButton(R.string.record_confirm,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ArrayList<String> list = new ArrayList<String>();
								Category currentCategory;
								Supplier currentSupplier;
								String name;
								String price;
								switch (type) {
								case DialogFactory.RECORD_ADD:
									currentCategory = (Category) productCategory
											.getAdapter().getItem(mCategoryPos);
									currentSupplier = (Supplier) productSupplier
											.getAdapter().getItem(mSupplierPos);
									name = productName.getText().toString();
									price = productPrice.getText().toString();
									if ((name.equals("") | price.equals("")) != true) {
										Product addProduct = new Product();
										addProduct.setId(1003);
										addProduct.setProductName(name);
										addProduct.setPrice(Double.valueOf(price));
										addProduct.setCategory(currentCategory);
										addProduct.setSupplier(currentSupplier);
										Log.d("dialog", "-->"
												+ addProduct.getCategory()
														.getName());
										is.create(addProduct);
									}
									shopServices.removeDialog(DialogFactory.RECORD_ADD);
									break;
								case DialogFactory.RECORD_UPDATE_DELETE:
									currentCategory = (Category) productCategory
											.getAdapter().getItem(mCategoryPos);
									currentSupplier = (Supplier) productSupplier
											.getAdapter().getItem(mSupplierPos);
									name = productName.getText().toString();
									price = productPrice.getText().toString();
									if ((name.equals("") | price.equals("")) != true) {
										Product updatePoduct = product;

										updatePoduct.setProductName(name);
										updatePoduct.setPrice(Double.valueOf(price));
										updatePoduct.setCategory(currentCategory);
										updatePoduct.setSupplier(currentSupplier);
										Log.d("dialog", "-->"
												+ updatePoduct.getCategory()
														.getName());
										is.update(updatePoduct);
									}
									
									
									shopServices.removeDialog(DialogFactory.RECORD_UPDATE_DELETE);
									break;
								default:
									break;
								}

							}
						})
				.setNegativeButton(R.string.record_cancel,
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						}).create();
	}
	
	// 对textView 用List<TextView> 来保存
	public static Dialog createDialog(final Activity shopServices, String title,
			View view, final ArrayList<TextView> tv, final int type,
			final Object obj) {
		
		final IServices is = (IServices) shopServices;
		final String className = shopServices.getClass().getSimpleName();

		;
		Log.d("orm", "添加类别" + is.getClass().getName());
		Log.d("dialog", "TextView" + tv.size());
		return new AlertDialog.Builder(shopServices)
				.setTitle(title)
				.setView(view)
				.setPositiveButton(R.string.record_confirm,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ArrayList<String> as = new ArrayList<String>();
								switch (type) {
								case DialogFactory.RECORD_ADD:
									as = checkEmpty(as, tv);
									if (as != null) {
										addServices(className, as, is);
									}
									shopServices.removeDialog(DialogFactory.RECORD_ADD);
									break;
								case DialogFactory.RECORD_UPDATE_DELETE:
									as = checkEmpty(as, tv);
									if (as != null) {
										updateServices(className, as, is, obj);
									}
									Log.d("dialog", "onclick!!!");
									shopServices.removeDialog(DialogFactory.RECORD_UPDATE_DELETE);
									break;
								default:
									break;
								}
								
							}

						})
				.setNegativeButton(R.string.record_cancel,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								switch (type) {
								case DialogFactory.RECORD_UPDATE_DELETE:
									deleteServices(is, obj);
									shopServices.removeDialog(DialogFactory.RECORD_UPDATE_DELETE);
									break;

								default:
									break;
								}
							}
						}).create();
	}

	public static boolean updateServices(String className,
			ArrayList<String> text, IServices is, Object obj) {
		if (className.equals(DialogFactory.CATEGORY)) {
			String updateName = text.get(0);
			if (updateName.equals("") != true) {
				Category c = (Category) obj;
				c.setName(updateName);
				is.update(c);
				return true;
			}
		} else if (className.equals(SupplierServices.class.getSimpleName())) {
			String supplierName = text.get(0);
			String supplierPhone = text.get(1);

			Supplier supplier = (Supplier) obj;
			supplier.setName(supplierName);
			supplier.setPhone(supplierPhone);
			supplier.setLastSupply(new Date(System.currentTimeMillis()));
			is.update(supplier);
			return true;

		}

		return false;

	}

	public static boolean deleteServices(IServices is, Object obj) {
		is.delete(obj);
		return false;

	}

	public static boolean addServices(String className, ArrayList<String> text,
			IServices is) {
		Log.d("dialog", "ok-------");
		if (className.equals(DialogFactory.CATEGORY)) {
			String name = text.get(0);
			Log.d("dialog", "name2-------" + name);
			// String text = tv.getText().toString();
			Category cg = new Category();
			if (name.equals("") != true) {
				Log.d("dialog", "add-------" + name);
				cg.setName(name.trim());
				is.create(cg);
			}
		} else if (className.equals(SupplierServices.class.getSimpleName())) {
			String name = text.get(0);
			String phone = text.get(1);

			Log.d("add", "name:" + name + "phone" + phone);
			Supplier supplier = new Supplier(System.currentTimeMillis());
			supplier.setName(name.trim());
			supplier.setPhone(phone.trim());
			is.create(supplier);

		} else if (className.equals(ProductServices.class.getSimpleName())) {
			// 货物处理
		} else if (className.equals(SalesServices.class.getSimpleName())) {
			// 销售处理
		} else if (className.equals(StockServices.class.getSimpleName())) {
			// 库存处理
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 检查字段是否为空
	 * 
	 * @param list
	 * @param tv
	 * @return
	 */
	public static ArrayList<String> checkEmpty(ArrayList<String> list,
			final ArrayList<TextView> tv) {
		for (TextView textView : tv) {

			String text = textView.getText().toString();
			Log.d("dialog", "update" + text);
			if (text.equals("")) {
				return null;
			}
			list.add(text);
		}
		return list;
	}

}
