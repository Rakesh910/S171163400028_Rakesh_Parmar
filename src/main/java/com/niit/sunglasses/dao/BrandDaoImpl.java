package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.Brand;

@Repository
public class BrandDaoImpl implements BrandDao{

	private static final Logger log = LoggerFactory.getLogger(BrandDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean brandSaveOrUpdate(Brand brand) {
		try {
			log.debug("Stating of the method brandSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(brand);
			log.debug("Ending of the method brandSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in brandSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<Brand> getAllBrands() {
		try {
			log.debug("Staring of the method getAllBrands");
		//	String hql = "select distinct b from Brand as b left join fetch b.cat ";
			/*String hql = "select b from Brand b left join fetch b.cat ";*/
			//String hql = "from Brand b left outer join b.cat where b.status=true ";
			
			String hql = "from Brand where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllBrands");
			@SuppressWarnings("unchecked")
			List<Brand> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Brands are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllBrands method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Brand getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from Brand WHERE brand_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<Brand> brandList = query.list();
			if(brandList != null && !brandList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return brandList.get(0);
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
	public boolean removeBrand(int id) {
		try {
			log.debug("Stating of the method removeBrand");
			Brand brand = new Brand();
			brand.setBrand_id(id);
			log.debug("Ready To remove brand with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(brand);
			sessionFactory.getCurrentSession().createQuery("Update Brand Set status = false Where brand_id = '"+id+"'").executeUpdate();
			log.debug("Brand Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeBrand Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
