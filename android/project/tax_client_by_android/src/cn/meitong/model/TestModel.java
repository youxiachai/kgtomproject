
package cn.meitong.model;

import java.io.Serializable;

/**
 * @author Tom_achai
 * @date 2011-12-3
 * 
 */
public class TestModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String t1;
	private String t2;
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
}
