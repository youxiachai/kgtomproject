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
import android.widget.TextView;

import com.achai.R;
import com.achai.shop.dao.SupplierDao;
import com.achai.shop.model.Supplier;
import com.achai.shop.utils.DialogFactory;
import com.j256.ormlite.dao.Dao;

public class SupplierServices extends AbstractServices {

	Dao<Supplier, Integer> supplierDao = null;
	public List<Supplier> supplier_list = null;
	ListView supplierLv;
	Button addButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.records_list);
		supplierLv = getListView();
		addButton = (Button) findViewById(R.id.record_add);
		addButton.setText(R.string.add_supplier);
		addButton.setOnClickListener(new AddRecordListeing(this));
		showlist();
	}

	@Override
	public boolean create(Object obj) {

		try {
			supplierDao = getHelper().getSupplierDao();
			SupplierDao sDao = new SupplierDao(supplierDao);
			sDao.insert(obj);
			showlist();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Object obj) {
		try {
		
			SupplierDao sDao = new SupplierDao(getHelper().getSupplierDao());
			sDao.delete(obj);
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
			supplierDao = getHelper().getSupplierDao();
			SupplierDao sDao = new SupplierDao(supplierDao);
			sDao.update(obj);
			showlist();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void showlist() {
		List<String> suppliers = new ArrayList<String>();
		Dao<Supplier, Integer> supplierDao;
		try {
			supplierDao = getHelper().getSupplierDao();
			supplier_list = supplierDao.queryForAll();
			for (Supplier supplier : supplier_list) {
				suppliers.add(supplier.getName() + ":" + supplier.getPhone());
			}
			supplierLv.setAdapter(new ArrayAdapter<String>(this,
					R.layout.items, R.id.tv, suppliers));
			supplierLv.setOnItemLongClickListener(new ListItemListening(this));
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	TextView supplierName;
	TextView supplierPhone;
	LayoutInflater factory = null;
	View supplierDialogView = null;

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DialogFactory.RECORD_ADD:
			// 添加自定义对话框
			factory = LayoutInflater.from(this);
			supplierDialogView = factory.inflate(R.layout.dialog_suppliers,
					null);
			supplierName = (TextView) supplierDialogView
					.findViewById(R.id.dialog_supplier_name);
			supplierPhone = (TextView) supplierDialogView
					.findViewById(R.id.dialog_supplier_phone);
			ArrayList<TextView> supplierTextView = new ArrayList<TextView>();
			supplierTextView.add(supplierName);
			supplierTextView.add(supplierPhone);

			return DialogFactory.createDialog(this, "", supplierDialogView,
					supplierTextView, DialogFactory.RECORD_ADD, null);

		default:
			break;
		}

		return super.onCreateDialog(id);
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {

		Supplier supplier = null;
		switch (id) {
		case DialogFactory.RECORD_UPDATE_DELETE:
			factory = LayoutInflater.from(this);
			supplierDialogView = factory.inflate(R.layout.dialog_suppliers,
					null);
			ArrayList<TextView> supplierTextView = new ArrayList<TextView>();
			supplier = supplier_list.get(args.getInt(DialogFactory.POSITION));
			supplierName = (TextView) supplierDialogView
					.findViewById(R.id.dialog_supplier_name);
			supplierName.setText(supplier.getName());
			supplierPhone = (TextView) supplierDialogView
					.findViewById(R.id.dialog_supplier_phone);
			supplierPhone.setText(supplier.getPhone());
			supplierTextView.add(supplierName);
			supplierTextView.add(supplierPhone);

			return DialogFactory.createDialog(this, "修改", supplierDialogView,
					supplierTextView, DialogFactory.RECORD_UPDATE_DELETE,
					supplier);

		default:
			break;
		}

		return super.onCreateDialog(id, args);
	}
}
