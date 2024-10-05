package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDao {
	public List<ReviewDto> selectUsrList(ReviewDto reviewDto);
	public int insert(ReviewDto reviewDto);
	public int listCount(ReviewVo vo);
}
