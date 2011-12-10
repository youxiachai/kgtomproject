
package cn.meitong.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.meitong.model.ResponseItem;
import cn.meitong.values.ResultValues;

import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-9
 * 
 */
public class OutResultParse extends DefaultHandler {
	
	StringBuffer buffer;
	final ResponseItem responseItem ;
	public OutResultParse(ResponseItem res){
		this.responseItem = res;
	}
	
	String tagName;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		super.startElement(uri, localName, qName, attributes);
		Log.d("soap","start"+ localName);
		Log.d("soap", "start"+qName);
		this.tagName = localName;
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		String temp = new String(ch,start,length);
		if(tagName.equals(ResultValues.Head.REPLYCODE)){
			responseItem.replyCode = temp;
		}
		
		if(tagName.equals(ResultValues.Head.REPLYMsg)){
			responseItem.replyMsg = temp;
			Log.d("soap", temp);
		}
		//查询的发票代码
		if(tagName.equals(ResultValues.Body.FPDM)){
			responseItem.fpdm = temp;
		}
		//查询的发票号码
		if(tagName.equals(ResultValues.Body.FPHM)){
			responseItem.fphm = temp;
		}
		//查询出的付款方名称，发票无记录时为空 
		if(tagName.equals(ResultValues.Body.FKFMC)){
			responseItem.fkfMc = temp;
		}
		//查询出的付款方证件号码，发票无记录时为空 
		if(tagName.equals(ResultValues.Body.FKFZJHM)){
			responseItem.fkfZjhm = temp;
		}
		//查询出的收款方名称，发票无记录时为空
		if(tagName.equals(ResultValues.Body.SKFMC)){
			responseItem.skfMc = temp;
		}
		//-查询出的收款方证件号码，发票无记录时为空
		if(tagName.equals(ResultValues.Body.SKFZJHM)){
			responseItem.skfZjhm = temp;
		}
		//-查询出的开票日期，发票无记录时为空 格式yyyy-MM-dd
		if(tagName.equals(ResultValues.Body.KPRQ)){
			responseItem.kprq = temp;
		}
		//查询的发票合计金额
		if(tagName.equals(ResultValues.Body.HJJE)){
			responseItem.hjJe = temp;
		}
		//查询出的税控码
		if(tagName.equals(ResultValues.Body.SKM)){
			responseItem.skm = temp;
		}
		//查询出的付款方手机号码(登记的手机号码)，发票无记录时为空
		if(tagName.equals(ResultValues.Body.FKFSJ)){
			responseItem.fkfSj = temp;
		}
		//查询出的中奖等级，发票无记录时为空 (预留)
		if(tagName.equals(ResultValues.Body.ZJDJ)){
			responseItem.zjDj = temp;
		}
		//查询出的中奖金额，发票无记录时为空 (预留)
		if(tagName.equals(ResultValues.Body.ZJJE)){
			responseItem.zjJe = temp;
		}
		//查询出的中奖期数，发票无记录时为空 (预留)
		if(tagName.equals(ResultValues.Body.ZJQS)){
			responseItem.zjQs = temp;
		}
		//查询出的查验次数，发票无记录时为空 (预留)
		if(tagName.equals(ResultValues.Body.CYCS)){
			responseItem.cyCs = temp;
		}
		//发票状态代码
		if(tagName.equals(ResultValues.Body.FPZTMC)){
			responseItem.fpztMc = temp;
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
	}
}
