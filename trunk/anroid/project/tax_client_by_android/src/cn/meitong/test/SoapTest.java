
package cn.meitong.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import org.apache.http.HttpConnection;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import cn.meitong.R;
import cn.meitong.handleparse.ResultHandle;

import cn.meitong.soap.WsseUtils;
import cn.meitong.values.ResultValues;

/**
 * @author Tom_achai
 * @date 2011-12-6
 * 
 */
public class SoapTest extends Activity {
	private ResultHandle rh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		Log.d("soap", "sssss");
		
		StringBuffer sb = new StringBuffer();
		String line = null;
		BufferedReader buffer = null;
		String values = null;
		String qrCode = getIntent().getStringExtra(ResultValues.QueryKey.QRCODE);
		try {
			InputStream is = getAssets().open("qrcode_type.xml");
			buffer = new BufferedReader(new InputStreamReader(is));
			while((line = buffer.readLine()) != null){
				Log.d("soap", line);
				if(line.trim().equals("<mmq>")){
					Log.d("soap","----->"+"qrcode");
					line += qrCode;
				}
				
				sb.append(line.trim());
			}
			values = sb.toString();
			Log.d("soap", "soap-->" + values);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	ResultHandle rh = new ResultHandle(this,values,true);


	}
		


	}
		
		
	
	
	




