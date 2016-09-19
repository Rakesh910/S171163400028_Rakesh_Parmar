package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.ProductSize;

public interface ProductSizeDao {

	public List<ProductSize> getAllProductSizes();
	
	public boolean productSizeSaveOrUpdate(ProductSize productSize);
	
	public ProductSize getById(int id);
	
	public boolean removeProductSize(int id);
}
