package cn.meitong.tab.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import cn.meitong.R;
import cn.meitong.history.HistoryHelper;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.values.ActionValues;
import cn.meitong.values.DataBaseValues;
import cn.meitong.values.ResultValues;

public class ShowHistoryResult extends ListActivity {
	private ArrayList<Map<String, String>> queryResult;
	private String tableName;
	private int historyType;
	private ContentMananger cm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fpcheck);
		tableName = getIntent().getStringExtra(ResultValues.TABLENAME);
		Log.d("history",""+ tableName);
		historyType = getIntent().getIntExtra(ResultValues.QueryType.QUERYTYPE,
				0);

		if (tableName != null) {
			queryResult = queryDateBase(tableName, historyType);
			
			SimpleAdapter adapter = new SimpleAdapter(this, queryResult,
					R.layout.history_item, new String[] {
							DataBaseValues.Column.DATE,
							DataBaseValues.Column.RESULT }, new int[] {
							R.id.history_date, R.id.history_result });
			this.setListAdapter(adapter);
		}
		cm = new ContentMananger(this);
		switch (historyType) {
		case ResultValues.QueryType.qRcode:
			cm.changeTitle(R.string.history_qrcorde);
			break;
		case ResultValues.QueryType.normal:
			cm.changeTitle(R.string.history_input);
			break;
		default:
			break;
		}
		cm.haveBack();
		cm.haveHideQuery();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		Map<String, String> tempResult = queryResult.get(position);
		String result = tempResult.get(DataBaseValues.Column.RESULT);
		Intent intent = new ContentMananger(this).setIntent(
				MainTabActivity.class, ResultValues.QueryKey.HISTORY, result,
				ActionValues.SHOWHISTORY);
		intent.putExtra(ResultValues.QueryType.QUERYTYPE, historyType);
		startActivity(intent);
	}

	private ArrayList<Map<String, String>> queryDateBase(String tableName,
			int historyType) {
		Log.d("history", tableName+ " "+ historyType);
		HistoryHelper hh = new HistoryHelper(this);
		SQLiteDatabase sdb = hh.getReadableDatabase();
		String querySql = "select * from " + tableName +" where type="+historyType;

		Cursor cursor = sdb.rawQuery(querySql, null);
		ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();

		while (cursor.moveToNext()) {
			Map<String, String> map = new HashMap<String, String>();
			String id = cursor.getString(cursor
					.getColumnIndex(DataBaseValues.Column.ID));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String result1 = cursor.getString(cursor
					.getColumnIndex(DataBaseValues.Column.RESULT));
			map.put(DataBaseValues.Column.DATE, date);
			map.put(DataBaseValues.Column.RESULT, result1);
			result.add(map);
		}
		sdb.close();
		return result;
	}

}
