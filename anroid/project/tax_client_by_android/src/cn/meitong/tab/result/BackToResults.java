package cn.meitong.tab.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import cn.meitong.R;
import cn.meitong.model.ResponseInvoke;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.TitleManager;
import cn.meitong.values.ResultValues;

public class BackToResults extends ListActivity {

	private String resultsOfString;
	private int[] resultStringTitleOfCheck = new int[] { R.string.Invoiceid,
			R.string.Invoicenumber, R.string.ordername,
			R.string.ordercertificalnumber, R.string.payname,
			R.string.paycertificalnumber, R.string.Invoicedate,
			R.string.Invoiceamount ,
			R.string.fiscalnumber,
			R.string.orderphonenumber,
			R.string.prizelevel,
			R.string.prizeaccount,
			R.string.prizepriods,
			R.string.checktime,
			R.string.Invokestute
	};
	private String[] resultStringTitleOfQRcode = new String[] {
		"版本号","防伪码","开票日期","金额","发票代码","发票号码","纳税人识别号","开票人"
	};
	int[] title;
	ArrayList<Invoice> ada ;
	
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
	    showButton(false);
		if(intent != null){
		int type = intent.getIntExtra(ResultValues.QueryType.QUERYTYPE, 0);
		switch (type) {
		case ResultValues.QueryType.normal:
		    	showButton(false);
		    	ResponseInvoke  invoke = (ResponseInvoke) intent.getSerializableExtra("");
		    	setnormal(invoke);
			break;
		case ResultValues.QueryType.qRcode:
			resultsOfString = intent
					.getStringExtra(ResultValues.QueryKey.QRCODE);
			showButton(true);
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
	    Button sms  = (Button)findViewById(R.id.sms);
	    Button fect = (Button)findViewById(R.id.fect);
		if(isShow == true) {
		    sms.setVisibility(0);
		    fect.setVisibility(0);
		}
		else {
		    sms.setVisibility(3);
		    fect.setVisibility(3);
		}
	}
	

	private void inputData() {
	    ada = new ArrayList<Invoice>();
		if (resultsOfString == null) {
			for (int i = 0; i < 8; i++) {
				setQRcode( i, "");
			}
		} else {
			String[] args = resultsOfString.split("\\|");
			for (int i = 0; i < args.length; i++) {
				setQRcode( i, args[i]);
			}
		}
	}

	public void setQRcode(int position, String value) {
		    Invoice inv = new Invoice(resultStringTitleOfQRcode[position], value);
		    ada.add(inv);
	}

	public void setnormal(ResponseInvoke invoke) {
	    ada = new ArrayList<Invoice>();
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[0]), invoke.fpdm));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[1]), invoke.fphm));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[2]), invoke.fkfMc));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[3]), invoke.fkfZjhm));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[4]), invoke.skfMc));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[5]), invoke.skfZjhm));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[6]), invoke.kprq));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[7]), invoke.hjJe));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[8]), invoke.skm));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[9]), invoke.fkfSj));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[10]), invoke.zjDj));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[11]), invoke.zjJe));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[12]), invoke.zjQs));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[13]), invoke.cyCs));
	    ada.add(new Invoice(getResources().getString(resultStringTitleOfCheck[14]), invoke.fpztMc));
	}
}
