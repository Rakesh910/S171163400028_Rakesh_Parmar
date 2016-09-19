package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.LensColor;

@Repository
public class LensColorDaoImpl implements LensColorDao{

	private static final Logger log = LoggerFactory.getLogger(LensColorDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean lensColorSaveOrUpdate(LensColor lensColor) {
		try {
			log.debug("Stating of the method lensColorSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(lensColor);
			log.debug("Ending of the method lensColorSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in lensColorSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<LensColor> getAllLensColors() {
		try {
			log.debug("Staring of the method getAllLensColors");
		//	String hql = "select distinct b from LensColor as b left join fetch b.cat ";
			/*String hql = "select b from LensColor b left join fetch b.cat ";*/
			//String hql = "from LensColor b left outer join b.cat where b.status=true ";
			String hql = "from LensColor where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllLensColors");
			@SuppressWarnings("unchecked")
			List<LensColor> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No LensColors are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllLensColors method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LensColor getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from LensColor WHERE lensColor_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<LensColor> lensColorList = query.list();
			if(lensColorList != null && !lensColorList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return lensColorList.get(0);
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
	public boolean removeLensColor(int id) {
		try {
			log.debug("Stating of the method removeLensColor");
			LensColor lensColor = new LensColor();
			lensColor.setLensColor_id(id);
			log.debug("Ready To remove lensColor with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(lensColor);
			sessionFactory.getCurrentSession().createQuery("Update LensColor Set status = false Where lensColor_id = '"+id+"'").executeUpdate();
			log.debug("LensColor Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeLensColor Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
