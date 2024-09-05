package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressService {
	@Autowired
	DeliveryAddressDao deliveryAddressDao;
	
	public List<DeliveryAddressDto> selectList(){
		return deliveryAddressDao.selectList();
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
	public int update(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.update(deliveryAddressDto);
	}
	public int updateDef(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.updateDef(deliveryAddressDto);
	}
}
