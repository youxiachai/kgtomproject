
package cn.meitong.decoder;

import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-13
 * 
 */
public class TimeOver extends TimerTask {

	private final Handler mHandler;
	private  int remainingIndividualSeconds;
	public boolean mState;
	public TimeOver(Handler handler,int seconds) {
		this.mHandler = handler;
		this.remainingIndividualSeconds = seconds;
	}
	
	@Override
	public void run() {
		if(mState){
			updateTimerValues();
		}
	}
	 protected synchronized void updateTimerValues() 
     {
		 Log.d("soap", "timer"+remainingIndividualSeconds);
		 remainingIndividualSeconds--;
		 if(remainingIndividualSeconds == 0){
			 Log.d("soap", "time over");
			 mState = false;
			Message m = mHandler.obtainMessage(1);
			mHandler.sendMessage(m);
		 }
		 
     }
}
