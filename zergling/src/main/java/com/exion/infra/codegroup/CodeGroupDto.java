package com.exion.infra.codegroup;

import java.util.Date;

public class CodeGroupDto {
//public class CodeGroupDto extends PagingResponseDto {
	private String seq;
	private String codeGroupName;
	private Integer groupOrder;
	private String  groupDesc;
	private Integer codeCount;
	private Integer useNy;
	private Integer delNy;
	private Date regDate;
	private Date modDate;

	//------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCodeGroupName() {
		return codeGroupName;
	}
	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
	}
	public Integer getGroupOrder() {
		return groupOrder;
	}
	public void setGroupOrder(Integer groupOrder) {
		this.groupOrder = groupOrder;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public Integer getCodeCount() {
		return codeCount;
	}
	public void setCodeCount(Integer codeCount) {
		this.codeCount = codeCount;
	}
	public Integer getUseNy() {
		return useNy;
	}
	public void setUseNy(Integer useNy) {
		this.useNy = useNy;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getModDate() {
		return modDate;
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	
}
