package cn.meitong.values;

import android.content.Context;
import android.content.Intent;

/**
 * @author Tom_achai
 * @date 2011-11-24
 * 
 */
public final class ResultValues {
	public final static String RESULT = "result";
	public final static String QRRESULT = "qrresult";
	//用于测试soap
	//public final static String NAMESPACE = "http://WebXml.com.cn/";
	//public final static String WSDL = "http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";

//	public final static String METHOD = "getSupportProvince";
	
	//税务的wsdl ns
	public final static String NAMESPACE = "http://services.fpzx.tax.foresee.com";
	public final static String WSDL = "http://www.gdltax.gov.cn/wssw-webservice/services/FpzxService?wsdl";
	public final static String METHOD = "invoke";
	
	public final static int SUCCESS = 1;
	public final static int FAIL = 0;

	// 用于构造soap 主体的字段
	public final static String IN0 = "in0";

	public final static String ROOT = "root";

	// -------------------request&response body fields-----------------

	public final static class Head {
		public final static String HEAD = "head";
		public final static String SERVICE = "service";
		public final static String HANDLER = "handler";
		public final static String ACTION = "action";
		
		public final static String REPLYCODE = "replyCode";
		public final static String REPLYMsg = "replyMsg";
		public final static String HEAD_ARRAY[] = {"replyCode", "replyMsg" };
	}

	public final static class Body {
		public final static String BODY = "body";
		public final static String ITEMS = "items";
		public final static String ITEM = "item";
		public final static String FPDM = "fpdm";
		public final static String FPHM = "fphm";
		public final static String FKFMC = "fkfMc";
		public final static String SKFMC = "skfMc";
		public final static String SKFZJHM = "skfZjhm";
		public final static String KPRQ = "kprq";
		public final static String HJJE = "hjJe";
		public final static String SKM = "skm";
		public final static String FKFSJ = "fkfSj";
		public final static String ZJDJ = "zjDj";
		public final static String ZJJE = "zjJe";
		public final static String ZJQS = "zjQs";
		public final static String CYCS = "cyCs";
		public final static String FPZTMC = "fpztMc";
		public final static String CXJG = "cxjg";
		public final static String BODY_ARRAY[] = {"fpdm", "fpdh", "fkfMc", "skfMc",
			"skfZjhm", "kprq", "hjJe", "skm", "fkfSj", "zjDj", "zjJe",
			"zjQs", "cyCs", "fpztMc", "cxjg" };
	}

	public final static class Channel {
		public final static String CHANNEL = "channel";
		public final static String TYPE = "type";
		public final static String IP = "ip";
		public final static String PHONE = "phoneNo";
		public final static String CCID = "ccid";
		public final static String IMEI = "imei";
	}

	public final static class Item {
		public final static String ITEM = "item";
		public final static String FPDM = "fpdm";
		public final static String FPHM = "fphm";
		public final static String KPJE = "kpje";
		public final static String MMQ = "mmq";
	}
	// -------------------request&response body fields-----------------
	
	public final static class QueryType{
		public final static String QUERYTYPE = "queryType";
		public final static int normal = 0;
		public final static int qRcode = 1;
		public final static int phoneNumber = 2;
		public final static int enrolment = 3;
	}
	public final static class QueryKey{
		public final static String NORMAL = "normalresult";
		public final static String QRCODE = "qrresult";
		public final static String PHONENUMBER = "phonenumberresult";
		public final static String ENROLMENT = "enrolmentresult";
	}
	

	
}
