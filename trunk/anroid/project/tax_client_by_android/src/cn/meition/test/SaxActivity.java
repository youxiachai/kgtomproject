package cn.meition.test;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import qing.SDCardService;
import qing.XmlReader;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import cn.meiton.action.ResultValues;
import cn.meitong.R;

/**
 * @author zhang
 */
public class SaxActivity extends Activity {

    private Button b;
    private Button b1;
    private List<Map<String, String>> l = new ArrayList<Map<String, String>>();
    private XmlReader r;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.sax);
	b = (Button) findViewById(R.id.b);
	// 解析按钮
	b.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {

		// 获得sdcard里xml的File对象
		SDCardService s = new SDCardService();
		File f = s.createFile("test/", "test_2.xml");

		r = new XmlReader();
		// 获得xml里的内容
		String str = r.readerXML(f);

		SAXParserFactory spf = SAXParserFactory.newInstance();
		XMLReader reader;
		try {
		    reader = spf.newSAXParser().getXMLReader();
		    reader.setContentHandler(r);
		    reader.parse(new InputSource(new StringReader(str)));
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});

	b1 = (Button) findViewById(R.id.b1);

	// 打印
	b1.setOnClickListener(new OnClickListener() {

	    @Override
	    public void onClick(View v) {
		System.out.println("onClick");
		// 获得xmlReader里解析完之后的list
		l = r.getList();
		// 遍历
		for (int i = 0; i < l.size(); i++) {
		    Map<String, String> m = new HashMap<String, String>();
		    m = l.get(i);
		    // 默认返回的list的第一个Map是保存Head
		    if (i == 0) {
			for (String c : ResultValues.Head.HEAD_ARRAY) {
			    System.out.println(c + "-----------" + m.get(c));
			}
		    } else {
			// 其他的Map都是Body
			for (String c : ResultValues.Body.BODY_ARRAY) {
			    System.out.println(c + "-----------" + m.get(c));
			}
		    }
		}
	    }
	});
    }

}