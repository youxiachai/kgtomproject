
package cn.meitong.soap;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-2
 * 
 */
public class SoapUtils {
	private final static String TAG = "soap";
	
	//是否启用debug模式
	private boolean Debug = true;
	//设置Soap 对象
	private SoapObject soap;
	//设置访问方法
	private String methods;
	
	//设置 wsdl 路径
	private String wsdl;
	
	//设置soap请求是否带header
	private boolean header = false;
	
	public SoapUtils(){
		
	}
	
	public SoapUtils(SoapObject soap,String wsdl,String methods){
		this.wsdl = wsdl;
		this.soap = soap;
		this.methods = methods;
	}
	
	public SoapUtils(SoapObject soap,String wsdl,String methods,boolean header){
		this.wsdl = wsdl;
		this.soap = soap;
		this.methods = methods;
		this.header = header;
	}
	public void startService(){
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		//设置与服务器兼容的编码
		soapEnvelope.dotNet = true;
		
		//set Body
		soapEnvelope.setOutputSoapObject(soap);
		
		//set header
		if(header){
			soapEnvelope.headerOut = new Element[1];
			soapEnvelope.headerOut[0] = buildWsseHeader(soapEnvelope.env,"tom_achai", "helloworld");
		}
		
		//访问服务器
		HttpTransportSE transport = new HttpTransportSE(wsdl);
		if(Debug){
			transport.debug = true;
		}
		
		try {
			transport.call(soap.getNamespace()+methods, soapEnvelope);
			if(Debug){
				String requestdump = transport.requestDump;
				String responsdump = transport.responseDump;
				Log.d(TAG, requestdump);
				Log.d(TAG, responsdump);
				String env = soapEnvelope.env;
				Log.d(TAG, env);
			}
			
			//返回解析结果
			SoapObject result = (SoapObject) soapEnvelope.getResponse();
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
	private String wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private String wsu = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private String passwordType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest";
	
	private Element buildWsseHeader(String env,String name,String password){
		//根节点
		Element header = new Element().createElement(wsse, "Security");
		header.setPrefix("wsse", wsse);
		//设置必须读报头
	//	header.setAttribute(env, "mustUnderstand", "1");
		
		//子节点
		Element usernameToken = new Element().createElement(wsse, "UsernameToken");
		usernameToken.setPrefix("wsu", wsu);
		usernameToken.setAttribute(wsu, "Id", "token");
		
		//--------------节点定义
		Element usernameE = new Element().createElement(wsse, "Username");
		usernameE.addChild(Node.TEXT, name);
		
		Element passwordE = new Element().createElement(wsse, "Password");
		passwordE.setAttribute(null, "Type", passwordType);
		passwordE.addChild(Node.TEXT, "testPasssssssss");
		
		Element nonceE = new Element().createElement(wsse, "Nonce");
		nonceE.addChild(Node.TEXT, "nnnnnnnnnnnnn");
		
		Element createdE = new Element().createElement(wsu, "Created");
		SimpleDateFormat sdf = new SimpleDateFormat();
		Calendar c = sdf.getCalendar();
		String time = "" +c.DATE;
		createdE.addChild(Node.TEXT, time);
		//-------------------------
		//把以上节点放到 usernameToken
		usernameToken.addChild(Node.ELEMENT, usernameE);
		usernameToken.addChild(Node.ELEMENT, passwordE);
		usernameToken.addChild(Node.ELEMENT, nonceE);
		usernameToken.addChild(Node.ELEMENT, createdE);
		
		
		//把子节点放到根节点
		header.addChild(Node.ELEMENT, usernameToken);
		return header;
	}
	
}
