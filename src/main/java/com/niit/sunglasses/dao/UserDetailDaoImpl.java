package com.niit.sunglasses.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.sunglasses.model.Brand;
import com.niit.sunglasses.model.UserDetail;

@Repository
public class UserDetailDaoImpl implements UserDetailDao{

	private static final Logger log = LoggerFactory.getLogger(UserDetailDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean userDetailSaveOrUpdate(UserDetail userDetail) {
		try {
			log.debug("Stating of the method userDetailSaveOrUpdate");
			sessionFactory.getCurrentSession().saveOrUpdate(userDetail);
			log.debug("Ending of the method userDetailSaveOrUpdate");
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.error("Exception occurred in userDetailSaveOrUpdate method ");
			return false;
		}
	}

	@Override
	public UserDetail getById(int id) {
		try {
			log.debug("Staring of the method getById with id ="+id);
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail WHERE user_id = '"+id+"' AND enabled = true");
			@SuppressWarnings("unchecked")
			List<UserDetail> userList = query.list();
			if(userList != null && !userList.isEmpty()){
				log.debug("Record Found in method getById with id ="+id);
				return userList.get(0);
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
	public UserDetail getByEmailID(String email) {
		try {
			log.debug("Staring of the method getById with id ="+email);
			Query query = sessionFactory.getCurrentSession().createQuery("from UserDetail WHERE user_email = '"+email+"' AND enabled = true");
			@SuppressWarnings("unchecked")
			List<UserDetail> userList = query.list();
			if(userList != null && !userList.isEmpty()){
				log.debug("Record Found in method getById with id ="+email);
				return userList.get(0);
			}else{
				log.debug("No Record Found in getById with id ="+email);
				return null;
			}
			/*return (UserDetail) sessionFactory.getCurrentSession().get(UserDetail.class, new String(email));*/
		} catch (HibernateException e) {
			log.error("Error Occures in getById Method..!! (id = '"+email+"')");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean removeUserDetail(int id) {
		try {
			log.debug("Stating of the method removeUserDetail");
			Brand brand = new Brand();
			brand.setBrand_id(id);
			log.debug("Ready To remove userDetail with Id :- "+id);
			//sessionFactory.getCurrentSession().delete(brand);
			sessionFactory.getCurrentSession().createQuery("Update UserDetail Set enabled = false Where user_id = '"+id+"'").executeUpdate();
			log.debug("UserDetail Removed with Id :- "+id);
			return true;
		} catch (HibernateException e) {
			log.error("Error Occures in removeUserDetail Method..!! (id = '"+id+"')");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UserDetail isValidUser(String email, String password) {
		String hql = "from UserDetail where user_email = '"+email+"' AND user_password = '"+password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<UserDetail> list = query.list();
		if(list == null || list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}
}
