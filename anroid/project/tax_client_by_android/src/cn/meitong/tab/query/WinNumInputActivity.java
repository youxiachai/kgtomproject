package cn.meitong.tab.query;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.TitleManager;

public class WinNumInputActivity extends Activity {
	private TextView mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winnum);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText(R.string.title_lucky);
		new TitleManager(this).getBack().setOnClickListener(new BackListener(this));
	}
}
