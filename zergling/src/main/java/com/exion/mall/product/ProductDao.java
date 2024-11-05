package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
	public int insertProd(ProductDto dto);
//	public List<ProductDto> prodList();

	public List<ProductDto> usrProdList(ProductVo vo);
	public List<ProductDto> newProdList(ProductVo vo);
	public List<ProductDto> bestProdList(ProductVo vo);
	public List<ProductDto> bestCategoryProdList(ProductVo vo);
	public List<ProductDto> mdPickProdList(ProductVo vo);
	public List<ProductDto> xdmProdList(ProductVo vo);	
	public ProductDto prodOne(ProductDto dto);
	public ProductDto prodUsrOne(ProductVo vo);
	public int listCount(ProductVo vo);
	public int update(ProductDto dto);
	public int reviewNum(ProductDto dto);
	public int uelete(ProductDto dto);
	public int delete(ProductDto dto);
	
	//이미지
	public List<ProductDto> imgList(ProductDto dto);
	public List<ProductDto> imgUsrList(ProductDto dto);
	public ProductDto imgUsrOne(ProductDto dto);
	public int insertUploaded(ProductDto dto);
}
