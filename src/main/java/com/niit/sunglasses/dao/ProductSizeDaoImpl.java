package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.ProductSize;

@Repository
public class ProductSizeDaoImpl implements ProductSizeDao{

	private static final Logger log = LoggerFactory.getLogger(ProductSizeDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean productSizeSaveOrUpdate(ProductSize productSize) {
		try {
			log.debug("Stating of the method productSizeSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(productSize);
			log.debug("Ending of the method productSizeSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in productSizeSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<ProductSize> getAllProductSizes() {
		try {
			log.debug("Staring of the method getAllProductSizes");
		//	String hql = "select distinct b from ProductSize as b left join fetch b.cat ";
			/*String hql = "select b from ProductSize b left join fetch b.cat ";*/
			//String hql = "from ProductSize b left outer join b.cat where b.status=true ";
			String hql = "from ProductSize where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllProductSizes");
			@SuppressWarnings("unchecked")
			List<ProductSize> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No ProductSizes are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllProductSizes method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProductSize getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from ProductSize WHERE size_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<ProductSize> productSizeList = query.list();
			if(productSizeList != null && !productSizeList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return productSizeList.get(0);
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
	public boolean removeProductSize(int id) {
		try {
			log.debug("Stating of the method removeProductSize");
			ProductSize productSize = new ProductSize();
			productSize.setSize_id(id);
			log.debug("Ready To remove productSize with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(productSize);
			sessionFactory.getCurrentSession().createQuery("Update ProductSize Set status = false Where size_id = '"+id+"'").executeUpdate();
			log.debug("ProductSize Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeProductSize Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
