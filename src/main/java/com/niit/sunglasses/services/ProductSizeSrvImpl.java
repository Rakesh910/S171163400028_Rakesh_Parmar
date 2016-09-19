package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.ProductSizeDao;
import com.niit.sunglasses.model.ProductSize;

@Service
@Transactional
public class ProductSizeSrvImpl implements ProductSizeSrv {

	@Autowired
	private ProductSizeDao productSizeDao;
	@Override
	public boolean productSizeSaveOrUpdate(ProductSize productSize) {
		return productSizeDao.productSizeSaveOrUpdate(productSize);
	}
	
	@Override
	public List<ProductSize> getAllProductSizes() {
		return productSizeDao.getAllProductSizes();
	}

	@Override
	public ProductSize getById(int id) {
		return productSizeDao.getById(id);
	}

	@Override
	public boolean removeProductSize(int id) {
		return productSizeDao.removeProductSize(id);
	}

}
