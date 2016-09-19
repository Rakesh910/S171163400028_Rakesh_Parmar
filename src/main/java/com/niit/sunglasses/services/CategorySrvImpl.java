package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.CategoryDao;
import com.niit.sunglasses.model.Category;

@Service
@Transactional
public class CategorySrvImpl implements CategorySrv {

	@Autowired
	private CategoryDao categoryDao;
	@Override
	public boolean categorySaveOrUpdate(Category category) {
		return categoryDao.categorySaveOrUpdate(category);
	}
	
	@Override
	public List<Category> getAllCategorys() {
		return categoryDao.getAllCategorys();
	}

	@Override
	public Category getById(int id) {
		return categoryDao.getById(id);
	}

	@Override
	public boolean removeCategory(int id) {
		return categoryDao.removeCategory(id);
	}

	@Override
	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	@Override
	public List<Category> getCategoryListByBrandId(int id) {
		return categoryDao.getCategoryListByBrandId(id);
	}

}
