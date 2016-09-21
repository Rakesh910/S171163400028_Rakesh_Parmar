package com.niit.sunglasses.handler;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
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
		OrderDetail orderDetail = (OrderDetail) session.getAttribute("order");
		orderDetail.setShipping_address(user.getUser_address());
		orderDetail.setBilling_address(user.getUser_address());
		return orderDetail;
	}
	
	public String validateDetails(OrderDetail orderDetails,MessageContext messageContext){
		String status = "success";
		
		if(orderDetails.getShipping_address().isEmpty()){
			status = "failure";
		}
		
		if(orderDetails.getBilling_address().isEmpty()){
			status = "failure";
		}
		return status;	
	}
	
	public String saveOrderDetails(OrderDetail orderDetail,MessageContext messageContext){
		try {
			
		} catch (Exception e) {

		}
		return null;
	}

}
