package com.niit.sunglasses.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.niit.sunglasses.model.OrderDetail;
import com.niit.sunglasses.model.UserDetail;
import com.niit.sunglasses.services.UserDetailSrv;

@Component
public class CheckOutHandler {
	
	@Autowired
	private UserDetailSrv userDetailSrv;
	
	@Autowired
	HttpSession session;
	
	
	public OrderDetail initFlow(){
		UserDetail user = userDetailSrv.getById((int)(session.getAttribute("userId")));
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setUser_detail(user);
		return orderDetail;
	}

}
