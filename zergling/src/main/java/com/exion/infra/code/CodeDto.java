package com.exion.infra.code;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exion.infra.codegroup.PagingResponseDto;

public class CodeDto extends PagingResponseDto {
	
	private String seq;
	private String codeName;
	private Integer codeOrder;
	private String codeDesc;
	private String codeGroupName;
	private Integer useNy;
	private Integer delNy;
	private Date regDate;
	private Date modDate;
	private String codeGroup_seq;
	
//	for cache
	public static List<CodeDto> cachedCodeArrayList = new ArrayList<CodeDto>();
	
	//----------------------------------
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public Integer getCodeOrder() {
		return codeOrder;
	}
	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getCodeGroupName() {
		return codeGroupName;
	}
	public void setCodeGroupName(String codeGroupName) {
		this.codeGroupName = codeGroupName;
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
	public String getCodeGroup_seq() {
		return codeGroup_seq;
	}
	public void setCodeGroup_seq(String codeGroup_seq) {
		this.codeGroup_seq = codeGroup_seq;
	}
	public static List<CodeDto> getCachedCodeArrayList() {
		return cachedCodeArrayList;
	}
	public static void setCachedCodeArrayList(List<CodeDto> cachedCodeArrayList) {
		CodeDto.cachedCodeArrayList = cachedCodeArrayList;
	}
	
}
