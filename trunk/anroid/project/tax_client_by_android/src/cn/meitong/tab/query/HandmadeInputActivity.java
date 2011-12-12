package cn.meitong.tab.query;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;
import cn.meitong.handleparse.ResultHandle;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.tab.listener.TitleManager;
import cn.meitong.values.ResultValues;

public class HandmadeInputActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handmadeinput);
        initEdit();
      
    }
	private EditText mfpdm;
	private EditText mfphm;
	private EditText mfpje;
	private void initEdit() {
		mfpdm = (EditText) findViewById(R.id.edit_fpdm);
		mfphm = (EditText) findViewById(R.id.edit_fphm);
		mfpje = (EditText) findViewById(R.id.edit_fpje);
	}
	public void sendToQuery(){
//		金额:51327|
//		代码:244011106020
//		号码:|05369908
		String fpdm = mfpdm.getText().toString();
		String fphm = mfphm.getText().toString();
		String fpje = mfpje.getText().toString();
		Log.d("soap", fpdm+""+fphm+fpje);
//		String queryValues = "244011106020"+","+"05369908"+","+"51327";
//		String message = ContentMananger.queryValues(ResultValues.QueryType.normal, queryValues, this);
//		ResultHandle rh = new ResultHandle(this, message, true);
		Toast.makeText(this,"输入查询"+ fpdm+fphm+fpje, Toast.LENGTH_SHORT);
	}
}
