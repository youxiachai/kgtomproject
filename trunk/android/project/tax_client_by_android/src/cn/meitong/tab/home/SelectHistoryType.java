package cn.meitong.tab.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.values.ResultValues;

public class SelectHistoryType extends Activity{
	
	private TableRow historyQrcode;
	private TableRow historyInput;
	private TableRow historySms;
	String gettableName ;
	Intent direct;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selecttype);
		historyQrcode = (TableRow) findViewById(R.id.history_qrcode);
		historyInput = (TableRow) findViewById(R.id.history_input);
		historySms = (TableRow) findViewById(R.id.history_sms);
		 gettableName = getIntent().getStringExtra(ResultValues.TABLENAME);
		
		Log.d("history", ""+gettableName);
		historyQrcode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 direct = setIntent(ShowHistoryResult.class,gettableName,ResultValues.QueryType.qRcode);		
				startActivity(direct);
			}
		});
		
		historyInput.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				direct = setIntent(ShowHistoryResult.class, gettableName,ResultValues.QueryType.normal);
				startActivity(direct);
			}
		});
		
		historySms.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				direct = setIntent(ShowHistoryResult.class, gettableName,ResultValues.QueryType.sms);
				startActivity(direct);	
			}
		});
	}
	
	public Intent setIntent(Class<?> classname,String tableName,int type){
		Intent intent = new Intent().setClass(this, classname);
		Log.d("history", classname.getName());
		intent.putExtra(ResultValues.TABLENAME, tableName);
		intent.putExtra(ResultValues.QueryType.QUERYTYPE, type);
		return intent;
	}
	
}
