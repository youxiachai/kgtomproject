package cn.meitong.home;

import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;

public class SMSSendActivity extends Activity {
	
	private TextView sendToNo;
	private Button smsSend;
	private EditText message;
	private TextView mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_send);
		sendToNo = (TextView) findViewById(R.id.sendToNo);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText(R.string.title_sms_send);
		smsSend = (Button) findViewById(R.id.sms_send);
		smsSend.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String strNo = sendToNo.getText().toString();
                String strContent = message.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                PendingIntent sentIntent = PendingIntent.getBroadcast(SMSSendActivity.this, 0, new Intent(), 0);
               
                if (strContent.length() > 70) {
                    List<String> msgs = smsManager.divideMessage(strContent);
                    for (String msg : msgs) {
                        smsManager.sendTextMessage(strNo, null, msg, sentIntent, null);                        
                    }
                } else {
                    smsManager.sendTextMessage(strNo, null, strContent, sentIntent, null);
                }
                Toast.makeText(SMSSendActivity.this, "以发送", Toast.LENGTH_LONG).show();
            }
		});
		message = (EditText) findViewById(R.id.sms_message);
		message.setText(getIntent().getStringExtra(SMSInputActivity.INTENE_MESSAGE));
	}
}
