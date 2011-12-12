package cn.meitong.tab.query;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.TitleManager;

public class PhoneInputActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phoneinput);
		mPhoneInput = (EditText) findViewById(R.id.phone_input);
	}
	private EditText mPhoneInput;
	
	public void sendToQuery(){
		String phoneNumber = mPhoneInput.getText().toString();
		Toast.makeText(this, "手机号码查验"+phoneNumber, Toast.LENGTH_LONG).show();
	}
}
