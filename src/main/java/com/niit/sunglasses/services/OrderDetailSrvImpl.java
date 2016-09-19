package com.niit.sunglasses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.OrderDetailDao;
import com.niit.sunglasses.model.OrderDetail;

@Service
@Transactional
public class OrderDetailSrvImpl implements OrderDetailSrv {

	@Autowired
	private OrderDetailDao orderDetailDao;
	@Override
	public boolean orderDetailSave(OrderDetail orderDetail) {
		return orderDetailDao.orderDetailSave(orderDetail);
	}
	
	@Override
	public OrderDetail getById(int id) {
		return orderDetailDao.getById(id);
	}
}
