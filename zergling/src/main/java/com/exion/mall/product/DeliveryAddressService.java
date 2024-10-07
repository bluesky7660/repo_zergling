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
	public DeliveryAddressDto buySelectOne(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.buySelectOne(deliveryAddressDto);
}
	public DeliveryAddressDto selectDefOne(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.selectDefOne(deliveryAddressDto);
	}
	public int update(DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		int a = deliveryAddressDao.update(deliveryAddressDto);
		List<DeliveryAddressDto> lists = deliveryAddressDao.selectList(vo);
//		System.out.println("리스트: "+deliveryAddressDao.selectList(vo).get(1).getMember_seq());
//		System.out.println("리스트: "+deliveryAddressDao.selectList(vo));
		String thisNum = deliveryAddressDao.selectOne(deliveryAddressDto).getDaSeq();
		int def = deliveryAddressDto.getDefaultNy();
//		System.out.println("유저번호2: "+deliveryAddressDao.selectOne(deliveryAddressDto).getMember_seq());
		if(def==1) {
			for(DeliveryAddressDto list:lists) {
				System.out.println("유저번호: "+list.getDaSeq());
				System.out.println("유저번호one: "+thisNum);
				int defNum = list.getDefaultNy();
				if(defNum == 1) {
					if(list.getDaSeq().equals(String.valueOf(thisNum))) {
						System.out.println("선택한 주소번호2: "+list.getDaSeq());
						deliveryAddressDto.setDefaultNy(1);
						System.out.println("디폴트2: "+deliveryAddressDto.getDefaultNy());
						deliveryAddressDto.setDaSeq(list.getDaSeq());
					}else {
						System.out.println("바뀌는 주소번호: "+list.getDaSeq());
						deliveryAddressDto.setDefaultNy(0);
						System.out.println("디폴트3: "+deliveryAddressDto.getDefaultNy());
						deliveryAddressDto.setDaSeq(list.getDaSeq());
					}
					deliveryAddressDao.updateDefUsr(deliveryAddressDto);
				}
//				if(list.getDaSeq().equals(String.valueOf(thisNum))) {
//					deliveryAddressDto.setDefaultNy(1);
//					System.out.println("디폴트1: "+deliveryAddressDto.getDefaultNy());
//					deliveryAddressDto.setDaSeq(list.getDaSeq());
//				}else {
//					deliveryAddressDto.setDefaultNy(0);
//					System.out.println("디폴트2: "+deliveryAddressDto.getDefaultNy());
//					deliveryAddressDto.setDaSeq(list.getDaSeq());
//				}
				deliveryAddressDao.updateDef(deliveryAddressDto);
			}
		}
		
		return a;
	}
	public int updateDef(DeliveryAddressDto deliveryAddressDto) {
		return deliveryAddressDao.updateDef(deliveryAddressDto);
	}
	public int listCount(DeliveryAddressVo vo) {
		return deliveryAddressDao.listCount(vo);
	}
	public int updateDefUsr(DeliveryAddressDto deliveryAddressDto,DeliveryAddressVo vo) {
		
		List<DeliveryAddressDto> lists = deliveryAddressDao.selectList(vo);
		int num = vo.getDefaultNy();
		int daseq = vo.getChSeq();
		int i = 0;
		String seq = vo.getSeq();
		String mseq = vo.getMember_seq();
		System.out.println("리스트: "+lists);
		System.out.println("선택한 디폴트: "+num);
		System.out.println("선택한 주소번호: "+daseq);
		System.out.println("유저번호: "+seq);
		System.out.println("유저번호2: "+mseq);
		
		for(DeliveryAddressDto list:lists) {
			System.out.println("주소번호: "+list.getDaSeq());
			System.out.println("주소디폴트: "+list.getDefaultNy());
			if(list.getDaSeq().equals(String.valueOf(daseq))) {
				System.out.println("선택한 주소번호1: "+list.getDaSeq());
				list.setDefaultNy(1);
				System.out.println("디폴트1: "+deliveryAddressDto.getDefaultNy());
			}
			int defNum = list.getDefaultNy();
			if(defNum == 1) {
				if(list.getDaSeq().equals(String.valueOf(daseq))) {
					System.out.println("선택한 주소번호2: "+list.getDaSeq());
					deliveryAddressDto.setDefaultNy(1);
					System.out.println("디폴트2: "+deliveryAddressDto.getDefaultNy());
					deliveryAddressDto.setDaSeq(list.getDaSeq());
				}else {
					System.out.println("바뀌는 주소번호: "+list.getDaSeq());
					deliveryAddressDto.setDefaultNy(0);
					System.out.println("디폴트3: "+deliveryAddressDto.getDefaultNy());
					deliveryAddressDto.setDaSeq(list.getDaSeq());
				}
				deliveryAddressDao.updateDefUsr(deliveryAddressDto);
			}
//			if(list.getDaSeq().equals(String.valueOf(daseq))) {
//				deliveryAddressDto.setDefaultNy(1);
//				System.out.println("디폴트1: "+deliveryAddressDto.getDefaultNy());
//				deliveryAddressDto.setDaSeq(list.getDaSeq());
//			}else {
//				deliveryAddressDto.setDefaultNy(0);
//				System.out.println("디폴트2: "+deliveryAddressDto.getDefaultNy());
//				deliveryAddressDto.setDaSeq(list.getDaSeq());
//			}
//			deliveryAddressDao.updateDef(deliveryAddressDto);
			i++;
		}
//		String thisNum = deliveryAddressDao.selectOne(deliveryAddressDto).getDaSeq();
//		System.out.println("유저번호one: "+thisNum);
//		int a = deliveryAddressDao.updateDefUsr(deliveryAddressDto);
		return i;
	}
}
