package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao {
	public List<ReviewDto> selectUsrList(ReviewVo vo);
	public List<ReviewDto> listAll(ReviewVo vo);
	public List<ReviewDto> totalNum(ReviewVo vo);
	public int insert(ReviewDto reviewDto);
	public int uelete(ReviewDto reviewDto);
	public int listCount(ReviewVo vo);
	
}
