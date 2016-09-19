package com.niit.sunglasses.services;

import com.niit.sunglasses.model.OrderDetail;

public interface OrderDetailSrv {
public boolean orderDetailSave(OrderDetail orderDetail);
	
	public OrderDetail getById(int id);
}
