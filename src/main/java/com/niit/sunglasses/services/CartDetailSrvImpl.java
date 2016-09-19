package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.CartDetailDao;
import com.niit.sunglasses.model.CartDetail;

@Service
@Transactional
public class CartDetailSrvImpl implements CartDetailSrv {

	@Autowired
	private CartDetailDao cartDetailDao;

	@Override
	public List<CartDetail> getCartDetailsByOrderId(int id) {
		return cartDetailDao.getCartDetailsByOrderId(id);
	}

	@Override
	public boolean cartDetailSave(CartDetail cartDetail) {
		return cartDetailDao.cartDetailSave(cartDetail);
	}
}
