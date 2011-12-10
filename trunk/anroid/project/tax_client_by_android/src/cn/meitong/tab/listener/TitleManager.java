
package cn.meitong.tab.listener;

import cn.meitong.R;
import android.app.Activity;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * 
 * 用于管理title 的 
 * 左右两个按钮 和中间title的处理
 * @author Tom_achai
 * @date 2011-11-22
 * 
 */
public final class TitleManager {

	private  Activity activityButton;
	
	public TitleManager(Activity activity){
		this.activityButton = activity;
	}
	
	public Button getBack() {
		return (Button) activityButton.findViewById(R.id.titleBack);
	}
	
	public RadioButton getRadioBack(){
		return (RadioButton) activityButton.findViewById(R.id.titleBack);
	}
	
	public Button getQuery(){
		return (Button) activityButton.findViewById(R.id.titleQuery);
	}
	public TextView getTitle(){
		return (TextView) activityButton.findViewById(R.id.titleText);
	}
}
