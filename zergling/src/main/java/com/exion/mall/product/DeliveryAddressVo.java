package com.exion.mall.product;

import com.exion.infra.codegroup.BaseVo;

public class DeliveryAddressVo extends BaseVo{
	private String seq;
	private String daSeq;
	private String member_seq;
//---------------------------
	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getDaSeq() {
		return daSeq;
	}

	public void setDaSeq(String daSeq) {
		this.daSeq = daSeq;
	}

	public String getMember_seq() {
		return member_seq;
	}

	public void setMember_seq(String member_seq) {
		this.member_seq = member_seq;
	}
	
}
