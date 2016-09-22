package com.niit.sunglasses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.CardDetailDao;
import com.niit.sunglasses.model.CardDetail;

@Service
@Transactional
public class CardDetailSrvImpl implements CardDetailSrv {

	@Autowired
	private CardDetailDao cardDetailDao;
	@Override
	public boolean cardDetailSaveOrUpdate(CardDetail cardDetail) {
		return cardDetailDao.cardDetailSaveOrUpdate(cardDetail);
	}

	@Override
	public CardDetail getById(int id) {
		return cardDetailDao.getById(id);
	}
}
