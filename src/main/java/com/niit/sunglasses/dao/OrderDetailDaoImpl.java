package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.OrderDetail;

@Repository
public class OrderDetailDaoImpl implements OrderDetailDao{

	private static final Logger log = LoggerFactory.getLogger(OrderDetailDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean orderDetailSave(OrderDetail orderDetail) {
		try {
			log.debug("Stating of the method orderDetailSaveOrUpdate");
			sessionFactory.getCurrentSession().save(orderDetail);
			log.debug("Ending of the method orderDetailSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in orderDetailSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public OrderDetail getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from OrderDetail WHERE orderDetail_id = '"+id+"' AND status = true");
			@SuppressWarnings("unchecked")
			List<OrderDetail> orderDetailList = query.list();
			if(orderDetailList != null && !orderDetailList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return orderDetailList.get(0);
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
