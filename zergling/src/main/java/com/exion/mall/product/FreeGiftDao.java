package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.exion.infra.code.CodeDto;

@Repository
public interface FreeGiftDao {
	public List<FreeGiftDto> fgList(FreeGiftVo vo);
	public FreeGiftDto fgOne(FreeGiftDto freeGiftDto);
	public int insert(FreeGiftDto freeGiftDto);
	public int update(FreeGiftDto freeGiftDto);
	public int uelete(FreeGiftDto freeGiftDto);
	public int delete(FreeGiftDto freeGiftDto);
}
