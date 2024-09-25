package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressService {
	@Autowired
	DeliveryAddressDao deliveryAddressDao;
	
	public List<DeliveryAddressDto> selectList(DeliveryAddressVo vo){
		return deliveryAddressDao.selectList(vo);
	}
	public int insertAddr(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.insertAddr(deliveryAddressDto);
	}	
	public DeliveryAddressDto selectOne(DeliveryAddressDto deliveryAddressDto) {
			return deliveryAddressDao.selectOne(deliveryAddressDto);
	}
	public DeliveryAddressDto selectDefOne(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.selectDefOne(deliveryAddressDto);
}
	public int update(DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		int a = deliveryAddressDao.update(deliveryAddressDto);
		System.out.println("리스트: "+deliveryAddressDao.selectList(vo));
		System.out.println("유저번호: "+deliveryAddressDao.selectOne(deliveryAddressDto).getSeq());
		System.out.println("유저번호2: "+deliveryAddressDao.selectOne(deliveryAddressDto).getMember_seq());
		return a;
	}
	public int updateDef(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.updateDef(deliveryAddressDto);
	}
}
