package cn.meitong.tab.listener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.meitong.R;
import cn.meitong.decoder.CaptureActivity;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.result.BackToResults;
import cn.meitong.tab.sms.SMSInputActivity;
import cn.meitong.tab.sms.SMSSendActivity;
import cn.meitong.values.ActionValues;
import cn.meitong.values.ResultValues;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author Tom_achai
 * @date 2011-12-5
 * 
 */
public class ContentMananger implements OnClickListener {

	private Button bScan;
	private ProgressDialog dialog;
	private final Activity activityContent;

	public ContentMananger(Activity activity) {
		this.activityContent = activity;
	}

	public void haveScanAction() {
		activityContent.findViewById(R.id.button_scan).setOnClickListener(this);
	}

	public void haveSendAction() {
		activityContent.findViewById(R.id.send).setOnClickListener(this);
	}

	public void haveQrcoreQuery() {
		activityContent.findViewById(R.id.online_query)
				.setOnClickListener(this);
	}

	// 手机号码
	public void havaInputPhoneNo() {
		activityContent.findViewById(R.id.inputphone).setOnClickListener(this);
	}

	// 手工输入
	public void haveInputQuery() {
		activityContent.findViewById(R.id.inputquery).setOnClickListener(this);
	}

	// 载入条
	public void haveProgressDialog() {
		dialog = ProgressDialog.show(activityContent, "", "查验中", true);
	}

	public void havedismiss() {
		dialog.dismiss();
	}

	public void haveSmsQuery() {
		activityContent.findViewById(R.id.sms).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_scan:
			Log.d("mainTab", "ContentManagner");
			intent = new Intent();
			intent.setClass(activityContent, CaptureActivity.class);

			activityContent.startActivity(intent);

			break;
		case R.id.send:
			// Intent smsIntent = new Intent(SMSInputActivity.this,
			// SMSSendActivity.class);
			// smsIntent.putExtra(SMSInputActivity.INTENE_MESSAGE, message
			// .getText().toString());
			// startActivity(smsIntent);
			((SMSInputActivity) activityContent).sendSms();
			Log.d("mainTab", "sssssssssend!!!!!!");
			break;
		case R.id.inputphone:
			Log.d("main", "phoneinput");

			intent = setIntent(MainTabActivity.class, null, null,
					ActionValues.INPUTPHONE);
			activityContent.startActivity(intent);
			break;
		case R.id.inputquery:
			Log.d("main", "inputquery");
			intent = setIntent(MainTabActivity.class, null, null,
					ActionValues.INPUTQUERY);
			activityContent.startActivity(intent);
			break;
		case R.id.sms:
			// intent =
			String fpdm = ((BackToResults) activityContent).args[4];
			String fphm = ((BackToResults) activityContent).args[5];
			String fpje = ((BackToResults) activityContent).args[3];
			String smsSend = "fpcy" + "," + fpdm + "," + fphm + "," + fpje;
			// intent = new Intent().setClass(activityContent,
			// SMSSendActivity.class);
			// intent.putExtra(ResultValues.SMS_RESULT, smsSend);
			intent = setIntent(MainTabActivity.class, ResultValues.SMS_RESULT,
					smsSend, ActionValues.SCAN_SMS_SEND);
			//

			activityContent.startActivity(intent);
			break;
		case R.id.online_query:
			Log.d("soap", "onclick");
			haveProgressDialog();
			// String queryValues =
			String queryValues = activityContent.getIntent().getStringExtra(ResultValues.QueryKey.QRCODE);
//		测试用数据
//			String queryValues = "01|95851e46d213843b117|20111207111719537|51327|244011106020|05369908|440104718171810|培训帐户";
			String message = ContentMananger.queryValues(ResultValues.QueryType.qRcode,
					queryValues,activityContent);
			Log.d("soap", "message-->" + message);
			ResultHandle rh = new ResultHandle(activityContent, message, true);
			havedismiss();
			break;
		default:
			break;
		}

	}

	/**
	 * 用于处理消息跳转到那个activity
	 * 
	 * @param cls
	 * @param queryKey
	 * @param result
	 * @param action
	 * @return
	 */
	public Intent setIntent(Class<?> cls, String queryKey, String result,
			String action) {
		Intent intent = new Intent();
		intent.setClass(activityContent, cls);
		intent.setAction(action);
		intent.putExtra(queryKey, result);
		return intent;
	}

	/**
	 * 设置查询的值
	 * 
	 * @param type
	 * @param queryValues
	 * @return
	 */
	public static String queryValues(int type, String queryValues,
			Activity queryActivity) {
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		InputStream is = null;
		try {
			switch (type) {
			case ResultValues.QueryType.qRcode:
				is = queryActivity.getAssets().open("qrcode_type.xml");
				buffer = new BufferedReader(new InputStreamReader(is, "utf-8"));
				while ((line = buffer.readLine()) != null) {
					if (line.trim().equals("<mmq>")) {
						line += queryValues;
					}
					sb.append(line.trim());
				}
				break;
			case ResultValues.QueryType.normal:
				is = queryActivity.getAssets().open("normal_type.xml");
				buffer = new BufferedReader(new InputStreamReader(is, "utf-8"));
				String[] temp = queryValues.split(",");
				while ((line = buffer.readLine()) != null) {
					if (line.trim().equals("<fpdm>")) {
						line += temp[0];
					} else if (line.trim().equals("<fphm>")) {
						line += temp[1];
					} else if (line.trim().equals("<kpje>")) {
						line += temp[2];
					}
					sb.append(line.trim());
				}
				break;
			default:
				break;
			}
			return sb.toString();
		} catch (Exception e) {

		}

		return null;
	}
}
