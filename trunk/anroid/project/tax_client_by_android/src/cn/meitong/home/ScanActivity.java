
package cn.meitong.home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.decoder.CaptureActivity;

/**
 * @author Tom_achai
 * @date 2011-11-21
 * 
 */
public class ScanActivity extends Activity {
	
	private TextView mTitle;
	private Button mScan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		mTitle = (TextView) findViewById(R.id.titleText);
		mScan = (Button) findViewById(R.id.button_scan);
		mTitle.setText(R.string.title_scan);
		
	}
	
	class ScanListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent().setClass(ScanActivity.this, CaptureActivity.class);
			startActivity(intent);
		}
		
	}
}
