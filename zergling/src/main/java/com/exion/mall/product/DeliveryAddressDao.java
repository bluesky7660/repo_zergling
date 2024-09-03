package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressDao {
	public List<DeliveryAddressDto> selectList();
	public int insertAddr(DeliveryAddressDto deliveryAddressDto);
	public DeliveryAddressDto selectOne(DeliveryAddressDto deliveryAddressDto);
}
