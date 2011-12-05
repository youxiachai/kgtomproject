package cn.meitong.tab.result;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.values.ResultValues;

public class ShowResult extends Activity {
	
	

	private TextView mCode;
	private String result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showresult);
	
	//	mCode = (TextView) findViewById(R.id.showrealut_Invoicenid);
		mCode = (TextView) findViewById(R.id.resulttest);
		//做测试使用
		Intent intent = getIntent();
		result = intent.getStringExtra(ResultValues.RESULT);
		
		if(result != null){
			mCode.setText(result);
		}
		
		
		
	}
	
	

}
