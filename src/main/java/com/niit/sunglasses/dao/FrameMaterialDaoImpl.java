package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.FrameMaterial;

@Repository
public class FrameMaterialDaoImpl implements FrameMaterialDao{

	private static final Logger log = LoggerFactory.getLogger(FrameMaterialDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean frameMaterialSaveOrUpdate(FrameMaterial frameMaterial) {
		try {
			log.debug("Stating of the method frameMaterialSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(frameMaterial);
			log.debug("Ending of the method frameMaterialSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in frameMaterialSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<FrameMaterial> getAllFrameMaterials() {
		try {
			log.debug("Staring of the method getAllFrameMaterials");
		//	String hql = "select distinct b from FrameMaterial as b left join fetch b.cat ";
			/*String hql = "select b from FrameMaterial b left join fetch b.cat ";*/
			//String hql = "from FrameMaterial b left outer join b.cat where b.status=true ";
			String hql = "from FrameMaterial where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllFrameMaterials");
			@SuppressWarnings("unchecked")
			List<FrameMaterial> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No FrameMaterials are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllFrameMaterials method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public FrameMaterial getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from FrameMaterial WHERE frameMaterial_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<FrameMaterial> frameMaterialList = query.list();
			if(frameMaterialList != null && !frameMaterialList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return frameMaterialList.get(0);
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
	public boolean removeFrameMaterial(int id) {
		try {
			log.debug("Stating of the method removeFrameMaterial");
			FrameMaterial frameMaterial = new FrameMaterial();
			frameMaterial.setFrameMaterial_id(id);
			log.debug("Ready To remove frameMaterial with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(frameMaterial);
			sessionFactory.getCurrentSession().createQuery("Update FrameMaterial Set status = false Where frameMaterial_id = '"+id+"'").executeUpdate();
			log.debug("FrameMaterial Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeFrameMaterial Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
