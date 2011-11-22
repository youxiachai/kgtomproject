package cn.meitong.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.meitong.R;

public class SMSInputActivity extends Activity {

	private Button send;
	private EditText message;
	public final static String INTENE_MESSAGE = "sms_message";
	private TextView mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_input);
		send = (Button) findViewById(R.id.send);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText(R.string.title_sms);
		send.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent smsIntent = new Intent(SMSInputActivity.this, SMSSendActivity.class);
				smsIntent.putExtra(SMSInputActivity.INTENE_MESSAGE, message.getText()
						.toString());
				startActivity(smsIntent);

			}
		});
		message = (EditText) findViewById(R.id.sms_input);
	}
}
