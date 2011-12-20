
package cn.meitong.model;

import java.io.Serializable;

/**
 * @author Tom_achai
 * @date 2011-12-9
 * 因为用get/set开销比较大,这里就不使用了
 */
public class ResponseItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8588734870473430039L;
	public String replyCode;
	public String replyMsg;
	public String fpdm;
	public String fphm;
	public String fkfMc;
	public String fkfZjhm;
	public String skfMc;
	public String skfZjhm;
	public String kprq;
	public String hjJe;
	public String skm;
	public String fkfSj;
	public String zjDj;
	public String zjJe;
	public String zjQs;
	public String cyCs;
	public String fpztDm;
	public String fpztMc;
	public String cxjg;
	@Override
	public String toString() {
		return "ResponseItem [replyCode=" + replyCode + ", replyMsg="
				+ replyMsg + ", fpdm=" + fpdm + ", fphm=" + fphm + ", fkfMc="
				+ fkfMc + ", fkfZjhm=" + fkfZjhm + ", skfMc=" + skfMc
				+ ", skfZjhm=" + skfZjhm + ", kprq=" + kprq + ", hjJe=" + hjJe
				+ ", skm=" + skm + ", fkfSj=" + fkfSj + ", zjDj=" + zjDj
				+ ", zjJe=" + zjJe + ", zjQs=" + zjQs + ", cyCs=" + cyCs
				+ ", fpztDm=" + fpztDm + ", fpztMc=" + fpztMc + ", cxjg="
				+ cxjg + "]";
	}
	
}
