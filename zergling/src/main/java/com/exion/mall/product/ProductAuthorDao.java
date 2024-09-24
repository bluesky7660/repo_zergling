package com.exion.mall.product;

import java.util.List;

public interface ProductAuthorDao {
	public int insert(ProductAuthorDto productAuthorDto);
	public int update(ProductAuthorDto productAuthorDto);
	public List<ProductAuthorDto> productAuthorSelected(ProductAuthorDto productAuthorDto);
}
