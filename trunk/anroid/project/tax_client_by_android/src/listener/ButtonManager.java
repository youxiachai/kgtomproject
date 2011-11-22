
package listener;

import cn.meitong.R;
import android.app.Activity;
import android.widget.Button;

/**
 * @author Tom_achai
 * @date 2011-11-22
 * 
 */
public final class ButtonManager {
	private Button mBack;

	private  Activity activityButton;
	
	public ButtonManager(Activity activity){
		this.activityButton = activity;
	}
	
	public Button getmBack() {
		return (Button) activityButton.findViewById(R.id.titleBack);
	}
}
