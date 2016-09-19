package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.FrameColor;

@Repository
public class FrameColorDaoImpl implements FrameColorDao{

	private static final Logger log = LoggerFactory.getLogger(FrameColorDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean frameColorSaveOrUpdate(FrameColor frameColor) {
		try {
			log.debug("Stating of the method frameColorSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(frameColor);
			log.debug("Ending of the method frameColorSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in frameColorSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<FrameColor> getAllFrameColors() {
		try {
			log.debug("Staring of the method getAllFrameColors");
		//	String hql = "select distinct b from FrameColor as b left join fetch b.cat ";
			/*String hql = "select b from FrameColor b left join fetch b.cat ";*/
			//String hql = "from FrameColor b left outer join b.cat where b.status=true ";
			String hql = "from FrameColor where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllFrameColors");
			@SuppressWarnings("unchecked")
			List<FrameColor> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No FrameColors are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllFrameColors method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public FrameColor getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from FrameColor WHERE frameColor_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<FrameColor> frameColorList = query.list();
			if(frameColorList != null && !frameColorList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return frameColorList.get(0);
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
	public boolean removeFrameColor(int id) {
		try {
			log.debug("Stating of the method removeFrameColor");
			FrameColor frameColor = new FrameColor();
			frameColor.setFrameColor_id(id);
			log.debug("Ready To remove frameColor with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(frameColor);
			sessionFactory.getCurrentSession().createQuery("Update FrameColor Set status = false Where frameColor_id = '"+id+"'").executeUpdate();
			log.debug("FrameColor Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeFrameColor Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
