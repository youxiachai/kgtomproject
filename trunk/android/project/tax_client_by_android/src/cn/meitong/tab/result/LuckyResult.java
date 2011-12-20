package cn.meitong.tab.result;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.meitong.R;
import cn.meitong.model.ResponseItem;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class LuckyResult extends Activity {
	private TextView replyCode;
	private TextView replyMsg;
	private TextView fpdm;
	private TextView fphm;
	private TextView kprq;
	private TextView hjJe;
	private TextView fkfSj;
	public String result;

	public ResponseItem responseItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.luckyresult);
		initTextView();
		//获取返回的值
		 Intent intent = getIntent();
		 result = intent.getStringExtra(ResultValues.QUERYRESULT);
			if (result != null) {
				Log.d("soap", result);
				responseItem = ContentMananger.parseResult(result);
				
				if(responseItem.replyCode.equals("1")){
					//
//					Toast.makeText(this, responseItem.replyMsg +"无返回结果,请检查发票输入是否正确", Toast.LENGTH_LONG).show();
					ContentMananger.haveTips(this, responseItem.replyMsg);
					String error = replyMsg(responseItem.replyMsg);
					if(error !=null){
						String fpdm = error.substring(0,12);
						String fphm = error.substring(12, 20);
						Log.d("soap", error);
						setQueryResult(fpdm,fphm);
					}
					
				}else{
					setQueryResult();
				}
			}
	}
	
	private void setQueryResult(String fpdm2, String fphm2) {
		fpdm.setText(fpdm2);
		fphm.setText(fphm2);
		kprq.setText(responseItem.kprq);
		hjJe.setText(responseItem.hjJe);
		fkfSj.setText(responseItem.fkfSj);
	}

	private String replyMsg(String replyMsg){
		List<String> matchList = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		try {
			Pattern regex = Pattern.compile("\\d");
			Matcher regexMatcher = regex.matcher(replyMsg);
			while (regexMatcher.find()) {
				matchList.add(regexMatcher.group());
			} 
			
			for (String string : matchList) {
				sb.append(string);
			}
			return sb.toString();
		} catch (PatternSyntaxException ex) {
			// Syntax error in the regular expression
		}
		
		return null;
	}

	private void initTextView() {
		fpdm = (TextView) findViewById(R.id.fpdm);
		fphm = (TextView) findViewById(R.id.fphm);
		hjJe = (TextView) findViewById(R.id.hjje);
		kprq = (TextView) findViewById(R.id.kprq);
		fkfSj = (TextView) findViewById(R.id.fkfsj);
		
	}
	private void setQueryResult() {
		fpdm.setText(responseItem.fpdm);
		fphm.setText(responseItem.fphm);
		kprq.setText(responseItem.kprq);
		hjJe.setText(responseItem.hjJe);
		fkfSj.setText(responseItem.fkfSj);
	}
}
