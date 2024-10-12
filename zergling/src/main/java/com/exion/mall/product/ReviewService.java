package com.exion.mall.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
	@Autowired
	ReviewDao reviewDao;
	@Autowired
	ProductService productService;
	
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
	public Map<String, Map<String, Object>> listScore(ReviewVo vo,ProductVo productVo) {
		List<ProductDto> prods = productService.usrProdList(productVo);
		Map<String, Map<String, Object>> returnMap = new HashMap<>();
		for(ProductDto prod : prods) {
			System.out.println("상품seq: "+prod.getSeq());
			System.out.println("타입: "+prod.getTitle()	);
			vo.setSeq(prod.getSeq());
			List<ReviewDto> reviews = reviewDao.totalNum(vo);
			Double total = 0.0;
			int count = 0;
			for(ReviewDto review: reviews) {
				total += review.getRvScore();
				count++;
			}
			System.out.println("total: " +total );
			Double result = count > 0 ? Math.round((total / count) * 10) / 10.0 : 0.0;
			System.out.println(prod.getSeq()+"번 result: " +result +", " +count +"개 리뷰");
			// 각 상품에 대한 정보를 맵에 저장
		    Map<String, Object> reviewStats = new HashMap<>();
		    reviewStats.put("count", count);
		    reviewStats.put("result", result);
		    
		    // 상품 seq를 키로 하여 리뷰 통계 저장
		    returnMap.put(prod.getSeq(), reviewStats);
		}
		
		// 결과 출력
		for (Map.Entry<String, Map<String, Object>> entry : returnMap.entrySet()) {
			String productId = entry.getKey();
		    Map<String, Object> stats = entry.getValue();
		    System.out.println("상품 ID: " + productId + ", 리뷰 개수: " + stats.get("count") + ", 평균 점수: " + stats.get("result"));
		}
		
		return returnMap;
	}
}
