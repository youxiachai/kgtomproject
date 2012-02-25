package com.achai.shop.view;

import com.achai.R;
import com.achai.shop.backup.BackupTask;
import com.achai.shop.noitfy.UpdateStatics;

import android.app.Activity;
import android.content.Intent;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class StatisticsListening implements OnClickListener {
	
	private final StatisticsAcitvity statActivity ;
	
	public StatisticsListening(StatisticsAcitvity stat) {
		this.statActivity = stat;
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.stat_cost:
			Log.d("stat", "cost");
			//statActivity.costChartView();
			//statActivity.queryDayResult("2010", "1",1);
		//	statActivity.queryYearResult("2010");
//			Intent update = new Intent();
//			update.setClass(statActivity, UpdateStatics.class);
//			statActivity.startService(update);
			BackupTask bt = new BackupTask(statActivity);
			bt.execute(BackupTask.COMMAND_BACKUP);
			
			break;
		case R.id.stat_sales:
			Log.d("stat", "sales");
			statActivity.salesChartView();
			break;
		case R.id.stat_profit:
			Log.d("stat", "profit");
			statActivity.profitChartView();
			break;
		default:
			break;
		}

	}

}
