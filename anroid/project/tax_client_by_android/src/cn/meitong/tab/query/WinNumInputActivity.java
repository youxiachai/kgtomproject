package cn.meitong.tab.query;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.tab.listener.MessageListener;
import cn.meitong.tab.listener.TitleManager;

public class WinNumInputActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.luckyresult);
		//扫描查询
		new ContentMananger(this).haveScanAction();
		//手机号码查询
		new ContentMananger(this).havaInputPhoneNo();
		//输入查询
		new ContentMananger(this).haveInputQuery();
	}
}
