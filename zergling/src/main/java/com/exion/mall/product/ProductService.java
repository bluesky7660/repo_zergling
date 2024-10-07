package com.exion.mall.product;

//import java.util.Arrays;
//import java.util.Iterator;
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
		

		List<String> authorLists = productAuthorDto.getListAuthor_seq();

		System.out.println("리스트: "+authorLists);
		int i=0;
		for(String author: authorLists) {
//			System.out.println("상품번호: "+productDto.getSeq());
//			System.out.println("작가번호: "+author);
			productAuthorDto.setProduct_seq(productDto.getSeq());
			productAuthorDto.setAuthor_seq(author);
//			if(i == 0) {
//				productAuthorDto.setaDefaultNy(1);
//			}else {
//				productAuthorDto.setaDefaultNy(0);
//			}
			productAuthorDao.insert(productAuthorDto);
			i++;
		}
		productAuthorDao.defaultUpdt(productAuthorDto);
		return a;
	}
//	public List<ProductDto> prodList(){
//		return productDao.prodList();
//	}
	public List<ProductDto> usrProdList(ProductVo vo){
		return productDao.usrProdList(vo);
	}
	public List<ProductDto> newProdList(ProductVo vo){
		return productDao.newProdList(vo);
	}
	public List<ProductDto> bestProdList(ProductVo vo){
		return productDao.bestProdList(vo);
	}
	public List<ProductDto> xdmProdList(ProductVo vo){
		return productDao.xdmProdList(vo);
	}
	public ProductDto prodOne(ProductDto productDto){
//		System.out.println("리스트1: "+productDto.getAuthor_seq());
		return productDao.prodOne(productDto);
	}
	public ProductDto prodUsrOne(ProductVo vo){
		return productDao.prodUsrOne(vo);
	}
	public int update(ProductDto productDto, ProductAuthorDto productAuthorDto) {
		int a = productDao.update(productDto);
//		List<String> authorLists = productAuthorDto.getListAuthor_seq();
//		System.out.println("리스트2: "+authorLists);
//		for(String author: authorLists) {
//			System.out.println("상품번호: "+productDto.getSeq());
//			System.out.println("작가번호: "+author);
//			productAuthorDto.setProduct_seq(productDto.getSeq());
//			productAuthorDto.setAuthor_seq(author);
//			productAuthorDao.update(productAuthorDto);
//		}
		return a;
	}
	public int listCount(ProductVo vo) {
		return productDao.listCount(vo);
	}
}
