package cn.meitong.home;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.listener.BackListener;
import cn.meitong.listener.ButtonManager;

public class HelpActivity extends Activity {
	private TextView mTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		mTitle = (TextView) findViewById(R.id.titleText);
		mTitle.setText(R.string.title_help);
		new ButtonManager(this).getmBack().setOnClickListener(new BackListener(this));
	}
}
