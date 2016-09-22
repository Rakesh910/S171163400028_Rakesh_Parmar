package com.niit.sunglasses.dao;

import com.niit.sunglasses.model.CardDetail;

public interface CardDetailDao {

	public boolean cardDetailSaveOrUpdate(CardDetail cardDetail);
	
	public CardDetail getById(int id);
	
}
