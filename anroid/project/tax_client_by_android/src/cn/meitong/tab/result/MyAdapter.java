package cn.meitong.tab.result;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.meitong.R;

public class MyAdapter extends ArrayAdapter<Invoice> {

	private int res;
	Context context;
	public MyAdapter(Context context, int res, List<Invoice> objects) {
		super(context, res, objects);
		// TODO Auto-generated constructor stub
		this.res = res;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout myLayout;
		// TODO Auto-generated method stub
		Invoice data = getItem(position);
		
		
		if(convertView == null) { 
            myLayout = new LinearLayout(getContext()); 

            String inflater = Context.LAYOUT_INFLATER_SERVICE; 
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater); 
            vi.inflate(res, myLayout, true); 
        } else { 
            myLayout = (LinearLayout)convertView; 
        }
		
		TextView title = (TextView)myLayout.findViewById(R.id.invoketitle);
		TextView value = (TextView)myLayout.findViewById(R.id.invokevalue);
		
		title.setText(data.Invoicetitle);
		value.setText(data.Invoicevalue);
		
		return myLayout;
	}

	
}
