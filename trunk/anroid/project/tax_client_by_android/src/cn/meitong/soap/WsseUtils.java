package cn.meitong.soap;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.kobjects.base64.Base64;

import android.util.Log;

/**
 * @author Tom_achai
 * @date 2011-12-7
 * 
 */
public class WsseUtils {

	// 生成时间戳
	public static String currCreateTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 产生随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;
		Object initLock = new Object();
		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
							.toCharArray();
					// numbersAndLetters =
					// ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
			// randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);
	}

	// 生成基于WSSE 的密文密码
	public static String Base64sha1(String nonce, String created,
			byte[] password) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] b1 = nonce != null ? Base64.decode(nonce) : new byte[0];
		byte[] b2 = created != null ? created.getBytes("UTF-8") : new byte[0];
		byte[] b3 = password;
		byte[] b4 = new byte[b1.length + b2.length + b3.length];
		Log.d("soap", "密文长度:" + b4.length);
		md.update(b1, 0, b1.length);
		md.update(b2, 0, b2.length);
		md.update(b3, 0, b3.length);
		b4 = md.digest();

		String result = new String(Base64.encode(b4));

		Log.d("soap", "密文:" + result + "");
		return result;

	}

	public static String userNameToken(int length) {
		 Random randGen = null;
		 Object initLock = new Object(); 
		 char[] numbers = null;
		      if (randGen == null) {
		            synchronized (initLock) {
		                if (randGen == null) {
		                    randGen = new Random();
		                    numbers = ("0123456789").toCharArray();
		                    //numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		                }
		            }
		        }
		      char [] randBuffer = new char[length];
		   
		      for(int i =0; i < randBuffer.length; i++){
		    	  randBuffer[i] = numbers[randGen.nextInt(10)];
		      }
		      String userNameToken = "userNameToken-" + new String(randBuffer); 
			return userNameToken;
		}
}
