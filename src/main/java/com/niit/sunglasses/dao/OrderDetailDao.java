package com.niit.sunglasses.dao;

import com.niit.sunglasses.model.OrderDetail;

public interface OrderDetailDao {

	public boolean orderDetailSave(OrderDetail orderDetail);
	
	public OrderDetail getById(int id);
}
