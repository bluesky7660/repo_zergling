package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAddressDao {
//	public List<DeliveryAddressDto> selectList();
	public List<DeliveryAddressDto> selectList(DeliveryAddressVo vo);
	public List<DeliveryAddressDto> selectUsrList(DeliveryAddressDto deliveryAddressDto);
	public int insertAddr(DeliveryAddressDto deliveryAddressDto);
	public DeliveryAddressDto selectOne(DeliveryAddressDto deliveryAddressDto);
	public DeliveryAddressDto buySelectOne(DeliveryAddressDto deliveryAddressDto);
	public DeliveryAddressDto selectDefOne(DeliveryAddressDto deliveryAddressDto);
	public int listCount(DeliveryAddressVo vo);
	public int update(DeliveryAddressDto deliveryAddressDto);
	public int updateDef(DeliveryAddressDto deliveryAddressDto);
	public int updateDefUsr(DeliveryAddressDto deliveryAddressDto);
}
