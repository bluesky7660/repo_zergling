package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FreeGiftService {
	
	@Autowired
	FreeGiftDao freeGiftDao;
	
	public List<FreeGiftDto> fgList(){
		return freeGiftDao.fgList();
	}
	public FreeGiftDto fgOne(FreeGiftDto freeGiftDto) {
		return freeGiftDao.fgOne(freeGiftDto);
	}
	public int insert(FreeGiftDto freeGiftDto) {
		return freeGiftDao.insert(freeGiftDto);
	}
}
