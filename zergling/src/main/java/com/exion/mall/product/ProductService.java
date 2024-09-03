package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	
	public int insertProd(ProductDto productDto) {
		return productDao.insertProd(productDto);
	}
	public List<ProductDto> prodList(){
		return productDao.prodList();
	}
	public ProductDto prodOne(ProductDto productDto){
		return productDao.prodOne(productDto);
	}
}
