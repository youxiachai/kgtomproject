package cn.meitong.tab.result;

public class Invoice {
	
	private String InvoiceId;
	private String InvoiceNum;
	public Invoice(String id, String num) {
		this.InvoiceId = id;
		this.InvoiceNum = num;
	}
	public String getInvoiceId() {
		return InvoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		InvoiceId = invoiceId;
	}
	public String getInvoiceNum() {
		return InvoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		InvoiceNum = invoiceNum;
	}
	
	

}
