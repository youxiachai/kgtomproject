package cn.meitong.result;

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
            // ��һ��android�ĵ��й���LayoutInflater�Ķ���� 
            // This class is used to instantiate layout XML file into its corresponding View objects. 
            // It is never be used directly -- use getLayoutInflater() or getSystemService(String) 
            // to retrieve a standard LayoutInflater instance that is already hooked up to the current 
            // context and correctly configured for the device you are running on. . For example: 
            String inflater = Context.LAYOUT_INFLATER_SERVICE; 
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater); 
            vi.inflate(res, myLayout, true); 
            // ���ÿ��select imagesʱ�������� 
        } else { 
            // ����ֻ�û�����ù� 
            myLayout = (LinearLayout)convertView; 
        }
		
		TextView pnum = (TextView)myLayout.findViewById(R.id.num);
		TextView id = (TextView)myLayout.findViewById(R.id.showrealut_Invoiceid);
		TextView num = (TextView)myLayout.findViewById(R.id.showrealut_Invoicenum);
		
		pnum.setText("��¼"+String.valueOf(position)+"��");
		id.setText(data.getInvoiceId());
		num.setText(data.getInvoiceNum());
		
		return myLayout;
	}

	
}
