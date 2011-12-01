
/**
 * 
 */
package cn.meition.test;


import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
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
		button =(Button) findViewById(R.id.soap1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("soap", "onClick");
				webService();
				
			}
		});
		
	}
	public String ns;
	public String wsdl;
	//需要调用的方法名(获得本天气预报Web Services支持的洲、国内外省份和城市信息)
    public static final String getSupportProvince="getSupportProvince";
	private void webService() {
		//wsdl namespace
		ns = "http://WebXml.com.cn/";
		// wsdl url
		wsdl ="http://webservice.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl";
		
		//1
		SoapObject request = new SoapObject(ns, getSupportProvince);
		//2 request.add
		//3
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet=true;
		//4
		envelope.setOutputSoapObject(request); // 相当于 envelope.bodyOut=request;
		
		//5
		HttpTransportSE transport = new HttpTransportSE(wsdl);
		transport.debug = true;
		
		//6
		try {
			transport.call(ns+getSupportProvince, envelope);
			// 7
			SoapObject result = (SoapObject) envelope.getResponse();
			int count = result.getPropertyCount();
			
			for(int index=0; index<count; index++){
				Log.d("soap",result.getPropertyAsString(index));
			}
			Log.d("soap","count-->"+ count);
		} catch (IOException e) {
			Log.d("soap", "ioe!!!!!!!!");
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			Log.d("soap", "xmlpull!!!");
			e.printStackTrace();
		}
		
	}
}
