package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.Brand;

public interface BrandDao {

	public List<Brand> getAllBrands();
	
	public boolean brandSaveOrUpdate(Brand brand);
	
	public Brand getById(int id);
	
	public boolean removeBrand(int id);
}
