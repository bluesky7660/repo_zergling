package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeGiftService {
	
	@Autowired
	FreeGiftDao freeGiftDao;
	
	public List<FreeGiftDto> fgList(FreeGiftVo vo){
		return freeGiftDao.fgList(vo);
	}
	public FreeGiftDto fgOne(FreeGiftDto freeGiftDto) {
		return freeGiftDao.fgOne(freeGiftDto);
	}
	public int insert(FreeGiftDto freeGiftDto) {
		return freeGiftDao.insert(freeGiftDto);
	}
	public int update(FreeGiftDto freeGiftDto) {
		return freeGiftDao.update(freeGiftDto);
	}
	public int uelete(FreeGiftDto freeGiftDto) {
		return freeGiftDao.uelete(freeGiftDto);
	}
	public int delete(FreeGiftDto freeGiftDto) {
		return freeGiftDao.delete(freeGiftDto);
	}
}
