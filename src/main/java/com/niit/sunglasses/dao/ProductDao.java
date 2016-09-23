package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.Product;

public interface ProductDao {

	public List<Product> getAllProducts();
	
	public boolean productSaveOrUpdate(Product product);
	
	public Product getById(int id);
	
	public boolean removeProduct(int id);
	
	public List<Product> getNewArrivals();
}
