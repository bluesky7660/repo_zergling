package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface FreeGiftDao {
	public List<FreeGiftDto> fgList();
	public FreeGiftDto fgOne(FreeGiftDto freeGiftDto);
	public int insert(FreeGiftDto freeGiftDto);
}
