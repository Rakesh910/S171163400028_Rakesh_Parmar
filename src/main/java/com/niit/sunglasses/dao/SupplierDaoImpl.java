package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.Supplier;

@Repository
public class SupplierDaoImpl implements SupplierDao{

	private static final Logger log = LoggerFactory.getLogger(SupplierDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean supplierSaveOrUpdate(Supplier supplier) {
		try {
			log.debug("Stating of the method supplierSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(supplier);
			log.debug("Ending of the method supplierSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in supplierSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<Supplier> getAllSuppliers() {
		try {
			log.debug("Staring of the method getAllSuppliers");
		//	String hql = "select distinct b from Supplier as b left join fetch b.cat ";
			/*String hql = "select b from Supplier b left join fetch b.cat ";*/
			//String hql = "from Supplier b left outer join b.cat where b.status=true ";
			String hql = "from Supplier where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllSuppliers");
			@SuppressWarnings("unchecked")
			List<Supplier> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No Suppliers are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllSuppliers method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Supplier getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from Supplier WHERE supplier_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<Supplier> supplierList = query.list();
			if(supplierList != null && !supplierList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return supplierList.get(0);
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
	public boolean removeSupplier(int id) {
		try {
			log.debug("Stating of the method removeSupplier");
			Supplier supplier = new Supplier();
			supplier.setSupplier_id(id);
			log.debug("Ready To remove supplier with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(supplier);
			sessionFactory.getCurrentSession().createQuery("Update Supplier Set status = false Where supplier_id = '"+id+"'").executeUpdate();
			log.debug("Supplier Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeSupplier Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
