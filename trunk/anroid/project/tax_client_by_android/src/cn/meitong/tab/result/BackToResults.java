package cn.meitong.tab.result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.app.ListActivity;
import android.os.Bundle;
import cn.meitong.R;
import cn.meitong.tab.listener.BackListener;
import cn.meitong.tab.listener.TitleManager;

public class BackToResults extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.backtoresults);
		new TitleManager(this).getBack().setOnClickListener(new BackListener(this));
		Invoice aa = new Invoice("aa","bb");
		
		ArrayList<Invoice> ada = new ArrayList<Invoice>();
		ada.add(aa);
		ada.add(aa);
		ada.add(aa);
		ada.add(aa);
		ada.add(aa);
		ada.add(aa);
		
		
		MyAdapter myAdapter = new MyAdapter(this, R.layout.result_item, ada);
		
		this.setListAdapter(myAdapter);
	}

	
}
