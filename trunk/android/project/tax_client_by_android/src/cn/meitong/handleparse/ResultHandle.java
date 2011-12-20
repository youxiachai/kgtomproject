package cn.meitong.handleparse;

import org.ksoap2.serialization.SoapObject;

import cn.meitong.model.TestModel;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.tab.query.HandmadeInputActivity;
import cn.meitong.values.ActionValues;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;
import android.app.Activity;
import android.app.ProgressDialog;
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
	private String message;
	private ProgressDialog dialog;
	private boolean isCheck;

	public ResultHandle(Activity activity, String message, boolean isCheck,
			ProgressDialog dialog) {
		this.mActivity = activity;
		this.message = message;
		this.dialog = dialog;
		this.isCheck = isCheck;
		mThread = new ResultThread(this, message);
		mThread.setmState(true);
		mThread.start();
	}

	private void dissmissDialog() {
		dialog.dismiss();
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
			dissmissDialog();
			if (isCheck) {
				Intent intent = new Intent();
				intent.setClass(mActivity, MainTabActivity.class);
				intent.putExtra(ResultValues.QUERYRESULT, result);
				intent.setAction(ActionValues.QUERYRESULT);
				mActivity.startActivity(intent);
			} else {
				// 处理中奖登记返回值
				Log.d("soap", "中奖登记处理");
				Intent intent = new Intent();
				intent.setClass(mActivity, MainTabActivity.class);
				intent.putExtra(ResultValues.QUERYRESULT, result);
				intent.setAction(ActionValues.LUCKYRESULT);
				mActivity.startActivity(intent);
			}

			break;
		case ResultValues.FAIL:
			dissmissDialog();
			ContentMananger.haveTips(mActivity, TipsValues.connectError);
			break;

		default:
			break;
		}
	}
}
