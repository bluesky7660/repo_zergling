package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	@Autowired
	ReviewDao reviewDao;
	
	public List<ReviewDto> selectUsrList(ReviewVo vo){
		return reviewDao.selectUsrList(vo);
	}
	public List<ReviewDto> listAll(ReviewVo vo){
		return reviewDao.listAll(vo);
	}
	public int insert(ReviewDto reviewDto) {
		return reviewDao.insert(reviewDto);
	}
	public int listCount(ReviewVo vo) {
		return reviewDao.listCount(vo);
	}
	public List<ReviewDto> listNum(ReviewVo vo) {
		return reviewDao.totalNum(vo);
	}
	public Double totalNum(ReviewVo vo) {
		List<ReviewDto> reviews = reviewDao.totalNum(vo);
		Double total = 0.0;
		int count = 0;
		for(ReviewDto review: reviews) {
			total += review.getRvScore();
			count++;
		}
		System.out.println("total: " +total );
		Double result = Math.round((total/count)* 10) / 10.0;
//		result 
		System.out.println("result: " +result );
		
		return result;
	}
}
