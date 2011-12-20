package cn.meitong.tab.listener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import cn.meitong.R;
import cn.meitong.decoder.CaptureActivity;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.history.HistoryUtils;
import cn.meitong.model.ResponseItem;
import cn.meitong.sax.OutResultParse;
import cn.meitong.sax.ResponseParse;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.home.SelectHistoryType;
import cn.meitong.tab.query.LuckyInput;
import cn.meitong.tab.result.BackToResults;

import cn.meitong.tab.sms.SMSSendActivity;
import cn.meitong.values.ActionValues;
import cn.meitong.values.DataBaseValues;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


	public void haveHideQuery(){
		activityContent.findViewById(R.id.titleQuery).setVisibility(View.INVISIBLE);
	}
	public void haveQrcoreQuery() {
		activityContent.findViewById(R.id.online_query)
				.setOnClickListener(this);
	}
	//更改标题
	public void changeTitle(int title){
		((TextView)activityContent.findViewById(R.id.titleText)).setText(title);
	}

	// 手机号码
	public void havaInputPhoneNo() {
		activityContent.findViewById(R.id.inputphone).setOnClickListener(this);
	}

	// 手工输入
	public void haveInputQuery() {
		activityContent.findViewById(R.id.inputquery).setOnClickListener(this);
	}
	
	//发票查验历史记录
	public void haveHistoryfpCheck(){
		activityContent.findViewById(R.id.history_fpcheck).setOnClickListener(this);
	}

	public void haveHistoryfpDj(){
		activityContent.findViewById(R.id.history_dj).setOnClickListener(this);
	}
	//载入提示
	public static ProgressDialog haveProgressTips(Context activity,String message){
		ProgressDialog progressDialog;
		progressDialog = ProgressDialog.show(activity, "", message);
		return progressDialog;
	}
	
	public void haveBack(){
		activityContent.findViewById(R.id.titleBack).setOnClickListener(this);
	}
	
	private boolean mIsCheck;
	public void haveSmsQuery(boolean isCheck) {
		mIsCheck = isCheck;
		activityContent.findViewById(R.id.sms).setOnClickListener(this);
	}
	
	public static void haveTips(Context activity,String message){
		Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
	}
	@Override
	public void onClick(View v) {
		Intent intent;
		String tableName;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_scan:
			Log.d("mainTab", "ContentManagner");
			intent = new Intent();
			intent.setClass(activityContent, CaptureActivity.class);

			activityContent.startActivity(intent);

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
			if(mIsCheck){
				String fpdm = ((BackToResults) activityContent).args[4];		
				String fphm = ((BackToResults) activityContent).args[5];
				String fpje = ((BackToResults) activityContent).args[3];
				String smsSend = "fpdj" + "," + fpdm + "," + fphm + "," + fpje;
				String qrcode =  ((BackToResults) activityContent).resultsOfString;
				String smsQrcode = "FPSM," + qrcode;
				intent = setIntent(MainTabActivity.class, ResultValues.SMS_RESULT,
						smsSend, ActionValues.SCAN_SMS_SEND);
				intent.putExtra(ResultValues.SMS_QRCODE, smsQrcode);
			
			}else{
				String fpdm = ((LuckyInput)activityContent).sfpdm;
				String fphm = ((LuckyInput) activityContent).sfphm;			
				String fpje =((LuckyInput) activityContent).shjje;
				String smsSend = "fpdj" + "," + fpdm + "," + fphm + "," + fpje;
				intent = setIntent(MainTabActivity.class, ResultValues.SMS_RESULT,
						smsSend, ActionValues.SCAN_SMS_SEND);
			
			}
			activityContent.startActivity(intent);
			
			break;
		case R.id.online_query:
			Log.d("soap", "onclick");
			dialog = ContentMananger.haveProgressTips(activityContent, TipsValues.qrcodeProgress);
			//dialog = ProgressDialog.show(activityContent,"", "二维码查验中", true);
			// String queryValues =
			String queryValues = activityContent.getIntent().getStringExtra(ResultValues.QueryKey.QRCODE);
			//二维码登记信息
			new HistoryUtils(activityContent).insertResult(DataBaseValues.DJTABLE, queryValues,ResultValues.QueryType.qRcode);
//		测试用数据
		//	String queryValues = "01|95851e46d213843b117|20111207111719537|51327|244011106020|05369908|440104718171810|培训帐户";
			String message = ContentMananger.queryValues(ResultValues.QueryType.qRcode,
					queryValues,activityContent);
			Log.d("soap", "message-->" + message);
			ResultHandle rh = new ResultHandle(activityContent, message, ResultValues.isCheck,dialog);
		
			break;
			
		case R.id.history_fpcheck:
			tableName = DataBaseValues.CHECKTABLE;
			intent = setIntent(MainTabActivity.class, ResultValues.TABLENAME, tableName, ActionValues.HISTORYRESULT);
			activityContent.startActivity(intent);
			break;
			
		case R.id.history_dj:
			tableName = DataBaseValues.DJTABLE;
			intent = setIntent(MainTabActivity.class, ResultValues.TABLENAME, tableName, ActionValues.HISTORYRESULT);
			activityContent.startActivity(intent);
			break;
		case R.id.titleBack:
			activityContent.finish();
			
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
		String[] temp = null;
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
				temp = queryValues.split(",");
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
			case ResultValues.QueryType.phoneNumber:
				is = queryActivity.getAssets().open("phoneno_type.xml");
				buffer = new BufferedReader(new InputStreamReader(is,"utf-8"));
				temp = queryValues.split(",");
				while((line = buffer.readLine()) != null){
					if (line.trim().equals("<fkfSj>")) {
						line += temp[0];
					} else if (line.trim().equals("<kprqQ>")) {
						line += temp[1];
					} else if (line.trim().equals("<kprqZ>")) {
						line += temp[2];
					}
					sb.append(line.trim());
				}
				break;
			case ResultValues.QueryType.normalDj:
				is = queryActivity.getAssets().open("normaldj_type.xml");
				buffer = new BufferedReader(new InputStreamReader(is, "utf-8"));
				temp = queryValues.split(",");
				while ((line = buffer.readLine()) != null) {
					if (line.trim().equals("<fpdm>")) {
						line += temp[0];
					} else if (line.trim().equals("<fphm>")) {
						line += temp[1];
					}else if(line.trim().equals("<hjJe>")){
						line += temp[2];
					}else if (line.trim().equals("<kpje>")) {
						line += temp[2];
					}else if(line.trim().equals("<fkfMc>")){
						line += temp[3];
					}else if(line.trim().equals("<skfMc>")){
						line += temp[4];
					}else if(line.trim().equals("<kprq>")){
						line += temp[5];
					}else if(line.trim().equals("<fkfSj>")){
						line += temp[6];
					}
					sb.append(line.trim());
				}		
				break;
			case ResultValues.QueryType.qRodeDj:
				is = queryActivity.getAssets().open("qrcodedj_type.xml");
				buffer = new BufferedReader(new InputStreamReader(is, "utf-8"));
				temp = queryValues.split(",");
				while ((line = buffer.readLine()) != null) {
					if (line.trim().equals("<mmq>")) {
						line += temp[0];
					}else if(line.trim().equals("<fkfSj>")){
						line += temp[1];
					}
					sb.append(line.trim());
				}
				break;	
			default:
				break;
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public static ResponseItem parseResult(String queryResult){
		// 1 生成SAX工厂
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		ResponseItem responseItem = new ResponseItem();
		try {
			XMLReader responseReader = saxFactory.newSAXParser().getXMLReader();
			// 2 利用handle 异步解析xml
			ResponseParse outResponse = new ResponseParse();
			responseReader.setContentHandler(outResponse);
			responseReader
					.parse(new InputSource(new StringReader(queryResult)));
			String out = outResponse.queryResult;
			Log.d("soap", out);

			// 3 处理 最终结果
			OutResultParse outResult = new OutResultParse(responseItem);
			responseReader.setContentHandler(outResult);
			responseReader.parse(new InputSource(new StringReader(out)));
			Log.d("soap","responiten--->"+ responseItem.toString());
			return responseItem;
//			Intent luckIntent = new Intent().setClass(this, LuckyInput.class);
//			luckIntent.putExtra("luckyresult", responseItem);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
