package com.niit.sunglasses.dao;

import com.niit.sunglasses.model.UserDetail;

public interface UserDetailDao {

	public boolean userDetailSaveOrUpdate(UserDetail userDetail);
	
	public UserDetail getById(int id);
	
	public UserDetail getByEmailID(String email);
	
	public boolean removeUserDetail(int id);
	
	public UserDetail isValidUser(String email,String password);
}
