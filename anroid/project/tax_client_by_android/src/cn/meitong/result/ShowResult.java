package cn.meitong.result;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import cn.meiton.action.ResultValues;
import cn.meitong.R;
import cn.meitong.listener.BackListener;
import cn.meitong.listener.TitleManager;

public class ShowResult extends ListActivity {
	
	
	private TextView mTitle;
	private TextView mCode;
	private String result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showresult);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText("返回结果");
	//	mCode = (TextView) findViewById(R.id.showrealut_Invoicenid);
		new TitleManager(this).getBack().setOnClickListener(new BackListener(this));
		//做测试使用
		Intent intent = getIntent();
		result = intent.getStringExtra(ResultValues.RESULT);
		
		if(result != null){
			mCode.setText(result);
		}
		
		
		
	}
	
	

}
