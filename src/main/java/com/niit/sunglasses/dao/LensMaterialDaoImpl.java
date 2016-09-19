package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.LensMaterial;

@Repository
public class LensMaterialDaoImpl implements LensMaterialDao{

	private static final Logger log = LoggerFactory.getLogger(LensMaterialDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean lensMaterialSaveOrUpdate(LensMaterial lensMaterial) {
		try {
			log.debug("Stating of the method lensMaterialSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(lensMaterial);
			log.debug("Ending of the method lensMaterialSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in lensMaterialSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<LensMaterial> getAllLensMaterials() {
		try {
			log.debug("Staring of the method getAllLensMaterials");
		//	String hql = "select distinct b from LensMaterial as b left join fetch b.cat ";
			/*String hql = "select b from LensMaterial b left join fetch b.cat ";*/
			//String hql = "from LensMaterial b left outer join b.cat where b.status=true ";
			String hql = "from LensMaterial where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllLensMaterials");
			@SuppressWarnings("unchecked")
			List<LensMaterial> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No LensMaterials are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllLensMaterials method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LensMaterial getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from LensMaterial WHERE lensMaterial_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<LensMaterial> lensMaterialList = query.list();
			if(lensMaterialList != null && !lensMaterialList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return lensMaterialList.get(0);
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
	public boolean removeLensMaterial(int id) {
		try {
			log.debug("Stating of the method removeLensMaterial");
			LensMaterial lensMaterial = new LensMaterial();
			lensMaterial.setLensMaterial_id(id);
			log.debug("Ready To remove lensMaterial with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(lensMaterial);
			sessionFactory.getCurrentSession().createQuery("Update LensMaterial Set status = false Where lensMaterial_id = '"+id+"'").executeUpdate();
			log.debug("LensMaterial Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeLensMaterial Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
