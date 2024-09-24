package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
	public int insertProd(ProductDto productDto);
//	public List<ProductDto> prodList();
	public List<ProductDto> usrProdList(ProductVo vo);
	public List<ProductDto> xdmProdList(ProductVo vo);	
	public ProductDto prodOne(ProductDto productDto);
	public int update(ProductDto productDto);
	public int uelete(ProductDto productDto);
	public int delete(ProductDto productDto);
}
