package cn.meitong.tab.home;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;

import cn.meitong.R;
import cn.meitong.tab.help.HelpActivity;
import cn.meitong.tab.help.Help_2;
import cn.meitong.tab.help.Help_3;
import cn.meitong.tab.listener.MessageListener;
import cn.meitong.tab.more.MoreActivity;
import cn.meitong.tab.query.HandmadeInputActivity;
import cn.meitong.tab.query.LuckyInput;
import cn.meitong.tab.query.PhoneInputActivity;
import cn.meitong.tab.query.WinNumInputActivity;
import cn.meitong.tab.result.BackToResults;
import cn.meitong.tab.result.LuckyResult;
import cn.meitong.tab.result.QueryResult;

import cn.meitong.tab.sms.SMSSendActivity;
import cn.meitong.values.ActionValues;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TabValues;

/**
 * 将所有activity整合在一起
 * 
 * @author Tom_achai
 * @date 2011-11-19
 */
public class MainTabActivity extends TabActivity {

	private final static String TAG = "mainTab";
	private TabHost mHost;

	private Intent mScanQuery;
	private Intent mInputQuery;
	private Intent mLuckyQuery;
	private Intent mSmsInput;
	private Intent mSmsSend;
	private Intent mHelpQuery;
	private Intent mPhoneInput;
	private Intent mQueryResult;
	private Intent mShowResult;
	private Intent mLuckyResult;
	public Intent mLuckyInput;
	public Intent mMore;
	public Intent mSelectHistory;
	private Intent mHelp2;
	private Intent mHelp3;
	public TextView mTitle;

	private String  qRresult;
	private String action;

	public Button bBack;
	public Button bQuery;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.)
		Log.d(TAG, "start");
		setContentView(R.layout.maintabs);
		
		// 用于接收传过来的intent
		receiveIntent(getIntent());
	

		// 初始化Intent
		initIntent();
		// 初始化所有界面上所有按钮
		initRadios();
		// 利用ACTION 进行intent的数值填充
		setIntentResult(action);
		// 初始化
		initTabs();
		// 切换tab
		changeTabByAction(action);

	}

	/**
	 * 用于初始化intent
	 * 
	 */
	private void initIntent() {
		this.mHost = getTabHost();
		/**
		 * 这里是用于初始化tab...
		 */

		this.mScanQuery = MainTabUtils.setIntentClass(this, ScanActivity.class);

		this.mInputQuery =  MainTabUtils.setIntentClass(this,
				HandmadeInputActivity.class);
		this.mLuckyQuery =  MainTabUtils.setIntentClass(this,
				WinNumInputActivity.class);
		this.mMore =  MainTabUtils.setIntentClass(this, MoreActivity.class);

		this.mHelpQuery =  MainTabUtils.setIntentClass(this, HelpActivity.class);

		/**
		 * 用于设置不可见tab
		 */
		this.mShowResult =  MainTabUtils.setIntentClass(this, BackToResults.class);
		
		this.mSmsSend =  MainTabUtils.setIntentClass(this, SMSSendActivity.class);
		this.mPhoneInput =  MainTabUtils.setIntentClass(this, PhoneInputActivity.class);
		this.mQueryResult =  MainTabUtils.setIntentClass(this, QueryResult.class);
		this.mLuckyInput =  MainTabUtils.setIntentClass(this, LuckyInput.class);
		this.mLuckyResult =  MainTabUtils.setIntentClass(this, LuckyResult.class);
		this.mSelectHistory = MainTabUtils.setIntentClass(this, SelectHistoryType.class);
		this.mHelp2 = MainTabUtils.setIntentClass(this, Help_2.class);
		this.mHelp3 = MainTabUtils.setIntentClass(this, Help_3.class);

	}
	
	
	private String smsResult;
	private String queryResult;
	private String smsQrcode;
	private String tablename;
	private String historyResult;
	private int historytype;
	private void receiveIntent(final Intent intent) {
		if(intent != null){
//			qRresult = intent.getStringExtra(ResultValues.QueryKey.QRCODE);
//			smsResult = intent.getStringExtra(ResultValues.SMS_RESULT);
//			smsQrcode = intent.getStringExtra(ResultValues.SMS_QRCODE);
//			queryResult = intent.getStringExtra(ResultValues.QUERYRESULT);
//			tablename = intent.getStringExtra(ResultValues.TABLENAME);
			
			qRresult = MainTabUtils.getIntentString(intent, ResultValues.QueryKey.QRCODE);
			smsResult = MainTabUtils.getIntentString(intent, ResultValues.SMS_RESULT);
			smsQrcode = MainTabUtils.getIntentString(intent, ResultValues.SMS_QRCODE);
			queryResult = MainTabUtils.getIntentString(intent, ResultValues.QUERYRESULT);
			tablename = MainTabUtils.getIntentString(intent, ResultValues.TABLENAME);
			historyResult = MainTabUtils.getIntentString(intent, ResultValues.QueryKey.HISTORY);
			historytype = intent.getIntExtra(ResultValues.QueryType.QUERYTYPE, 0);
			action = intent.getAction();
			Log.d(TAG, qRresult + "---" + action);
		}
		
		
	}

	/**
	 * 用于Tabactivity中子activity间的传值 通过判断activity的action确定为那个intent设置值
	 * 
	 * @param action
	 */
	private void setIntentResult(String action) {

		if(action.equals(ActionValues.SCAN_SUCCESS)){
			Log.d(TAG, "SHOWRESULT");
			this.mShowResult.putExtra(ResultValues.QueryKey.QRCODE, qRresult);
		
			this.mShowResult.putExtra(ResultValues.QueryType.QUERYTYPE, ResultValues.QueryType.qRcode);
		
		}
		if(action.equals(ActionValues.SCAN_SMS_SEND)){
			Log.d(TAG, "SMS_SEND");
			if(smsResult != null){
				this.mSmsSend.putExtra(ResultValues.SMS_RESULT, smsResult);
			}
			if(smsQrcode !=null){
				this.mSmsSend.putExtra(ResultValues.SMS_QRCODE, smsQrcode);
			}
			
		}
		
		if(action.equals(ActionValues.QUERYRESULT)){
			this.mQueryResult.putExtra(ResultValues.QUERYRESULT,queryResult);
		}
		if(action.equals(ActionValues.LUCKYRESULT)){
			this.mLuckyResult.putExtra(ResultValues.QUERYRESULT, queryResult);
		}
		if(action.equals(ActionValues.HISTORYRESULT)){
			this.mSelectHistory.putExtra(ResultValues.TABLENAME, tablename);
		}
		if(action.equals(ActionValues.SHOWHISTORY)){
			this.mShowResult.putExtra(ResultValues.QueryKey.HISTORY, historyResult);	
			this.mShowResult.putExtra(ResultValues.QueryType.QUERYTYPE, historytype);
		}
	}

	/**
	 * 切换TAB 通过设置activity的action选择显示那个tab
	 * 
	 * @param action
	 */
	private void changeTabByAction(String action) {
		
		if(action.equals(ActionValues.SCAN_SUCCESS)){
			Log.d(TAG, "radiostate");
//			this.mHost.setCurrentTabByTag(TabValues.RESULT_TAB);
//			changeTitleText(R.string.title_scan_result);
			MainTabUtils.changeTabAndTitle(mHost, TabValues.RESULT_TAB, mTitle, R.string.title_scan_result);
			showTitleButton(false, false);
			//setRadioFalse();
			if(radioState){
				setRadioFalse();
				radioState = false;
			}else{
				setRadioTrue();
				radioState = true;
			}
		}
		if(action.equals(ActionValues.INPUTPHONE)){
			this.mHost.setCurrentTabByTag(TabValues.PHONENO_TAB);
			changeTitleText(R.string.title_phoneinput);
			showTitleButton(true, true);
		}
		if(action.equals(ActionValues.INPUTQUERY)){
			this.mHost.setCurrentTabByTag(TabValues.INPUT_TAB);
			changeTitleText(R.string.title_input);
			
			rInput.setChecked(true);
		}
		
		if(action.equals(ActionValues.SCAN_SMS_IN)){
			this.mHost.setCurrentTabByTag(TabValues.SMSIN_TAB);
			changeTitleText(R.string.title_sms);
		}
		if(action.equals(ActionValues.SCAN_SMS_SEND)){
			this.mHost.setCurrentTabByTag(TabValues.SMSOUT_TAB);
			changeTitleText(R.string.title_sms_send);
		}
		if(action.equals(ActionValues.QUERYRESULT)){
			this.mHost.setCurrentTabByTag(TabValues.QUERYRESULT_TAB);
			changeTitleText(R.string.title_queryresult);
			bQuery.setText(R.string.lucky_input);
			showTitleButton(true, true);
		}
		if(action.equals(ActionValues.LUCKYINPUT)){
			this.mHost.setCurrentTabByTag(TabValues.LUCKYINPUT_TAB);
			changeTitleText(R.string.title_lucky_input);
		}
		if(action.equals(ActionValues.LUCKYRESULT)){
			this.mHost.setCurrentTabByTag(TabValues.LUCKYRESULT_TAB);
			changeTitleText(R.string.title_lucky_result);
		}
		if(action.equals(ActionValues.HISTORYRESULT)){
			MainTabUtils.changeTabAndTitle(mHost, TabValues.SELECTHISTORY_TAB, mTitle, R.string.title_history);
		}
		if(action.equals(ActionValues.SHOWHISTORY)){
			MainTabUtils.changeTabAndTitle(mHost, TabValues.RESULT_TAB, mTitle,R.string.title_queryresult);
		}
		if(action.equals(ActionValues.HELP2)){
			MainTabUtils.changeTabAndTitle(mHost, TabValues.HELP_2_TAB, mTitle, R.string.title_help_2);
		}
		if(action.equals(ActionValues.HELP3)){
			MainTabUtils.changeTabAndTitle(mHost, TabValues.HELP_3_TAB, mTitle, R.string.title_help_3);
		}
	}
	private boolean radioState =true;;
	private void setRadioFalse(){
		rScan.setChecked(false);
	}
	
	private void setRadioTrue(){
		rScan.setChecked(true);
	}
	/**
	 * 设置tab 中的Intent 用于TabActivity启动activity 目前支持 扫描查询 输入查询 短信查询 帮助查询
	 * 
	 * 结果切换 中奖登记
	 * 
	 */
	private void initTabs() {

		TabHost localTabHost = this.mHost;
		localTabHost.addTab(buildTabSpec(TabValues.SCAN_TAB,
				R.string.main_scan, R.drawable.tab_icon_scan, this.mScanQuery));
		Log.d(TAG, ".................");
		localTabHost.addTab(buildTabSpec(TabValues.INPUT_TAB,
				R.string.main_input, R.drawable.tab_icon_input,
				this.mInputQuery));

		localTabHost.addTab(buildTabSpec(TabValues.LUCKY_TAB,
				R.string.main_sms, R.drawable.tab_icon_sms, this.mLuckyQuery));
		localTabHost.addTab(buildTabSpec(TabValues.HELP_TAB,
				R.string.main_help, R.drawable.tab_icon_help, this.mHelpQuery));
		localTabHost.addTab(buildTabSpec(TabValues.MORE_TAB,
				R.string.main_more, R.drawable.tab_icon_more, this.mMore));

		// 创建不可见tab
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.SMSIN_TAB, this.mSmsInput));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.RESULT_TAB,
				mShowResult));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.SMSOUT_TAB,
				mSmsSend));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.PHONENO_TAB,
				mPhoneInput));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.QUERYRESULT_TAB, mQueryResult));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.LUCKYINPUT_TAB, mLuckyInput));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.LUCKYRESULT_TAB, mLuckyResult));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.SELECTHISTORY_TAB, mSelectHistory));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.HELP_2_TAB, mHelp2));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.HELP_3_TAB, mHelp3));


	}

	/**
	 * 用于创建tab
	 * 
	 * @param tag
	 * @param resLabel
	 * @param resIcon
	 * @param content
	 * @return
	 */
	private TabHost.TabSpec buildTabSpec(String tag, int resLabel, int resIcon,
			final Intent content) {
		return this.mHost
				.newTabSpec(tag)
				.setIndicator(getString(resLabel),
						getResources().getDrawable(resIcon))
				.setContent(content);
	}

	/**
	 * 用于创建不可见的tabspec
	 * 
	 * @param tag
	 * @param content
	 * @return
	 */
	private TabHost.TabSpec buildInvisibleTabSpec(String tag,
			final Intent content) {
		return this.mHost.newTabSpec(tag).setIndicator("").setContent(content);
	}

	public RadioButton rScan;
	public RadioButton rInput;

	/**
	 * 初始化标题按钮 初始化底部按钮
	 */
	private void initRadios() {

		// -----------------底部按钮
		rScan = ((RadioButton) findViewById(R.id.radio_button0));
	//	rScan.setChecked(true);
		rScan.setOnCheckedChangeListener(new MessageListener(mHost, this));

		rInput = ((RadioButton) findViewById(R.id.radio_button1));
				rInput.setOnCheckedChangeListener(new MessageListener(mHost, this));

		((RadioButton) findViewById(R.id.radio_button3))
				.setOnCheckedChangeListener(new MessageListener(mHost, this));
		((RadioButton) findViewById(R.id.radio_button4))
				.setOnCheckedChangeListener(new MessageListener(mHost, this));
		((RadioButton) findViewById(R.id.radio_button5))
		.setOnCheckedChangeListener(new MessageListener(mHost, this));
		//
		// -----------------------------------

		// -------------------标题
		mTitle = (TextView) findViewById(R.id.titleText);
		bBack = ((Button) findViewById(R.id.titleBack));
		bBack.setOnClickListener(new MessageListener(mHost, this));
		bBack.setVisibility(View.INVISIBLE);
		bQuery = ((Button) findViewById(R.id.titleQuery));
		bQuery.setOnClickListener(new MessageListener(mHost, this));
		bQuery.setVisibility(View.INVISIBLE);
		// ----------------------
		//----------------内容
	
		
		
	}

	/**
	 * 控制更改标题文字
	 * @param title
	 */
	@Deprecated
	public void changeTitleText(int title){
		this.mTitle.setText(title);
	}
	/**
	 * 切换 
	 * Tab
	 * @param tag
	 */
	@Deprecated
	public void changeTab(final String tag){
		this.mHost.setCurrentTabByTag(tag);
	}
	/**
	 * 用于控制标题按钮的显示
	 * @param isBack
	 * @param isQuery
	 */
	public void showTitleButton(boolean isBack,boolean isQuery){
		if(isBack){
			this.bBack.setVisibility(View.VISIBLE);
		}else{
			this.bBack.setVisibility(View.INVISIBLE);
		}
		
		if(isQuery){
			this.bQuery.setVisibility(View.VISIBLE);
		}else{
			this.bQuery.setVisibility(View.INVISIBLE);
		}
	}
	
}
