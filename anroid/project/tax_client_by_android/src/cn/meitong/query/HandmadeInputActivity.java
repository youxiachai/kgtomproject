package cn.meitong.query;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.listener.BackListener;
import cn.meitong.listener.ButtonManager;

public class HandmadeInputActivity extends Activity {
	private TextView mTitle;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handmadeinput);
        mTitle = (TextView) findViewById(R.id.titleText);
        mTitle.setText(R.string.title_input);
        new ButtonManager(this).getmBack().setOnClickListener(new BackListener(this));
    }
	
}