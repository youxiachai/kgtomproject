
package cn.meitong.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import cn.meitong.R;
import cn.meitong.model.ResponseItem;
import cn.meitong.sax.ResponseParse;
import cn.meitong.sax.OutResultParse;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-9
 * 
 */
public class SaxTest extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		//1生成解析器对象
		SAXParserFactory saxf = SAXParserFactory.newInstance();
		
		
		try {
			InputStream is = getAssets().open("response.xml");
			XMLReader reader = saxf.newSAXParser().getXMLReader();
			//2 利用handle
			ResponseParse queryItem = new ResponseParse();
			reader.setContentHandler(queryItem);
			
			//3 解析 xml文件
			//reader.parse(new )
			reader.parse(new InputSource(is));
			String queryResult = queryItem.queryResult;
			Log.d("soap", ""+queryResult);
		//	XMLReader queryXML = saxf.newSAXParser().getXMLReader();
			ResponseItem ri = new ResponseItem();
			OutResultParse rp = new OutResultParse(ri);
			reader.setContentHandler(rp);
			reader.parse(new InputSource(new StringReader(queryResult)));
			Log.d("soap", "ok-->"+ri.replyMsg);
			Log.d("soap", "ok-->"+ri.hjJe);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
