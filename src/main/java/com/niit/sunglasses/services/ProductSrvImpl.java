package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.ProductDao;
import com.niit.sunglasses.model.Product;

@Service
@Transactional
public class ProductSrvImpl implements ProductSrv {

	@Autowired
	private ProductDao productDao;
	@Override
	public boolean productSaveOrUpdate(Product product) {
		return productDao.productSaveOrUpdate(product);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

	@Override
	public Product getById(int id) {
		return productDao.getById(id);
	}

	@Override
	public boolean removeProduct(int id) {
		return productDao.removeProduct(id);
	}

	@Override
	public List<Product> getNewArrivals() {
		return productDao.getNewArrivals();
	}

}
