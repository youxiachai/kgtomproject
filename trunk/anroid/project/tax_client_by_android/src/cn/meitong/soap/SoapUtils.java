package cn.meitong.soap;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParserException;

import cn.meiton.action.ResultValues;
import cn.meitong.model.Channel;
import cn.meitong.model.RequestItem;

import android.os.Bundle;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-2
 * 
 */
public class SoapUtils {
	private final static String TAG = "soap";

	// 是否启用debug模式
	private boolean Debug = true;
	// 设置Soap 对象
	private SoapObject soap;
	public void setDebug(boolean debug) {
		Debug = debug;
	}

	public void setSoap(SoapObject soap) {
		this.soap = soap;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public void setWsdl(String wsdl) {
		this.wsdl = wsdl;
	}

	public void setHeader(boolean header) {
		this.mustHeader = header;
	}

	public void setWsse(String wsse) {
		this.wsse = wsse;
	}

	public void setWsu(String wsu) {
		this.wsu = wsu;
	}

	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}

	// 设置访问方法
	private String methods;

	// 设置 wsdl 路径
	private String wsdl;

	// 设置soap请求是否必须读header
	private boolean mustHeader = false;

	public SoapUtils() {

	}

	public SoapUtils(SoapObject soap, String wsdl, String methods) {
		this.wsdl = wsdl;
		this.soap = soap;
		this.methods = methods;
	}

	public SoapUtils(SoapObject soap, String wsdl, String methods,
			boolean header) {
		this.wsdl = wsdl;
		this.soap = soap;
		this.methods = methods;
		this.mustHeader = header;
	}
	
	public SoapObject startServiceWsse(String username,String password){
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// 设置与服务器兼容的编码
		soapEnvelope.dotNet = false;

		// set Body
		soapEnvelope.setOutputSoapObject(soap);

	
			soapEnvelope.headerOut = new Element[1];
			soapEnvelope.headerOut[0] = buildWsseHeader(soapEnvelope.env,
					username, password);
		

		// 访问服务器
		HttpTransportSE transport = new HttpTransportSE(wsdl);
		if (Debug) {
			transport.debug = true;
		}

		try {
			transport.call(soap.getNamespace() + methods, soapEnvelope);
			if (Debug) {
				String requestdump = transport.requestDump;
				String responsdump = transport.responseDump;
				Log.d(TAG, requestdump);
				Log.d(TAG, responsdump);
			}

			// 返回解析结果
			SoapObject result = (SoapObject) soapEnvelope.getResponse();
			return (SoapObject) soapEnvelope.getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 还在测试阶段,build 头文体需要更改...
	 * 
	 * @return
	 */
	public SoapObject startService() {
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		// 设置与服务器兼容的编码
		soapEnvelope.dotNet = false;

		// set Body

		soapEnvelope.setOutputSoapObject(soap);


		// 访问服务器
		HttpTransportSE transport = new HttpTransportSE(wsdl);
		if (Debug) {
			transport.debug = true;
		}

		try {
			transport.call(soap.getNamespace() + methods, soapEnvelope);
			if (Debug) {
				String requestdump = transport.requestDump;
				String responsdump = transport.responseDump;
				Log.d(TAG, requestdump);
				Log.d(TAG, responsdump);
				String env = soapEnvelope.env;
				Log.d(TAG, env);
			}


			return (SoapObject) soapEnvelope.getResponse();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private String wsu = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private String passwordType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest";

	private Element buildWsseHeader(String env, String name, String password) {
		// 根节点
		Element header = new Element().createElement(wsse, "Security");
		header.setPrefix("wsse", wsse);
		// 设置必须读报头
		if(mustHeader){
			header.setAttribute(env, "mustUnderstand", "1");
		}

		// 子节点
		Element usernameToken = new Element().createElement(wsse,
				"UsernameToken");
		usernameToken.setPrefix("wsu", wsu);
		usernameToken.setAttribute(wsu, "Id", "token");

		// --------------节点定义
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
		String time = "" + c.DATE;
		createdE.addChild(Node.TEXT, time);
		// -------------------------
		// 把以上节点放到 usernameToken
		usernameToken.addChild(Node.ELEMENT, usernameE);
		usernameToken.addChild(Node.ELEMENT, passwordE);
		usernameToken.addChild(Node.ELEMENT, nonceE);
		usernameToken.addChild(Node.ELEMENT, createdE);

		// 把子节点放到根节点
		header.addChild(Node.ELEMENT, usernameToken);
		return header;
	}

	/**
	 * 用于构建soap 无命名空间节点
	 * 
	 * @param name
	 * @return
	 */
	public SoapObject buildSoapObject(String name) {
		SoapObject so = new SoapObject(null, name);
		return so;
	}

	/**
	 * 用于构建详细设置的属性节点
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public PropertyInfo buildProperty(String name, String value) {
		PropertyInfo pi = new PropertyInfo();
		pi.setNamespace(null);
		pi.setElementType(null);
		pi.setType(null);
		pi.setName(name);
		pi.setValue(value);
		return pi;
	}
	
	/**
	 * 用于构建主体节点
	 * @param ch
	 * @param itemlist
	 * @return
	 */
	public SoapObject buildRoot(Channel ch,List<RequestItem> itemlist) {

		// 子节点
		SoapObject in0 = new SoapObject(ResultValues.NAMESPACE,
				ResultValues.IN0);

		// ----------------构造值xml
		SoapObject root = buildSoapObject(ResultValues.ROOT);
		// -------
		SoapObject head = buildSoapObject(ResultValues.HEAD);
		SoapObject service = buildSoapObject(ResultValues.SERVICE);
		service.addProperty(buildProperty(ResultValues.HANDLER,
				"fpcyServiceHandler"));
		service.addProperty(buildProperty(ResultValues.ACTION, "fpcy"));
		// -----
		SoapObject channel = buildSoapObject(ResultValues.CHANNEL);
		channel.addProperty(buildProperty(ResultValues.TYPE, ch.type));
		channel.addProperty(buildProperty(ResultValues.IP, ch.ip));
		channel.addProperty(buildProperty(ResultValues.PHONE, ch.phoneNO));
		channel.addProperty(buildProperty(ResultValues.CCID, ch.ccid));
		channel.addProperty(buildProperty(ResultValues.IMEI, ch.imei));

		service.addSoapObject(channel);
		// ------------

		head.addSoapObject(service);
		// ---构造body
		SoapObject body = buildSoapObject(ResultValues.BODY);
		SoapObject items = buildSoapObject(ResultValues.ITEMS);
		
		for (RequestItem requestItem : itemlist) {
			SoapObject item = buildSoapObject(ResultValues.ITEM);
			item.addProperty(buildProperty(ResultValues.FPDM, requestItem.fpdm));
			item.addProperty(buildProperty(ResultValues.FPHM, requestItem.fphm));
			item.addProperty(buildProperty(ResultValues.KPJE, requestItem.kpje));
			items.addSoapObject(item);
		}
		
		body.addSoapObject(items);
		// -------
		root.addSoapObject(head);
		root.addSoapObject(body);
		// -------------------

		in0.addSoapObject(root);
		return in0;
	}
	

}
