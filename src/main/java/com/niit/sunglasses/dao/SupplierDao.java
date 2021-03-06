package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.Supplier;

public interface SupplierDao {

	public List<Supplier> getAllSuppliers();
	
	public boolean supplierSaveOrUpdate(Supplier supplier);
	
	public Supplier getById(int id);
	
	public boolean removeSupplier(int id);
}
