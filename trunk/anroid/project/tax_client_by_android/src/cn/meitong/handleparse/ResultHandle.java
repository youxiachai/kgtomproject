
package cn.meitong.handleparse;

import org.ksoap2.serialization.SoapObject;


import cn.meitong.model.TestModel;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.values.ActionValues;
import cn.meitong.values.ResultValues;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-3
 * 
 */
public class ResultHandle extends Handler {
	
	
	private ResultThread mThread;
	private Activity mActivity;
//	private SoapTest mActivity;
	
	
	
	
	private String message;

	public ResultHandle(Activity activity,String message,boolean isOnline){
		this.mActivity = activity;
		this.message = message;
		
		mThread = new ResultThread(this,message,isOnline);
		mThread.setmState(true);
		mThread.start();
	}
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case ResultValues.SUCCESS:
			Log.d("soap", "success------>");
			mThread.setmState(false);
			Bundle b = msg.getData();
			String result = b.getString(ResultValues.QUERYRESULT);
			Intent intent = new Intent();
			intent.setClass(mActivity, MainTabActivity.class);
			intent.putExtra(ResultValues.QUERYRESULT, result);
			intent.setAction(ActionValues.QUERYRESULT);
			mActivity.startActivity(intent);
			break;
		case ResultValues.FAIL:
			
			break;
		default:
			break;
		}
	}
}
