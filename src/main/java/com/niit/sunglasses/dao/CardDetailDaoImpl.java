package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.CardDetail;

@Repository
public class CardDetailDaoImpl implements CardDetailDao{

	private static final Logger log = LoggerFactory.getLogger(CardDetailDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean cardDetailSaveOrUpdate(CardDetail cardDetail) {
		try {
			log.debug("Stating of the method cardDetailSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(cardDetail);
			log.debug("Ending of the method cardDetailSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in cardDetailSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public CardDetail getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from CardDetail WHERE card_id = '"+id+"'");
			@SuppressWarnings("unchecked")
			List<CardDetail> cardDetailList = query.list();
			if(cardDetailList != null && !cardDetailList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return cardDetailList.get(0);
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
}
