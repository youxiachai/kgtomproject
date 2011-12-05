
package cn.meitong.tab.listener;

import cn.meitong.R;
import cn.meitong.decoder.CaptureActivity;
import cn.meitong.tab.home.MainTabActivity;
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
public class ContentMananger implements OnClickListener{

	private Button bScan;
	private ProgressDialog dialog;
	private final Activity activityContent;
	
	public ContentMananger(Activity activity){
		this.activityContent = activity;
	}
	
	public  void haveScanButton(){
		( (Button) activityContent.findViewById(R.id.button_scan)).setOnClickListener(this);
	}
	
	public void haveSendButton(){
		activityContent.findViewById(R.id.send).setOnClickListener(this);
	}
	
	public void haveProgressDialog(){
		dialog = ProgressDialog.show(activityContent, "", "查验中",true);
	}
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button_scan:
			Log.d("mainTab", "ContentManagner");
			Intent intent = new Intent();
			intent.setClass(activityContent, CaptureActivity.class);
	
			activityContent.startActivity(intent);		
			
			break;
		case R.id.send:	
//			Intent smsIntent = new Intent(SMSInputActivity.this,
//					SMSSendActivity.class);
//			smsIntent.putExtra(SMSInputActivity.INTENE_MESSAGE, message
//					.getText().toString());
//			startActivity(smsIntent);
			((SMSInputActivity) activityContent).sendSms();
			Log.d("mainTab", "sssssssssend!!!!!!");
			break;
		default:
			break;
		}
		
	}
	public  Intent setIntent(Class<?> cls,String queryKey,String result,String action){
		Intent intent = new Intent();
		intent.setClass(activityContent, cls);
		intent.setAction(action);
		intent.putExtra(queryKey, result);
		return intent;
	}
}
