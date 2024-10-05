package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	@Autowired
	ReviewDao reviewDao;
	
	public List<ReviewDto> selectUsrList(ReviewDto reviewDto){
		return reviewDao.selectUsrList(reviewDto);
	}
	public int insert(ReviewDto reviewDto) {
		return reviewDao.insert(reviewDto);
	}
	public int listCount(ReviewVo vo) {
		return reviewDao.listCount(vo);
	}
}
