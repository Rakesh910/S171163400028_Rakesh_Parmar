package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.FrameType;

@Repository
public class FrameTypeDaoImpl implements FrameTypeDao{

	private static final Logger log = LoggerFactory.getLogger(FrameTypeDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean frameTypeSaveOrUpdate(FrameType frameType) {
		try {
			log.debug("Stating of the method frameTypeSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(frameType);
			log.debug("Ending of the method frameTypeSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in frameTypeSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<FrameType> getAllFrameTypes() {
		try {
			log.debug("Staring of the method getAllFrameTypes");
		//	String hql = "select distinct b from FrameType as b left join fetch b.cat ";
			/*String hql = "select b from FrameType b left join fetch b.cat ";*/
			//String hql = "from FrameType b left outer join b.cat where b.status=true ";
			String hql = "from FrameType where status=true";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getAllFrameTypes");
			@SuppressWarnings("unchecked")
			List<FrameType> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No FrameTypes are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getAllFrameTypes method ");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public FrameType getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from FrameType WHERE frameType_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<FrameType> frameTypeList = query.list();
			if(frameTypeList != null && !frameTypeList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return frameTypeList.get(0);
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
	public boolean removeFrameType(int id) {
		try {
			log.debug("Stating of the method removeFrameType");
			FrameType frameType = new FrameType();
			frameType.setFrameType_id(id);
			log.debug("Ready To remove frameType with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(frameType);
			sessionFactory.getCurrentSession().createQuery("Update FrameType Set status = false Where frameType_id = '"+id+"'").executeUpdate();
			log.debug("FrameType Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeFrameType Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	

}
