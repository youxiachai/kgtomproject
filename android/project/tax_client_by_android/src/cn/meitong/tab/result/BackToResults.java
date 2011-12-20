package cn.meitong.tab.result;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.meitong.R;
import cn.meitong.model.ResponseInvoke;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;

import cn.meitong.values.ResultValues;

public class BackToResults extends ListActivity {

	public String resultsOfString;

	private int[] resultStringTitleOfCheck = new int[] { R.string.fpdm,
			R.string.fpdm, R.string.fkfmc, R.string.fkfzjhm, R.string.skfmc,
			R.string.skfzjhm, R.string.kprq, R.string.hjje, R.string.skm,
			R.string.fkfsj, R.string.zjdj, R.string.zjje, R.string.zjqs,
			R.string.cycs, R.string.fpztmc };
	public String[] resultStringTitleOfQRcode = new String[] { "版本号", "防伪码",
			"开票日期", "金额", "发票代码", "发票号码", "纳税人识别号", "开票人" };
	int[] title;
	ArrayList<Invoice> ada;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.backtoresults);
		// Intent intent = getIntent();
		// 用于处理传过来的值,以及格式化字符串
		setResultString(getIntent());

		// 显示
		MyAdapter myAdapter = new MyAdapter(this, R.layout.result_item, ada);

		this.setListAdapter(myAdapter);

		new ContentMananger(this).haveSmsQuery(true);

		new ContentMananger(this).haveQrcoreQuery();
	}

	private String historyResult;

	public void setResultString(final Intent intent) {
		showButton(false);
		if (intent != null) {
			int type = intent.getIntExtra(ResultValues.QueryType.QUERYTYPE, 0);
			switch (type) {
			case ResultValues.QueryType.normal:
				showButton(false);
				Log.d("history", "normal show!!!");
				// ResponseInvoke invoke = (ResponseInvoke)
				// intent.getSerializableExtra("");
				// setnormal(invoke);
				historyResult = intent
						.getStringExtra(ResultValues.QueryKey.HISTORY);
				Log.d("history", "" + resultsOfString);
				showButton(false);
				parseResult(historyResult);
				break;
			case ResultValues.QueryType.qRcode:
				showButton(true);
				historyResult = intent
						.getStringExtra(ResultValues.QueryKey.HISTORY);
				if (historyResult != null) {
					showButton(false);
					resultsOfString = historyResult;
					inputData();
				} else {
					resultsOfString = intent
							.getStringExtra(ResultValues.QueryKey.QRCODE);

					inputData();
				}

				break;
			case ResultValues.QueryType.sms:
				showButton(false);
				Log.d("history", "sms show!!!");
				// ResponseInvoke invoke = (ResponseInvoke)
				// intent.getSerializableExtra("");
				// setnormal(invoke);
				historyResult = intent
						.getStringExtra(ResultValues.QueryKey.HISTORY);
				String [] tempResult = historyResult.split(",");
				
				if(tempResult.length == 2){
					resultsOfString = tempResult[1];
					inputData();
				}else {
					parseSmsResult(tempResult);
					
				}
				Log.d("history", "" + resultsOfString);
				break;
			case ResultValues.QueryType.normalDj:
				break;
			default:
				break;
			}
		}
		// resultsOfString =
		// "01|95851e46d213843b117|20111207111719537|51327|244011106020|05369908|440104718171810|培训帐户";
		// showButton(true);
		// inputData();
	}

	private void parseSmsResult(String[] temp) {
		ada = new ArrayList<Invoice>();

		setQRcode(4, temp[1]);
		setQRcode(5, temp[2]);
		setQRcode(3, temp[3]);

	}

	private void parseResult(String resultsOfString2) {
		ada = new ArrayList<Invoice>();
		String[] temp = resultsOfString2.split(",");

		setQRcode(4, temp[0]);
		setQRcode(5, temp[1]);
		setQRcode(3, temp[2]);

	}

	public void showResult() {

	}

	// 用于管理显示按钮
	public void showButton(boolean isShow) {
		Button sms = (Button) findViewById(R.id.sms);
		Button fect = (Button) findViewById(R.id.online_query);
		if (isShow == true) {
			sms.setVisibility(0);
			fect.setVisibility(0);
		} else {
			sms.setVisibility(View.INVISIBLE);
			fect.setVisibility(View.INVISIBLE);
		}
	}

	private void inputData() {
		ada = new ArrayList<Invoice>();
		if (resultsOfString == null) {
			for (int i = 0; i < 8; i++) {
				if (i != 1)
					setQRcode(i, "");
			}
		} else {
			args = resultsOfString.split("\\|");
			for (int i = 0; i < args.length; i++) {
				if (i != 1)
					setQRcode(i, args[i]);
			}
		}
	}

	public String[] args;

	public void setQRcode(int position, String value) {
		Invoice inv = new Invoice(resultStringTitleOfQRcode[position], value);
		ada.add(inv);
	}

	public void setnormal(ResponseInvoke invoke) {
		ada = new ArrayList<Invoice>();
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[0]), invoke.fpdm));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[1]), invoke.fphm));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[2]), invoke.fkfMc));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[3]), invoke.fkfZjhm));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[4]), invoke.skfMc));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[5]), invoke.skfZjhm));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[6]), invoke.kprq));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[7]), invoke.hjJe));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[8]), invoke.skm));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[9]), invoke.fkfSj));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[10]), invoke.zjDj));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[11]), invoke.zjJe));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[12]), invoke.zjQs));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[13]), invoke.cyCs));
		ada.add(new Invoice(getResources().getString(
				resultStringTitleOfCheck[14]), invoke.fpztMc));
	}
}
