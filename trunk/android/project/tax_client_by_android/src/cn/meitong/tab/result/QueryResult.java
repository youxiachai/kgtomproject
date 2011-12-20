package cn.meitong.tab.result;

import java.io.StringReader;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import cn.meitong.R;
import cn.meitong.model.ResponseItem;
import cn.meitong.sax.OutResultParse;
import cn.meitong.sax.ResponseParse;
import cn.meitong.tab.home.MainTabActivity;
import cn.meitong.tab.listener.ContentMananger;
import cn.meitong.tab.query.LuckyInput;
import cn.meitong.values.ResultValues;
import cn.meitong.values.TipsValues;

public class QueryResult extends Activity {

	private TextView replyCode;
	private TextView replyMsg;
	private TextView fpdm;
	private TextView fphm;
	private TextView fkfMc;
	private TextView fkfZjhm;
	private TextView skfMc;
	private TextView skfZjhm;
	private TextView kprq;
	private TextView hjJe;
	private TextView skm;
	private TextView fkfSj;
	private TextView zjDj;
	private TextView zjJe;
	private TextView zjQs;
	private TextView cyCs;

	private TextView fpztMc;
	private TextView cxjg;
	public String result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showresult);
		initTextView();
		Log.d("soap", "queryResultcreated");
	
	
		//获取返回的值
		 Intent intent = getIntent();
		 result = intent.getStringExtra(ResultValues.QUERYRESULT);
		if (result != null) {
			//parseResult(result);
		//	ContentMananger.parseResult(result, responseItem);
			responseItem = ContentMananger.parseResult(result);
			if(responseItem != null){
				if(responseItem.replyCode.equals("1")){
					//
					ContentMananger.haveTips(this, responseItem.replyMsg);
				
					setQueryResult();
				}else{
					ContentMananger.haveTips(this, responseItem.replyMsg);
					if(responseItem.fpdm == null){
						ContentMananger.haveTips(this, "无发票登记信息");
					}
					setQueryResult();
				}
			}else{
				ContentMananger.haveTips(this, TipsValues.connectError);
			}
			
		}

	}
	public void showTips(){
		Toast.makeText(this, "无返回结果,无法提交", Toast.LENGTH_LONG).show();
	}
	private void setQueryResult() {
		fpdm.setText(responseItem.fpdm);
		fphm.setText(responseItem.fphm);
		fkfMc.setText(responseItem.fkfMc);
		fkfZjhm.setText(responseItem.fkfZjhm);
		skfMc.setText(responseItem.skfMc);
		skfZjhm.setText(responseItem.skfZjhm);
		kprq.setText(responseItem.kprq);
		hjJe.setText(responseItem.hjJe);
		skm.setText(responseItem.skm);
		fkfSj.setText(responseItem.fkfSj);
		zjDj.setText(responseItem.zjDj);
		zjJe.setText(responseItem.zjJe);
		zjQs.setText(responseItem.zjQs);
		cyCs.setText(responseItem.cyCs);
		
		fpztMc.setText(responseItem.fpztMc);
	}

	public ResponseItem responseItem;

	public void initTextView() {
		fpdm = (TextView) findViewById(R.id.fpdm);
		fphm = (TextView) findViewById(R.id.fphm);
		fkfMc = (TextView) findViewById(R.id.fkfmc);
		fkfZjhm = (TextView) findViewById(R.id.fkfzjhm);
		skfMc = (TextView) findViewById(R.id.skfmc);
		skfZjhm = (TextView) findViewById(R.id.skfzjhm);
		kprq = (TextView) findViewById(R.id.kprq);
		hjJe = (TextView) findViewById(R.id.hjje);
		skm = (TextView) findViewById(R.id.skm);
		fkfSj = (TextView) findViewById(R.id.fkfsj);
		zjDj = (TextView) findViewById(R.id.zjdj);
		zjJe = (TextView) findViewById(R.id.zjje);
		zjQs = (TextView) findViewById(R.id.zjqs);
		cyCs = (TextView) findViewById(R.id.cycs);

		fpztMc = (TextView) findViewById(R.id.fpztmc);
	}

	public String parseResult(String queryResult) {

		// 1 生成SAX工厂
		SAXParserFactory saxFactory = SAXParserFactory.newInstance();
		responseItem = new ResponseItem();
		try {
			XMLReader responseReader = saxFactory.newSAXParser().getXMLReader();
			// 2 利用handle 异步解析xml
			ResponseParse outResponse = new ResponseParse();
			responseReader.setContentHandler(outResponse);
			responseReader
					.parse(new InputSource(new StringReader(queryResult)));
			String out = outResponse.queryResult;
			Log.d("soap", out);

			// 3 处理 最终结果
			OutResultParse outResult = new OutResultParse(responseItem);
			responseReader.setContentHandler(outResult);
			responseReader.parse(new InputSource(new StringReader(out)));
			Log.d("soap","responiten--->"+ responseItem.toString());
//			Intent luckIntent = new Intent().setClass(this, LuckyInput.class);
//			luckIntent.putExtra("luckyresult", responseItem);
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
	
}
