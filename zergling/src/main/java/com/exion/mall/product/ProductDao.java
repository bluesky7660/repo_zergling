package com.exion.mall.product;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao {
	public int insertProd(ProductDto productDto);
	public List<ProductDto> prodList();
	public ProductDto prodOne(ProductDto productDto);
}
