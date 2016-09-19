package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.CartDetail;

public interface CartDetailSrv {
public List<CartDetail> getCartDetailsByOrderId(int id);
	
	public boolean cartDetailSave(CartDetail cartDetail);
}
