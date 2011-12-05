
package cn.meition.test;


import cn.meitong.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * @author Tom_achai
 * @date 2011-12-5
 * 
 */
public class ResultTest extends Activity {
	public TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.test);
		tv = (TextView) findViewById(R.id.textView1);
		tv.setText("hello world");
		 r = getIntent().getStringExtra("result");
		Intent getintent =getIntent();
		if(r != null){
			tv.setText(r);
		}
	}
	String r;
	public String printCurr(){
		return r;
	}
}
