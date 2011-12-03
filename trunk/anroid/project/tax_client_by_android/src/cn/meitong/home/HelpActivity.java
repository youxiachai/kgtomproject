package cn.meitong.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.help.Help_1;
import cn.meitong.help.Help_2;
import cn.meitong.help.Help_3;
import cn.meitong.listener.BackListener;
import cn.meitong.listener.ButtonManager;

public class HelpActivity extends Activity {
    private TextView mTitle;
    private TableRow top, middle, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.help);
	mTitle = (TextView) findViewById(R.id.titleText);
	mTitle.setText(R.string.title_help);

	top = (TableRow) findViewById(R.id.help_top);
	middle = (TableRow) findViewById(R.id.help_middle);
	bottom = (TableRow) findViewById(R.id.help_bottom);
	top.setOnClickListener(new HelpOnClickListener());
	middle.setOnClickListener(new HelpOnClickListener());
	bottom.setOnClickListener(new HelpOnClickListener());
	new ButtonManager(this).getmBack().setOnClickListener(
		new BackListener(this));
    }

    class HelpOnClickListener implements OnClickListener {

	@Override
	public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.help_top:
		Intent help_1 = new Intent(HelpActivity.this, Help_1.class);
		startActivity(help_1);
		break;
	    case R.id.help_middle:
		Intent help_2 = new Intent(HelpActivity.this, Help_2.class);
		startActivity(help_2);
		break;
	    case R.id.help_bottom:
		Intent help_3 = new Intent(HelpActivity.this, Help_3.class);
		startActivity(help_3);
		break;
	    }
	}

    }
}
