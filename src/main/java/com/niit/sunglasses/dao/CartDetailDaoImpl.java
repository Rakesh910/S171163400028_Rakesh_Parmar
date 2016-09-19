package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.sunglasses.model.CartDetail;

@Repository
public class CartDetailDaoImpl implements CartDetailDao{

	private static final Logger log = LoggerFactory.getLogger(CartDetailDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean cartDetailSave(CartDetail cartDetail) {
		try {
			log.debug("Stating of the method cartDetailSaveOrUpdate");
			sessionFactory.getCurrentSession().save(cartDetail);
			log.debug("Ending of the method cartDetailSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in cartDetailSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public List<CartDetail> getCartDetailsByOrderId(int id) {
		try {
			log.debug("Staring of the method getAllCartDetails");
		//	String hql = "select distinct b from CartDetail as b left join fetch b.cat ";
			/*String hql = "select b from CartDetail b left join fetch b.cat ";*/
			//String hql = "from CartDetail b left outer join b.cat where b.status=true ";
			
			String hql = "from CartDetail where Order_id = '"+id+"'";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			log.debug("Staring of the method getCartDetailsByOrderId");
			@SuppressWarnings("unchecked")
			List<CartDetail> list  = query.list();
			if(list==null || list.isEmpty()){
				log.info("No CartDetails are Availible");
			}
			return list;
		} catch (HibernateException e) {
			log.error("Exception occurred in getCartDetailsByOrderId method ");
			e.printStackTrace();
			return null;
		}
	}
}
