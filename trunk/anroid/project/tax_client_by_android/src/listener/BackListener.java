
package listener;

import cn.meiton.action.ActionValues;
import cn.meitong.home.MainTabActivity;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author Tom_achai
 * @date 2011-11-22
 * 
 */
public final class BackListener implements OnClickListener {

	private final Activity activityFinish;
	
	public BackListener(Activity activity){
		this.activityFinish = activity;
	}
	@Override
	public void onClick(View v) {
		run();
	}


	private void run() {
	
		Intent intent = new Intent().setClass(activityFinish, MainTabActivity.class);
		intent.setAction(ActionValues.SCAN_BACK);
		activityFinish.startActivity(intent);
	//	activityFinish.finish();
	}

}
