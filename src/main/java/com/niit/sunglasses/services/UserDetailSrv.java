package com.niit.sunglasses.services;

import com.niit.sunglasses.model.UserDetail;

public interface UserDetailSrv {

	public boolean userDetailSaveOrUpdate(UserDetail userDetail);
	
	public UserDetail getById(int id);
	
	public UserDetail getByEmailID(String email);
	
	public boolean removeUserDetail(int id);
	
	public UserDetail isValidUser(String email,String password);
}
