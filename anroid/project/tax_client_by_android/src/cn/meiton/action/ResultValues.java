package cn.meiton.action;

/**
 * @author Tom_achai
 * @date 2011-11-24
 * 
 */
public final class ResultValues {
	public final static String RESULT = "result";
	public final static String NAMESPACE = "http://WebXml.com.cn/";
	public final static String WSDL = "http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
	// public final static String METHODS [] = {"getSupportProvince"};
	public final static String METHOD = "getSupportProvince";
	public final static int SUCCESS = 1;
	public final static int FAIL = 0;

	// 用于构造soap 主体的字段
	public final static String IN0 = "in0";

	public final static String ROOT = "root";

	// -------------------request&response body fields-----------------

	public final class Head {
		public final static String HEAD = "head";
		public final static String SERVICE = "service";
		public final static String HANDLER = "handler";
		public final static String ACTION = "action";
	}

	public final class Body {
		public final static String BODY = "body";
		public final static String ITEMS = "items";
	}

	public final class Channel {
		public final static String CHANNEL = "channel";
		public final static String TYPE = "type";
		public final static String IP = "ip";
		public final static String PHONE = "phoneNo";
		public final static String CCID = "ccid";
		public final static String IMEI = "imei";
	}

	public final class Item {
		public final static String ITEM = "item";
		public final static String FPDM = "fpdm";
		public final static String FPHM = "fphm";
		public final static String KPJE = "kpje";
	}
	// -------------------request&response body fields-----------------

}
