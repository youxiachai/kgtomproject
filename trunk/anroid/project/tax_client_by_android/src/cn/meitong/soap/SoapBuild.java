package cn.meitong.soap;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

import cn.meitong.values.ResultValues;

import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-8
 * 
 */
public class SoapBuild {

	private static String wsse = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
	private static  String wsu = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd";
	private  static String passwordType = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest";

	/**
	 * 构建wsse 头报文
	 * 
	 * @param env
	 * @param name
	 * @param password
	 * @return
	 */
	public static Element buildWsseHeader(String env, String name, String password) {
		// 根节点
		Element header = new Element().createElement(wsse, "Security");
		header.setPrefix("wsse", wsse);
		// 设置必须读报头
		header.setAttribute(env, "mustUnderstand", "1");

		// 子节点
		Element usernameToken = new Element().createElement(wsse,
				"UsernameToken");
		usernameToken.setPrefix("wsu", wsu);
		usernameToken.setAttribute(wsu, "Id", "token");
		String token = WsseUtils.userNameToken(8);
		//生成密码username token
		usernameToken.addChild(Node.TEXT, token);

		// --------------节点定义
		String nonce = WsseUtils.randomString(16).toString();
		String createTime = WsseUtils.currCreateTime();
		// ------------------
		Element usernameE = new Element().createElement(wsse, "Username");
		usernameE.addChild(Node.TEXT, name);

		Element passwordE = new Element().createElement(wsse, "Password");
		passwordE.setAttribute(null, "Type", passwordType);
		String passwordencode = null;
		Log.d("soap", "nonce:" + nonce + "----create---" + createTime
				+ "----password-----" + password);
		try {
			// 测试加密算法是否正确
			// passwordencode = WsseUtils.Base64sha1("oRWsuWW5RyQg+KuaCtlFEA==",
			// "2011-12-06T06:37:17.908Z", password.getBytes("utf-8"));
			passwordencode = WsseUtils.Base64sha1(nonce, createTime,
					password.getBytes("utf-8"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		passwordE.addChild(Node.TEXT, passwordencode);

		Element nonceE = new Element().createElement(wsse, "Nonce");

		nonceE.addChild(Node.TEXT, nonce);

		Element createdE = new Element().createElement(wsu, "Created");
		// SimpleDateFormat sdf = new SimpleDateFormat();
		// Calendar c = sdf.getCalendar();
		// String time = "" + c.DATE;

		createdE.addChild(Node.TEXT, createTime);
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
	public static Element buildBody(String methodName,String value){
		//方法节点
		Element methodE = new Element().createElement(ResultValues.NAMESPACE, methodName);
		
		//子节点
		Element arg0E = new Element().createElement(ResultValues.NAMESPACE, "arg0");
		arg0E.setPrefix("soapenc", SoapEnvelope.ENC);
		arg0E.setPrefix("i", SoapEnvelope.XSI);
		arg0E.setAttribute(SoapEnvelope.XSI, "type", "soapenc:string");
		//把需要访问的值放进去
		arg0E.addChild(Node.TEXT, value);
		
		Element arg1E = new Element().createElement(ResultValues.NAMESPACE, "arg1");
		arg1E.setPrefix("i", SoapEnvelope.XSI);
		arg1E.setAttribute(SoapEnvelope.XSI, "nil", "true");
		methodE.addChild(Node.ELEMENT, arg0E);
		methodE.addChild(Node.ELEMENT, arg1E);
		
		return methodE;
	}
	
	/**
	 * 用于构建详细设置的属性节点
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public static PropertyInfo buildProperty(String name, String value,String namespace) {
		PropertyInfo pi = new PropertyInfo();
		pi.setNamespace(namespace);
		pi.setElementType(null);
		pi.setType(null);
		pi.setName(name);
		pi.setValue(value);
		return pi;
	}
	
	
}
