package cn.meitong.tab.home;

import android.app.Activity;
import android.os.Bundle;
import cn.meitong.R;
import cn.meitong.tab.listener.ContentMananger;



/**
 * @author Tom_achai
 * @date 2011-11-21
 * 
 */
public class ScanActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		
		new ContentMananger(this).haveScanAction();
	
		new ContentMananger(this).haveHistoryfpCheck();
		
		new ContentMananger(this).haveHistoryfpDj();
	}


}
