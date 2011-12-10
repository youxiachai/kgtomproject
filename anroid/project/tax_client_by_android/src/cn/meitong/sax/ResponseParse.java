
package cn.meitong.sax;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

import cn.meitong.model.RequestItem;
import cn.meitong.values.ResultValues;

/**
 * 讲返回的信息中的内容抽取出来
 * @author Tom_achai
 * @date 2011-12-9
 * 
 */
public class ResponseParse extends DefaultHandler {
	
	RequestItem ri = new RequestItem();

	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		Log.d("soap", "start!!");
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	private String out;
	private StringBuffer sb;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		
		if(qName.equals("ns1:out")){
			sb = new StringBuffer();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		
		if(qName.equals("ns1:out")){
			String result = sb.toString();
			queryResult = result;
			
		}
	}
	public String queryResult;
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
	
		if(sb != null){
			for(int i = start; i < start+length; i++){
				
				if(ch[i] == '\n'){
					return;
				}
				sb.append(ch[i]);
			//	Log.d("soap", "ch-->"+ch[i]);
			}
		}
	}	
	
	
}
