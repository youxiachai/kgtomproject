
package cn.meitong.home;


import cn.meitong.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author Tom_achai
 * @date 2011-11-21
 * 
 */
public class ScanActivity extends Activity {
	
	private TextView mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText(R.string.title_scan);
	}
}
