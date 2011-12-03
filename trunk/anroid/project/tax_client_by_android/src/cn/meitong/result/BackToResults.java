package cn.meitong.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.SimpleAdapter;
import cn.meitong.R;
import cn.meitong.listener.BackListener;
import cn.meitong.listener.ButtonManager;

public class BackToResults extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.backtoresults);
		new ButtonManager(this).getmBack().setOnClickListener(new BackListener(this));
		Invoice aa = new Invoice("aa","bb");
		
		List<Map<String, String>> data = new ArrayList<Map<String,String>>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "title");
		map.put("value", "value");
		data.add(map);
		
		SimpleAdapter myAdapter = new SimpleAdapter(this, data, R.layout.result_item, null, null);
		
		this.setListAdapter(myAdapter);
	}

	
}
