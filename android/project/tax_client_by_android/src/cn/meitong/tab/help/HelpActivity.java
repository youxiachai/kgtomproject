package cn.meitong.tab.help;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
import android.widget.TextView;
import cn.meitong.R;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.values.ActionValues;


public class HelpActivity extends Activity {
    private TextView mTitle;
    private TableRow top, middle, bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.help);


	top = (TableRow) findViewById(R.id.help_top);
	middle = (TableRow) findViewById(R.id.help_middle);
	bottom = (TableRow) findViewById(R.id.help_bottom);
//	top.setOnClickListener(new HelpOnClickListener());
	middle.setOnClickListener(new HelpOnClickListener());
	bottom.setOnClickListener(new HelpOnClickListener());

    }

    class HelpOnClickListener implements OnClickListener {


	public void onClick(View v) {
	    switch (v.getId()) {
	    case R.id.help_top:
		Intent help_1 = new Intent(HelpActivity.this, Help_1.class);
		startActivity(help_1);
		break;
	    case R.id.help_middle:
		Intent help_2 = new ContentMananger(HelpActivity.this).setIntent(MainTabActivity.class, null, null, ActionValues.HELP2);
		startActivity(help_2);
		break;
	    case R.id.help_bottom:
	    	Intent help_3 = new ContentMananger(HelpActivity.this).setIntent(MainTabActivity.class, null, null, ActionValues.HELP3);
			startActivity(help_3);
		break;
	    }
	}

    }
}
