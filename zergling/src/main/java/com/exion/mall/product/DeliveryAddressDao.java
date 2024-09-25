package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressDao {
//	public List<DeliveryAddressDto> selectList();
	public List<DeliveryAddressDto> selectList(DeliveryAddressVo vo);
	public int insertAddr(DeliveryAddressDto deliveryAddressDto);
	public DeliveryAddressDto selectOne(DeliveryAddressDto deliveryAddressDto);
	public DeliveryAddressDto selectDefOne(DeliveryAddressDto deliveryAddressDto);
	public int update(DeliveryAddressDto deliveryAddressDto);
	public int updateDef(DeliveryAddressDto deliveryAddressDto);
}
