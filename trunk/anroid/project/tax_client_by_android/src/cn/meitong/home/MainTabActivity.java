package cn.meitong.home;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import cn.meiton.action.ActionValues;
import cn.meitong.R;
import cn.meitong.decoder.CaptureActivity;
import cn.meitong.query.PhoneInputActivity;
import cn.meitong.query.WinNumInputActivity;
import cn.meitong.result.ShowResult;

/**
 * 将所有activity整合在一起
 * 
 * @author Tom_achai
 * @date 2011-11-19
 */
public class MainTabActivity extends TabActivity implements
		OnCheckedChangeListener {

	private final static String TAG = "mainTab";
	private TabHost mHost;

	private Intent mScanQuery;
	private Intent mInputQuery;
	private Intent mLuckyQuery;
	private Intent mSmsInput;
	private Intent mSmsSend;
	private Intent mHelpQuery;

	private Intent mShowResult;
	private TextView mTitle;

	private String result;
	private String action;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.)
		setContentView(R.layout.maintabs);

		// 用于接收传过来的intent
		receiveIntent(getIntent());

		// 初始化Intent
		initIntent();

		// 初始化按钮
		initRadios();

		// 利用ACTION 进行intent的数值填充
		putIntentExtra(action);
		// 初始化
		setupIntent();
		// 切换tab
		setTab(action);

	}

	/**
	 * 用于初始化intent
	 * 
	 */
	private void initIntent() {
		/**
		 * 这里是用于初始化tab...
		 */

		this.mScanQuery = new Intent().setClass(this, ScanActivity.class);

		this.mInputQuery = new Intent()
				.setClass(this, PhoneInputActivity.class);
		this.mLuckyQuery = new Intent().setClass(this,
				WinNumInputActivity.class);
		this.mSmsInput = new Intent().setClass(this, SMSInputActivity.class);

		this.mHelpQuery = new Intent().setClass(this, HelpActivity.class);

		/**
		 * 用于设置不可见tab
		 */
		this.mShowResult = new Intent().setClass(this, ShowResult.class);
		this.mSmsSend = new Intent().setClass(this, SMSSendActivity.class);

	}

	private void receiveIntent(final Intent intent) {
		result = intent.getStringExtra("result");
		action = intent.getAction();
		Log.d(TAG, result + "---" + action);
	}

	/**
	 * 用于Tabactivity中子activity间的传值 通过判断activity的action确定为那个intent设置值
	 * 
	 * @param action
	 */
	private void putIntentExtra(String action) {
		if (action == null) {
			action = "kg.maintab";
		}
		Log.d(TAG, "putIntentExtra1" + action);

		if (action.equals(ActionValues.SCAN_SUCCESS)) {
			Log.d(TAG, "putIntentExtra2" + action);
			this.mShowResult.putExtra("result", result);
		}
	}

	/**
	 * 切换TAB 通过设置activity的action选择显示那个tab
	 * 
	 * @param action
	 */
	private void setTab(String action) {
		
		
		if(action.equals("")){
			
		}
		
		if (action.equals(ActionValues.SCAN_SUCCESS)) {
			Log.d(TAG, "ok-------->");
			this.mHost.setCurrentTabByTag("m_result");
		}
	}

	/**
	 * 设置tab 中的Intent 用于TabActivity启动activity
	 */
	private void setupIntent() {
		this.mHost = getTabHost();
		TabHost localTabHost = this.mHost;
		localTabHost.addTab(buildTabSpec("mScan_tab", R.string.main_scan,
				R.drawable.icon_1, this.mScanQuery));
		localTabHost.addTab(buildTabSpec("mInput_tab", R.string.main_input,
				R.drawable.icon_2, this.mInputQuery));
		localTabHost.addTab(buildTabSpec("mLucky_tab", R.string.main_lucky,
				R.drawable.icon_3, this.mLuckyQuery));
		localTabHost.addTab(buildTabSpec("mSms_tab", R.string.main_sms,
				R.drawable.icon_4, this.mSmsInput));
		localTabHost.addTab(buildTabSpec("mhelp_tab", R.string.main_help,
				R.drawable.icon_5, this.mHelpQuery));

		// 创建不可见tab
		localTabHost.addTab(buildInvisibleTabSpec("m_result", mShowResult));
		localTabHost.addTab(buildInvisibleTabSpec("mSmsSend_tab", mSmsSend));

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

	/**
	 * 初始化底部按钮
	 */
	private void initRadios() {

		((RadioButton) findViewById(R.id.radio_button0))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_button1))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_button2))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_button3))
				.setOnCheckedChangeListener(this);
		((RadioButton) findViewById(R.id.radio_button4))
				.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			Log.d(TAG, "ischeck");
			switch (buttonView.getId()) {
			case R.id.radio_button0:
				Log.d(TAG, "0");
				this.mHost.setCurrentTabByTag("mScan_tab");
	//			跳转到扫描capture;
				Intent intent = new Intent().setClass(this, CaptureActivity.class);
				startActivity(intent);
				break;
			case R.id.radio_button1:
				Log.d(TAG, "1");
				this.mHost.setCurrentTabByTag("mInput_tab");

				break;
			case R.id.radio_button2:
				Log.d(TAG, "2");
				this.mHost.setCurrentTabByTag("mLucky_tab");
				break;
			case R.id.radio_button3:
				Log.d(TAG, "3");
				this.mHost.setCurrentTabByTag("mSms_tab");
				break;
			case R.id.radio_button4:
				Log.d(TAG, "4");
				this.mHost.setCurrentTabByTag("mhelp_tab");
				break;
			}
		}
	}

}
