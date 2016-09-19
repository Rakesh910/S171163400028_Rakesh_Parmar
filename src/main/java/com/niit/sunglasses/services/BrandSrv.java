package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.Brand;

public interface BrandSrv {
	public List<Brand> getAllBrands();
	
	public boolean brandSaveOrUpdate(Brand brand);
	
	public Brand getById(int id);
	
	public boolean removeBrand(int id);
}
