package cn.meitong.soap;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

import cn.meitong.model.Channel;
import cn.meitong.model.RequestItem;

import cn.meitong.values.ResultValues;

import android.os.Bundle;
import android.text.style.BulletSpan;
import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-2
 * 
 */
public class SoapUtils {
	private final static String TAG = "soap";

	public SoapUtils() {

	}

	public String sendRequest(String username, String password, String value) {

		// 构建soap 报文体
		SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER10);
		// 设置报文头
		soapEnvelope.headerOut = new Element[1];
		soapEnvelope.headerOut[0] = SoapBuild.buildWsseHeader(SoapEnvelope.ENV,
				username, password);

		// 设置报文主体
		SoapObject request = new SoapObject(ResultValues.NAMESPACE,
				ResultValues.METHOD);
		request.addProperty(SoapBuild.buildProperty("arg0", value,
				ResultValues.NAMESPACE));
		request.addProperty("arg1", "");
		soapEnvelope.bodyOut = request;
		soapEnvelope.dotNet = true;

		// //发送报文
		HttpTransportSE soaprequest = new HttpTransportSE(ResultValues.WSDL);
		soaprequest.debug = true;

		String responsedump = null;
		try {
			soaprequest.call("", soapEnvelope);
			// 获取服务器返回内容
			responsedump = soaprequest.responseDump;
			String requestdump = soaprequest.requestDump;
			Log.d("soap", responsedump);
			Log.d("soap", requestdump);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responsedump;
	}

}
