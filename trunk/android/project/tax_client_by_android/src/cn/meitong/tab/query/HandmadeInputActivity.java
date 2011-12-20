package cn.meitong.tab.query;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.history.HistoryUtils;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;

import cn.meitong.values.DataBaseValues;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;

public class HandmadeInputActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.handmadeinput);
		initEdit();

	}

	private EditText mfpdm;
	private EditText mfphm;
	private EditText mfpje;

	private void initEdit() {
		mfpdm = (EditText) findViewById(R.id.edit_fpdm);

		mfphm = (EditText) findViewById(R.id.edit_fphm);
		mfpje = (EditText) findViewById(R.id.edit_fpje);
	}

	private ProgressDialog dialog;

	public void sendToQuery() {
		// 金额:51327|
		// 代码:244011106020
		// 号码:|05369908
		String fpdm = mfpdm.getText().toString();
		String fphm = mfphm.getText().toString();
		String fpje = mfpje.getText().toString();
		
//		String fpdm = "244011106020";
//		String fphm = "05369908";
//		String fpje = "51327";
		// Log.d("soap", fpdm+""+fphm+fpje);
		// fpdm.
		Log.d("soap", "" + fpdm.length() + fphm.length() + fpje.length());
		
		if (regexInput(fpdm, fphm, fpje)) {
			
			dialog = ContentMananger.haveProgressTips(this,
					TipsValues.inputProgress);
			String queryValues = fpdm + "," + fphm + "," + fpje;
			new HistoryUtils(this).insertResult(DataBaseValues.CHECKTABLE, queryValues, ResultValues.QueryType.normal);
			String message = ContentMananger.queryValues(
					ResultValues.QueryType.normal, queryValues, this);
			ResultHandle rh = new ResultHandle(this, message, ResultValues.isCheck, dialog);
		}
		
		//QRCODE 登记测试
//		dialog = ContentMananger.haveProgressTips(this,
//				TipsValues.inputProgress);
//		String queryValues = "01|2627021608a667ae002|20111114125837142|268.2|244011107010|09077540|440106707684880|陈燕燕"+","+"13726205927";
//		new HistoryUtils(this).insertResult(DataBaseValues.CHECKTABLE, queryValues, ResultValues.QueryType.normal);
//		String message = ContentMananger.queryValues(
//				ResultValues.QueryType.qRodeDj, queryValues, this);
//		Log.d("soap", "qrcode"+message);
//		ResultHandle rh = new ResultHandle(this, message, ResultValues.isCheck, dialog);

	}

	private boolean regexInput(String fpdm, String fphm, String fpje) {

		if (fpdm.length() != 12) {
			mfpdm.setFocusable(true);
			ContentMananger.haveTips(this, TipsValues.fpdmError);
			return false;
		}
		Log.d("soap", fphm.length() + "");
		if (fphm.length() != 8) {
			mfphm.setFocusable(true);
			ContentMananger.haveTips(this, TipsValues.fphmError);
			return false;
		}

		if (fpje.equals("")) {
			ContentMananger.haveTips(this, TipsValues.fpjeError);
			return false;
		}

		return true;
	}

}
