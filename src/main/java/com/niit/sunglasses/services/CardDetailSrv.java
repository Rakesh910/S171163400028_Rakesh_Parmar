package com.niit.sunglasses.services;

import com.niit.sunglasses.model.CardDetail;

public interface CardDetailSrv {
	public boolean cardDetailSaveOrUpdate(CardDetail cardDetail);
	
	public CardDetail getById(int id);
}
