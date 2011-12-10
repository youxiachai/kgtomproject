package cn.meitong.handleparse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.AttributeInfo;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import cn.meitong.model.Channel;
import cn.meitong.model.RequestItem;
import cn.meitong.model.TestModel;
import cn.meitong.soap.SoapUtils;
import cn.meitong.values.ResultValues;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-3
 * 
 */
public class ResultThread extends Thread {

	private Handler mHandle;

	private String message;
	private boolean mState = false;

	public void setmState(boolean mState) {
		this.mState = mState;
	}


	private boolean isOnline;

	public ResultThread(ResultHandle handle, String message, 
			boolean isOnline) {
		this.mHandle = handle;
		this.message = message;

		this.isOnline = isOnline;
	}

	private List<RequestItem> parseRequestItem(String message, int type) {

		List<RequestItem> rilist = new ArrayList<RequestItem>();
		RequestItem ri;
		switch (type) {
		case ResultValues.QueryType.normal:

			return rilist;
		case ResultValues.QueryType.qRcode:
			ri = new RequestItem();
			ri.mmq = message;
			rilist.add(ri);
			Log.d("soap", "size" + rilist.size());
			return rilist;
		default:
			break;
		}

		Log.d("soap", "request----->size" + rilist.size());
		return null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		if (mState) {
			Log.d("soap", "run------->start");
			// startSend(queryType);
			// ----
			if (isOnline) {
				startSend();
			} else {
			
			}
		}
	}



	/**
	 * 用于向webServices 发送soap 请求 和 获得soap 的返回结果
	 */
	private void startSend() {
		String password = "unicom(&^%#@";
		String username = "gdds_fpzx_chinaunicom";

		SoapUtils su = new SoapUtils();	
		String result = su.sendRequest(username, password, message);
		
		if(result != null){
			Log.d("soap", "success");
			Message m = mHandle.obtainMessage(ResultValues.SUCCESS);
			Bundle b = new Bundle();
			b.putString(ResultValues.QUERYRESULT, result);
			m.setData(b);
			mHandle.sendMessage(m);
			
		}else{
			Log.d("soap", "fail!!!!!");
			Message m = mHandle.obtainMessage(ResultValues.FAIL);
			mHandle.sendMessage(m);
			
		}

	}
	
	private void handleResults(String result){
		
	}

	private void handleResults(SoapObject result) {
		// 如果返回的结果不为空开始构造返回的字符串
		if (result != null) {
			Log.d("soap", "result!!!!!!!");
			Message m = mHandle.obtainMessage(1);
			Bundle b = new Bundle();
			int count = result.getPropertyCount();
			// 构造返回的对象
			TestModel tm = new TestModel();
			tm.setT1(result.getPropertyAsString(0));
			tm.setT2(result.getPropertyAsString(1));
			b.putSerializable("soap", tm);

			Log.d("soap", "count-->" + count);
			m.setData(b);
			Log.d("soap", "run------->finish");
			mHandle.sendMessage(m);
		} else {
			Log.d("soap", "fail!!!!!");
			Message m = mHandle.obtainMessage(2);
		}
	}

}
