package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.Category;

public interface CategorySrv {
	public List<Category> getAllCategorys();
	
	public boolean categorySaveOrUpdate(Category category);
	
	public Category getById(int id);
	
	public boolean removeCategory(int id);
	
	public List<Category> getCategoryList();
	
	public List<Category> getCategoryListByBrandId(int id);
}
