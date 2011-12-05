package cn.meitong.tab.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import cn.meitong.R;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.TitleManager;
import cn.meitong.values.ResultValues;

public class BackToResults extends ListActivity {

	private String resultsOfString;
	private int[] resultStringTitle = new int[] { R.string.Invoiceid,
			R.string.Invoicenumber, R.string.ordername,
			R.string.ordercertificalnumber, R.string.payname,
			R.string.paycertificalnumber, R.string.Invoicedate,
			R.string.Invoiceamount };
	ArrayList<Invoice> ada = new ArrayList<Invoice>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.backtoresults);
		// Intent intent = getIntent();
		//用于处理传过来的值,以及格式化字符串
		setResultString(getIntent());

		
		//显示
		MyAdapter myAdapter = new MyAdapter(this, R.layout.result_item, ada);

		this.setListAdapter(myAdapter);
	}

	public void setResultString(final Intent intent) {
		if(intent != null){
		int type = intent.getIntExtra(ResultValues.QueryType.QUERYTYPE, 0);
		switch (type) {
		case ResultValues.QueryType.normal:

			break;
		case ResultValues.QueryType.qRcode:
			resultsOfString = intent
					.getStringExtra(ResultValues.QueryKey.QRCODE);
			inputData();
			break;
		case ResultValues.QueryType.phoneNumber:
			break;
		case ResultValues.QueryType.enrolment:
			break;
		default:
			break;
		}
		}

	}

	public void showResult() {
		
	}

	// 用于管理显示按钮
	public void showButton(boolean isShow) {
		
	}
	

	private void inputData() {
		if (resultsOfString == null) {
			for (int i = 0; i < 8; i++) {
				set(i, "");
			}
		} else {
			String[] args = resultsOfString.split("\\|");
			for (int i = 0; i < 8; i++) {
				switch (i) {
				case 0:
					set(i, args[4]);
					break;
				case 1:
					set(i, args[5]);
					break;
				case 7:
					set(i, args[3]);
					break;
				default:
					set(i, "");
					break;
				}
			}
		}
	}

	public void set(int position, String value) {
		Invoice inv = new Invoice(getResources().getString(
				resultStringTitle[position]), value);
		ada.add(inv);
	}

	public void outputString(String string) {
		String string1 = string.replace("|", "z");
		Log.d("isRun", string1);
		String[] args = string1.split("z");
		// for(int i = 0; i <args.length; i++)
		Log.i("isRun", args[1]);
		Log.d("isRun", "" + args);
	}
}
