package qing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.meiton.action.ResultValues;

/**
 * @author zhang
 * 
 */
public class XmlReader extends DefaultHandler {

    // 用来保存root节点,有两个,分别为head和item
    private String rootName = "";

    private String tagName = "";
    private Map<String, String> m;
    private List<Map<String, String>> l;

    public List<Map<String, String>> getList() {
	return l;
    }

    @Override
    public void startDocument() throws SAXException {
	l = new ArrayList<Map<String, String>>();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
	    Attributes atts) throws SAXException {
	tagName = localName;
	// 判断当前的节点是否为head
	if (tagName.equals(ResultValues.Head.HEAD)) {
	    rootName = tagName;
	    // 初始化Map,所有值为null
	    m = new HashMap<String, String>();
	    for (String c : ResultValues.Head.HEAD_ARRAY) {
		m.put(c, null);
	    }
	} else if (tagName.equals(ResultValues.Body.ITEM)) {// 是否为item
	    rootName = tagName;
	    // 初始化Map,所有值为null
	    m = new HashMap<String, String>();
	    for (String c : ResultValues.Body.BODY_ARRAY) {
		m.put(c, null);
	    }
	}
    }

    @Override
    public void characters(char[] ch, int start, int length)
	    throws SAXException {
	// 获得节点内容
	String str = new String(ch, start, length);

	// 判断是否是head里的
	if (rootName.equals(ResultValues.Head.HEAD)) {
	    // 循环判断是哪一个字段
	    for (String c : ResultValues.Head.HEAD_ARRAY) {
		if (tagName.equals(c)) {
		    m.put(c, str);
		    break;
		}
	    }
	} else if (rootName.equals(ResultValues.Body.ITEM)) {// 判断是否是item里的
	    // 循环判断是哪一个字段
	    for (String c : ResultValues.Body.BODY_ARRAY) {
		if (tagName.equals(c)) {
		    m.put(c, str);
		    break;
		}
	    }
	}
    }

    @Override
    public void endElement(String uri, String localName, String qName)
	    throws SAXException {
	// 判断结束的节点时候为head或item,是则添加进list,并重置rootName
	if (localName.equals(ResultValues.Head.HEAD)
		|| localName.equals(ResultValues.Body.ITEM)) {
	    l.add(m);
	    rootName = "";
	}
	tagName = "";
    }

    /**
     * 读取xml
     * 
     * @param f
     *            xml的File对象
     * @return xml的内容
     */
    public String readerXML(File f) {
	String str = "";
	String r_str = "";
	FileInputStream fis = null;
	InputStreamReader isr = null;
	BufferedReader br = null;
	try {
	    fis = new FileInputStream(f);
	    isr = new InputStreamReader(fis);
	    br = new BufferedReader(isr);
	    while ((str = br.readLine()) != null) {
		r_str += str;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		fis.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return r_str;
    }
}
