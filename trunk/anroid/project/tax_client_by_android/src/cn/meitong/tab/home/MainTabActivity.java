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
import cn.meitong.tab.listener.MessageListener;
import cn.meitong.tab.query.HandmadeInputActivity;
import cn.meitong.tab.query.WinNumInputActivity;
import cn.meitong.tab.result.BackToResults;

import cn.meitong.tab.sms.SMSInputActivity;
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

	private Intent mShowResult;
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
		setupIntent();
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

		this.mScanQuery = new Intent().setClass(this, ScanActivity.class);

		this.mInputQuery = new Intent().setClass(this,
				HandmadeInputActivity.class);
		this.mLuckyQuery = new Intent().setClass(this,
				WinNumInputActivity.class);
		

		this.mHelpQuery = new Intent().setClass(this, HelpActivity.class);

		/**
		 * 用于设置不可见tab
		 */
		this.mShowResult = new Intent().setClass(this, BackToResults.class);
		this.mSmsInput = new Intent().setClass(this, SMSInputActivity.class);
		this.mSmsSend = new Intent().setClass(this, SMSSendActivity.class);
	
		
	

	}

	private void receiveIntent(final Intent intent) {
		if(intent != null){
			qRresult = intent.getStringExtra(ResultValues.QueryKey.QRCODE);
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
		
	
	}

	/**
	 * 切换TAB 通过设置activity的action选择显示那个tab
	 * 
	 * @param action
	 */
	private void changeTabByAction(String action) {

		// if (action.equals(ActionValues.SCAN_BACK)) {
		// Log.d("radio", "com----------");
		// this.mHost.setCurrentTabByTag("mScan_tab");
		// RadioButton rb = (RadioButton) findViewById(R.id.radio_button0);
		// // rb.setClickable(true);
		// rb.setChecked(true);
		// Log.d("radio", rb.isChecked() + "");
		// }
		// if (action.equals(ActionValues.SCAN_SUCCESS)) {
		// Log.d(TAG, "ok-------->");
		// this.mHost.setCurrentTabByTag("m_result");
		// }
		// if (action.equals(ActionValues.SCAN_SMS)) {
		// this.mHost.setCurrentTabByTag("mSms_tab");
		// }
		

		if(action.equals(ActionValues.SCAN_SUCCESS)){
			Log.d(TAG, "radiostate");
			this.mHost.setCurrentTabByTag(TabValues.RESULT_TAB);
			showTitleButton(true, false);
			//setRadioFalse();
			if(radioState){
				setRadioFalse();
				radioState = false;
			}else{
				setRadioTrue();
				radioState = true;
			}
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
	private void setupIntent() {

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

		// 创建不可见tab
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.SMSIN_TAB, this.mSmsInput));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.RESULT_TAB,
				mShowResult));
		localTabHost.addTab(buildInvisibleTabSpec(TabValues.SMSOUT_TAB,
				mSmsSend));
//		localTabHost.addTab(buildInvisibleTabSpec(TabValues.LUCKY_TAB,
//				mLuckyQuery));
		//------------test
		


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
	

	/**
	 * 初始化标题按钮 初始化底部按钮
	 */
	private void initRadios() {

		// -----------------底部按钮
		rScan = ((RadioButton) findViewById(R.id.radio_button0));
		rScan.setChecked(true);
		rScan.setOnCheckedChangeListener(new MessageListener(mHost, this));

		((RadioButton) findViewById(R.id.radio_button1))
				.setOnCheckedChangeListener(new MessageListener(mHost, this));

		((RadioButton) findViewById(R.id.radio_button3))
				.setOnCheckedChangeListener(new MessageListener(mHost, this));
		((RadioButton) findViewById(R.id.radio_button4))
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
	public void changeTitleText(int title){
		this.mTitle.setText(title);
	}
	/**
	 * 切换 
	 * Tab
	 * @param tag
	 */
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
