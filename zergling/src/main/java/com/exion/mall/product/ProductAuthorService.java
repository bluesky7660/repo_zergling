package com.exion.mall.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAuthorService {
	@Autowired
	ProductAuthorDao productAuthorDao;
	
	public List<ProductAuthorDto> productAuthorSelected(ProductAuthorDto productAuthorDto){
		return productAuthorDao.productAuthorSelected(productAuthorDto);
	}
}
