package cn.meitong.query;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.listener.BackListener;
import cn.meitong.listener.ButtonManager;

public class WinNumInputActivity extends Activity {
	private TextView mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winnum);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText(R.string.title_lucky);
		new ButtonManager(this).getmBack().setOnClickListener(new BackListener(this));
	}
}
