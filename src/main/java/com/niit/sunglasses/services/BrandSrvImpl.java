package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.BrandDao;
import com.niit.sunglasses.model.Brand;

@Service
@Transactional
public class BrandSrvImpl implements BrandSrv {

	@Autowired
	private BrandDao brandDao;
	@Override
	public boolean brandSaveOrUpdate(Brand brand) {
		return brandDao.brandSaveOrUpdate(brand);
	}
	
	@Override
	public List<Brand> getAllBrands() {
		return brandDao.getAllBrands();
	}

	@Override
	public Brand getById(int id) {
		return brandDao.getById(id);
	}

	@Override
	public boolean removeBrand(int id) {
		return brandDao.removeBrand(id);
	}

}
