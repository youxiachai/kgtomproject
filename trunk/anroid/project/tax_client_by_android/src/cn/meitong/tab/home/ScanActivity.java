package cn.meitong.tab.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.decoder.CaptureActivity;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.tab.listener.MessageListener;
import cn.meitong.tab.listener.TitleManager;

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
	
		

	}


}
