package cn.meitong.tab.query;

import cn.meitong.R;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.history.HistoryUtils;
import cn.meitong.model.ResponseItem;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.values.DataBaseValues;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LuckyInput extends Activity {

	private ResponseItem responseItem;
	private TextView fkfSj;
	private TextView kprq;
	private TextView fkfMc;
	private TextView skfMc;
	private TextView fpdm;
	private TextView fphm;
	private TextView hjJe;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.luckyinput);
		initTextView();
		responseItem = (ResponseItem) getIntent().getSerializableExtra("lucky");
		if (responseItem != null) {
			Log.d("soap", "发票代码" + responseItem.fpdm);
			setQueryResult();
			new ContentMananger(this).haveSmsQuery(false);
		}

	}

	public void initTextView() {
		fpdm = (TextView) findViewById(R.id.fpdm);
		fphm = (TextView) findViewById(R.id.fphm);
		fkfMc = (TextView) findViewById(R.id.fkfmc);
		hjJe = (TextView) findViewById(R.id.hjje);
		skfMc = (TextView) findViewById(R.id.skfmc);

		kprq = (TextView) findViewById(R.id.kprq);

		fkfSj = (TextView) findViewById(R.id.fkfsj);

	}

	private ProgressDialog dialog;

	private void setQueryResult() {
		fphm.setText(responseItem.fphm);
		fpdm.setText(responseItem.fpdm);

		fkfMc.setText(responseItem.fkfMc);

		skfMc.setText(responseItem.skfMc);

		kprq.setText(responseItem.kprq);
		hjJe.setText(responseItem.hjJe);

		fkfSj.setText(responseItem.fkfSj);
		sfpdm = fpdm.getText().toString();
		sfphm = fphm.getText().toString();
		shjje = hjJe.getText().toString();
		sfkfMc = fkfMc.getText().toString();
		sskfMc = skfMc.getText().toString();
		skprq = kprq.getText().toString();
		sfkfSj = fkfSj.getText().toString();

	}

	public String sfpdm;
	public String sfphm;
	public String shjje;
	public String sfkfMc;
	public String sskfMc;
	public String skprq;
	public String sfkfSj;

	public void upQuery() {

		// String sfpdm = "244010902029";
		// String sfphm = "08117248";
		// String shjje = "100";
		// String sfkfMc = "张三";
		// String sskfMc = "李四";
		// String skprq = "2011-12-5";
		// String sfkfSj = "13726205927";
		//
		// String message = ContentMananger.queryValues(
		// ResultValues.QueryType.normal, queryValues, this);
		if (sfkfSj.equals("")) {
			ContentMananger.haveTips(this, TipsValues.phoneDjError);
		} else {
			dialog = ContentMananger.haveProgressTips(this,
					TipsValues.luckydjProgress);
			String queryValues = sfpdm + "," + sfphm + "," + shjje + ","
					+ sfkfMc + "," + sskfMc + "," + skprq + "," + sfkfSj;
			String message = ContentMananger.queryValues(
					ResultValues.QueryType.normalDj, queryValues, this);
			new HistoryUtils(this).insertResult(DataBaseValues.DJTABLE, queryValues, ResultValues.QueryType.normal);
			Log.d("soap", message + "");
			ResultHandle rh = new ResultHandle(this, message,
					ResultValues.isDj, dialog);
		}

	}

}
