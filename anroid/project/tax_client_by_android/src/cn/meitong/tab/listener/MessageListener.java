package cn.meitong.tab.listener;


import cn.meitong.R;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.query.HandmadeInputActivity;
import cn.meitong.tab.query.PhoneInputActivity;
import cn.meitong.values.ActionValues;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TabValues;
import android.app.Activity;
import android.content.Intent;
import android.opengl.Visibility;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * 用于设置 单选按钮的监听 标题更改
 * 
 * @author Tom_achai
 * @date 2011-12-4
 * 
 */
public class MessageListener implements OnClickListener,
		OnCheckedChangeListener {

	private final static String TAG = "MessageListner";

	private Activity activityFinish;

	private MainTabActivity mtActivity;
	private TabHost mHost;

	public MessageListener(Activity activity) {
		this.activityFinish = activity;

	}

	/**
	 * 用于处理底部消息
	 * @param mHost
	 * @param activity
	 */
	public MessageListener(TabHost mHost, MainTabActivity activity) {
		this.mHost = mHost;
		this.mtActivity = activity;

	}
	private int buttonState;
	/**
	 * 用于设置标题的消息
	 * @param mHost
	 * @param activity
	 * @param isBack
	 */
	public MessageListener(TabHost mHost, MainTabActivity activity,int backState) {
		this.mHost = mHost;
		this.mtActivity = activity;
		this.buttonState = backState;
		

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.titleBack:
			back();
			break;
		case R.id.titleQuery:
			query();
			break;
		case R.id.button_scan:
			scan();
			break;
		default:
			break;
		}
	}

	private void scan() {
		Log.d(TAG, "SCAN1111111111111");
	
	}

	/**
	 *标题上的查询方法 
	 */
	private void query() {
//		Log.d(TAG,"query!!!!!!");
//		String n = (String) mtActivity.getCurrentActivity().getText(R.id.titleText);
//	 String name = mtActivity.getCurrentActivity().getClass().getName();
//	ResultTest tv =  (ResultTest) mtActivity.getCurrentActivity();	
	
	//可以获取intent
	//String tvs = tv.getIntent().getStringExtra("result");
//	String tvs = tv.printCurr();
//	 Log.d(TAG,"query!!!!!!"+ tv.printCurr());
	 
	  sendQuery(this.mHost.getCurrentTabTag());
	}
	
	/**
	 * 执行TabHost 中 当前activity 的方法
	 * @param tag
	 */
	private void sendQuery(String tag){
		
		if(tag.equals(TabValues.INPUT_TAB)){
			HandmadeInputActivity hi = (HandmadeInputActivity) mtActivity.getCurrentActivity();
			 hi.sendToQuery();
			Log.d(TAG,"query!!!!!!"+mtActivity.getCurrentActivity().getClass().getName());
		}
		if(tag.equals(TabValues.SMSIN_TAB)){
			Log.d(TAG,"query!!!!!!"+mtActivity.getCurrentActivity().getClass().getName());
		}
		if(tag.equals(TabValues.PHONENO_TAB)){
			PhoneInputActivity pia = (PhoneInputActivity) mtActivity.getCurrentActivity();
			pia.sendToQuery();
		}
	
	}
	
	private void back() {

		// Intent intent = new Intent().setClass(activityFinish,
		// MainTabActivity.class);
		// intent.setAction(ActionValues.SCAN_BACK);
		// activityFinish.startActivity(intent);
		// activityFinish.finish();
		// 返回 到扫描界面
		Log.d(TAG, "back-------->");
		mtActivity.changeTab(TabValues.SCAN_TAB);
		mtActivity.changeTitleText(R.string.title_scan);
		
		mtActivity.showTitleButton(false, false);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			Log.d(TAG, "ischeck");
			switch (buttonView.getId()) {
			case R.id.radio_button0:
				Log.d(TAG, "scan");
				mtActivity.changeTab(TabValues.SCAN_TAB);
				mtActivity.changeTitleText(R.string.title_scan);
				mtActivity.showTitleButton(false, false);
				break;
			case R.id.radio_button1:
				Log.d(TAG, "input");
				mtActivity.changeTab(TabValues.INPUT_TAB);
				mtActivity.changeTitleText(R.string.title_input);
				mtActivity.showTitleButton(true, true);
				break;

			case R.id.radio_button3:
				Log.d(TAG, "sms");
				mtActivity.changeTab(TabValues.LUCKY_TAB);
				mtActivity.changeTitleText(R.string.title_lucky);
				mtActivity.showTitleButton(true, false);
				break;
			case R.id.radio_button4:
				Log.d(TAG, "help");
				mtActivity.changeTab(TabValues.HELP_TAB);
				mtActivity.changeTitleText(R.string.title_help);
				mtActivity.showTitleButton(true, false);
				break;
			}
		}
	}
	

	

	

}
