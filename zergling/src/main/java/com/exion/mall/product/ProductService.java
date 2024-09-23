package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductAuthorDao productAuthorDao;
	
	public int insertProd(ProductDto productDto,ProductAuthorDto productAuthorDto) {
		int a = productDao.insertProd(productDto);
		productAuthorDto.setProduct_seq(productDto.getSeq());
		productAuthorDto.setAuthor_seq(productDto.getAuthor_seq());
		productAuthorDao.insert(productAuthorDto);
		return a;
	}
//	public List<ProductDto> prodList(){
//		return productDao.prodList();
//	}
	public List<ProductDto> usrProdList(ProductVo vo){
		return productDao.usrProdList(vo);
	}
	public List<ProductDto> xdmProdList(ProductVo vo){
		return productDao.xdmProdList(vo);
	}
	public ProductDto prodOne(ProductDto productDto){
		return productDao.prodOne(productDto);
	}
	public int update(ProductDto productDto) {
		return productDao.update(productDto);
	}
}
