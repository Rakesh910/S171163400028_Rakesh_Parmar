package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.Product;

public interface ProductSrv {
	public List<Product> getAllProducts();
	
	public boolean productSaveOrUpdate(Product product);
	
	public Product getById(int id);
	
	public boolean removeProduct(int id);
	
	public List<Product> getNewArrivals();
}
