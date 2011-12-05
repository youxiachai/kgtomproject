package cn.meitong.tab.sms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.tab.listener.TitleManager;
import cn.meitong.values.ResultValues;

public class SMSInputActivity extends Activity {

	private Button send;
	private EditText message;
	public final static String INTENE_MESSAGE = "sms_message";
	private TextView mTitle;
	private String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_input);
		result = getIntent().getStringExtra(ResultValues.RESULT);

	
		new ContentMananger(this).haveSendButton();
	
	
		message = (EditText) findViewById(R.id.sms_input);
		if (result != null) {
			message.setText(result);
		}
	}
	
	public void sendSms(){
		Log.d("mainTab", "sssssssss");
	}

	
}
