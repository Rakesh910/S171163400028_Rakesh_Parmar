package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.CartDetail;

public interface CartDetailDao {

	public List<CartDetail> getCartDetailsByOrderId(int id);
	
	public boolean cartDetailSave(CartDetail cartDetail);
}
