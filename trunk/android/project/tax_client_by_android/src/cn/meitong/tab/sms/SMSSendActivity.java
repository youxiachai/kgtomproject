package cn.meitong.tab.sms;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;
import cn.meitong.history.HistoryUtils;
import cn.meitong.tab.listener.BackListener;

import cn.meitong.values.DataBaseValues;
import cn.meitong.values.ResultValues;

public class SMSSendActivity extends Activity {
	
	private TextView sendToNo;
	private Button smsSend;
	private EditText message;
	private String rs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms_send);
		sendToNo = (TextView) findViewById(R.id.sendToNo);
		
		
	
		smsSend = (Button) findViewById(R.id.sms_send);
		smsSend.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String strNo = sendToNo.getText().toString();
                String strContent = message.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();
                PendingIntent sentIntent = PendingIntent.getBroadcast(SMSSendActivity.this, 0, new Intent(), 0);
               
                PendingIntent deliverPI = PendingIntent.getBroadcast(SMSSendActivity.this,0,new Intent(),0);
                if (strContent.length() > 70) {

                    ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
                    ArrayList<PendingIntent> delivers = new ArrayList<PendingIntent>();
                    ArrayList<String> msgs = smsManager.divideMessage(strContent);
                    for(int i=0; i < msgs.size();i++){
                    	sentIntents.add(sentIntent);
                    	delivers.add(deliverPI);
                    }
                    smsManager.sendMultipartTextMessage(strNo, null, msgs, sentIntents,  delivers);
                } else {
                	
                    smsManager.sendTextMessage(strNo, null, strContent, sentIntent, null);
                	 
                }
                Toast.makeText(SMSSendActivity.this, "已发送", Toast.LENGTH_LONG).show();
            }
		});
		message = (EditText) findViewById(R.id.sms_message);
		
		String smsResult = getIntent().getStringExtra(ResultValues.SMS_RESULT);
		String smsQrcode = getIntent().getStringExtra(ResultValues.SMS_QRCODE);
		if(smsQrcode != null){
			if(smsResult != null){
				new HistoryUtils(this).insertResult(DataBaseValues.CHECKTABLE, smsQrcode, ResultValues.QueryType.sms);
				message.setText(smsQrcode);
				 String strContent = message.getText().toString();
				 Log.d("soap","length"+strContent.length());
				rs = getIntent().getStringExtra(ResultValues.SMS_RESULT);
				String [] show = rs.split(",");
				TextView tv1 = (TextView) findViewById(R.id.sms_send_fpdm);
				tv1.setText(show[1]);
				TextView tv2 = (TextView) findViewById(R.id.sms_send_fphm);
				tv2.setText(show[2]);
				TextView tv3 = (TextView) findViewById(R.id.sms_send_fpje);
				tv3.setText(show[3]);
			}
		}else{
			if(smsResult != null){
				message.setText(smsResult);
				new HistoryUtils(this).insertResult(DataBaseValues.DJTABLE, smsResult, ResultValues.QueryType.sms);
				rs = getIntent().getStringExtra(ResultValues.SMS_RESULT);
				String [] show = rs.split(",");
				TextView tv1 = (TextView) findViewById(R.id.sms_send_fpdm);
				tv1.setText(show[1]);
				TextView tv2 = (TextView) findViewById(R.id.sms_send_fphm);
				tv2.setText(show[2]);
				TextView tv3 = (TextView) findViewById(R.id.sms_send_fpje);
				tv3.setText(show[3]);
			}
		}

		
		
	}
}
