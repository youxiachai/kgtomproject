
package cn.meitong.parse;

import org.ksoap2.serialization.SoapObject;



import cn.meitong.model.TestModel;
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
	//private SoapTest mActivity;
	
	
	public ResultHandle(Activity activity){
		this.mActivity = activity;
		mThread = new ResultThread(this);
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
			TestModel tm = (TestModel) b.getSerializable("soap");
			Log.d("soap", tm.getT1()+"---"+tm.getT2());
			
		
			
//			Intent intent = new Intt();
//			mActivity.startActivity(intent);
			break;
		case ResultValues.FAIL:
			
			break;
		default:
			break;
		}
	}
}
