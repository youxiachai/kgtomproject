package cn.meitong.result;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import cn.meitong.R;

public class BackToResults extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.backtoresults);
		
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
