package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao{

	private static final Logger log = LoggerFactory.getLogger(CategoryDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean categorySaveOrUpdate(Category category) {
		try {
			log.debug("Stating of the method categorySaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(category);
			log.debug("Ending of the method categorySaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in categorySaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<Category> getAllCategorys() {
		try {
			log.debug("Staring of the method getAllCategorys");
			String hql = "Select category.cat_id as cat_id,category.cat_name as cat_name,category.status as status,brnd.brand_id as brand_id,brnd.brand_name as brand_name from Category category join category.brand brnd where category.status = true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			log.debug("Staring of the method getAllCategorys");
			@SuppressWarnings("unchecked")
			List<Category> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Categorys are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllCategorys method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Category getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from Category WHERE cat_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<Category> categoryList = query.list();
			if(categoryList != null && !categoryList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return categoryList.get(0);
			}else{
				log.debug("No Record Found in getById with id ="+id);
				return null;
			}
		} catch (HibernateException e) {
			log.error("Error Occures in getById Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean removeCategory(int id) {
		try {
			log.debug("Stating of the method removeCategory");
			Category category = new Category();
			category.setCat_id(id);
			log.debug("Ready To remove category with Id :- "+id);
			/*sessionFactory.getCurrentSession().delete(category);*/
			sessionFactory.getCurrentSession().createQuery("Update Category Set status = false Where cat_id = '"+id+"'").executeUpdate();
			log.debug("Category Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeCategory Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Category> getCategoryList() {
		try {
			log.debug("Staring of the method getAllCategorys");
			String hql = "from Category where status = true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllCategorys");
			@SuppressWarnings("unchecked")
			List<Category> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Categorys are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllCategorys method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Category> getCategoryListByBrandId(int id) {
		try {
			log.debug("Staring of the method getAllCategorys");
			String hql = "Select category.cat_id as cat_id,category.cat_name as cat_name,category.status as status,brnd.brand_id as brand_id,brnd.brand_name as brand_name from Category category join category.brand brnd where category.status = true AND brnd.brand_id ='"+id+"'"; 
			log.debug("Staring of the method getAllCategorys");
			Query query = sessionFactory.getCurrentSession().createQuery(hql).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			@SuppressWarnings("unchecked")
			List<Category> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Categorys are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllCategorys method ");
			e.printStackTrace();
			return null;
		}
	}

	

}
