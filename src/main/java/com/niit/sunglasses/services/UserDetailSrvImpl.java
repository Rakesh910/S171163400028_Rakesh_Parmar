package com.niit.sunglasses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.UserDetailDao;
import com.niit.sunglasses.model.UserDetail;

@Service
@Transactional
public class UserDetailSrvImpl implements UserDetailSrv {

	@Autowired
	private UserDetailDao userDetailDao;

	@Override
	public boolean userDetailSaveOrUpdate(UserDetail userDetail) {
		return userDetailDao.userDetailSaveOrUpdate(userDetail);
	}

	@Override
	public UserDetail getById(int id) {
		return userDetailDao.getById(id);
	}

	@Override
	public UserDetail getByEmailID(String email) {
		return userDetailDao.getByEmailID(email);
	}

	@Override
	public boolean removeUserDetail(int id) {
		return userDetailDao.removeUserDetail(id);
	}

	@Override
	public UserDetail isValidUser(String email, String password) {
		return userDetailDao.isValidUser(email, password);
	}
	}
