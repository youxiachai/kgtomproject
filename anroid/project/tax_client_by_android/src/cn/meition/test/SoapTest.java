/**
 * 
 */
package cn.meition.test;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.meitong.R;

/**
 * @author Tom_achai
 * @date 2011-12-1
 * 
 */
public class SoapTest extends Activity {

	public Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		button = (Button) findViewById(R.id.soap1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("soap", "onClick");
				// webService();
			//	webFpzx();
				 initWebService(getWeaterbyCityName, true, true);
				//webWords();
			}
		});

	}

	private void webFpzx() {

		String ns = "http://services.fpzx.tax.foresee.com";
		String wsdl = "http://www.gdltax.gov.cn/wssw-webservice/services/FpzxService?wsdl";

		String getResponse = "invoke";
		// String get = "invokeResponse";

		SoapObject request = new SoapObject(ns, getResponse);
//		request.setProperty(index, value)
		//request.addAttribute(attributeInfo)
		request.addProperty("in0", "ok");
		request.addProperty("in1", "ssss");

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER12);
		envelope.dotNet = true;

		envelope.setOutputSoapObject(request);
		

		HttpTransportSE transport = new HttpTransportSE(wsdl);
		transport.debug = true;

		try {
			transport.call(ns + getResponse, envelope);

			Log.d("soap", envelope.toString());
			SoapObject result = (SoapObject) envelope.getResponse();
			int count = result.getPropertyCount();
			Log.d("soap", "count-->" + count);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("soap", "ioe!!!!!!!!");
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			Log.d("soap", "xmlpull!!!");
			e.printStackTrace();
		}

	}

	// 需要调用的方法名(获得本天气预报Web Services支持的洲、国内外省份和城市信息)
	public static final String getSupportProvince = "getSupportDataSet";
	public static final String getSupportCity = "getSupportCity";
	public static final String getWeaterbyCityName = "getWeatherbyCityName";
	
	private Element buildAuthHeader(String namespace) {
//	    Element h = new Element().createElement(NAMESPACE, "AuthHeader");
//	    h.setPrefix("wsse", NAMESPACE);
//	    Element username = new Element().createElement(NAMESPACE, "user");
//	    String USERNAME = "test";
//	    String PASSWORD ="123";
//	    username.addChild(Node.TEXT, USERNAME);
//	    h.addChild(Node.ELEMENT, username);
//	    Element pass = new Element().createElement(NAMESPACE, "pass");
//	    pass.addChild(Node.TEXT, PASSWORD);
//	    h.addChild(Node.ELEMENT, pass);
	    
	    //根节点
		Element security = new Element().createElement(namespace, "Security");
	    security.setPrefix("wsse", namespace);
	    security.setAttribute("http://schemas.xmlsoap.org/soap/envelope/", "mustUnderstand", "1");
	    
	    //子节点
	    Element usernameToken = new Element().createElement(namespace, "UsernameToken");
	    usernameToken.setPrefix("wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
	    usernameToken.setAttribute("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Id", "UsernameToken-26644003");
	    
	    security.addChild(Node.ELEMENT, usernameToken);
	    
	    //子子节点
	    Element username = new Element().createElement(namespace, "Username");
	    username.addChild(Node.TEXT, "gdds_fpzx_chinamobile");
	    
	    Element password = new Element().createElement(namespace, "Password");
	    password.setAttribute(null, "Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
	    
	    password.addChild(Node.TEXT, "aaaaaaaaaaaa");
	    
	    Element nonce = new Element().createElement(namespace, "Nonce");
	    nonce.addChild(Node.TEXT, "asdfgqwer0");
	    
	    Element created = new Element().createElement("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd", "Created");
	    
	    usernameToken.addChild(Node.ELEMENT,username);
	    
	    usernameToken.addChild(Node.ELEMENT, password);
	    
	    usernameToken.addChild(Node.ELEMENT, nonce);
	    
	    usernameToken.addChild(Node.ELEMENT, created);
	    
	  
	    
	    
	    return security;
	}
	
	private void initWebService(String methodName, boolean haveproperty,
			boolean debug) {
		String ns = "http://WebXml.com.cn/";
		String wsdl = "http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";

		SoapObject request = new SoapObject(ns, methodName);

		// 加入一个参数列表
		if (haveproperty) {
			request.addProperty("theCityName", "珠海");
		}

		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// 设置与服务器兼容的编码
		envelope.dotNet = true;
		envelope.headerOut = new Element[1];
		String wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
		envelope.headerOut[0] = buildAuthHeader(wsse);
		// set Boday
		envelope.setOutputSoapObject(request);
		


		// 访问服务器
		HttpTransportSE httpTransport = new HttpTransportSE(wsdl);

		if (debug) {
			httpTransport.debug = true;
		}

		try {
			httpTransport.call(ns + methodName, envelope);

			if (debug) {
				String requestDebug = httpTransport.requestDump;
				String responseDebug = httpTransport.responseDump;

				Log.d("soap", "request--->" + requestDebug);
				Log.d("soap", "response-->" + responseDebug);
			}
			// 解析返回结果
			SoapObject result = (SoapObject) envelope.getResponse();
			int count = result.getPropertyCount();

			for (int index = 0; index < count; index++) {
				Log.d("soap", result.getPropertyAsString(index));
			}
			Log.d("soap", "count-->" + count);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void webService() {
		// wsdl namespace
		// ns = "http://services.fpzx.tax.foresee.com";
		String ns = "http://WebXml.com.cn/";
		// wsdl url
		String wsdl = "http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
		// wsdl =
		// "http://61.142.246.130/wssw-webservice/services/FpzxService?wsdl";

		// 1
		SoapObject request = new SoapObject(ns, getSupportProvince);
		request.addProperty("byProvinceName", "广东");
		// 2 request.add
		// 3
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		// 4
		envelope.setOutputSoapObject(request); // 相当于 envelope.bodyOut=request;

		// 5
		HttpTransportSE transport = new HttpTransportSE(wsdl);
		transport.debug = true;

		// 6
		try {
			transport.call(ns + getSupportProvince, envelope);
			String requestdump = transport.requestDump;
			Log.d("soap", requestdump);
			// 7
			SoapObject result = (SoapObject) envelope.getResponse();

			int count = result.getPropertyCount();

			for (int index = 0; index < count; index++) {
				Log.d("soap", result.getPropertyAsString(index));
			}
			Log.d("soap", "count-->" + count);
		} catch (IOException e) {
			Log.d("soap", "ioe!!!!!!!!");
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			Log.d("soap", "xmlpull!!!");
			e.printStackTrace();
		}

	}

	public void webWords(){
		String ns = "http://WebXml.com.cn/";
		String wsdl = "http://fy.webxml.com.cn/webservices/EnglishChinese.asmx?wsdl";
		String SuggestWord ="SuggestWord";
		SoapObject request = new SoapObject(ns, SuggestWord);
		
		PropertyInfo pi = new PropertyInfo();
		pi.setName("wordKey");
		pi.setValue("hello");
		PropertyInfo pi2 = new PropertyInfo();
		pi2.setName("in2");
		pi2.setValue("request");
		pi2.setNamespace(ns);
		
		
		request.addProperty(pi);
		request.addProperty(pi2);
		
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		
		
		HttpTransportSE httpTransport = new HttpTransportSE(wsdl);
		httpTransport.debug = true;
		
		try {
			httpTransport.call(ns+SuggestWord, envelope);
			String requestdump = httpTransport.requestDump;
			String responsedump = httpTransport.responseDump;
			Log.d("soap", requestdump);
			
			SoapObject result = (SoapObject) envelope.getResponse();
			
			int count = result.getPropertyCount();
			Log.d("soap", "count" + count);
			
			for (int index = 0; index < count; index++) {
				Log.d("soap", result.getPropertyAsString(index));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
