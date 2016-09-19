package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.SupplierDao;
import com.niit.sunglasses.model.Supplier;

@Service
@Transactional
public class SupplierSrvImpl implements SupplierSrv {

	@Autowired
	private SupplierDao supplierDao;
	@Override
	public boolean supplierSaveOrUpdate(Supplier supplier) {
		return supplierDao.supplierSaveOrUpdate(supplier);
	}
	
	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierDao.getAllSuppliers();
	}

	@Override
	public Supplier getById(int id) {
		return supplierDao.getById(id);
	}

	@Override
	public boolean removeSupplier(int id) {
		return supplierDao.removeSupplier(id);
	}

}
