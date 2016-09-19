package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.LensColorDao;
import com.niit.sunglasses.model.LensColor;

@Service
@Transactional
public class LensColorSrvImpl implements LensColorSrv {

	@Autowired
	private LensColorDao lensColorDao;
	@Override
	public boolean lensColorSaveOrUpdate(LensColor lensColor) {
		return lensColorDao.lensColorSaveOrUpdate(lensColor);
	}
	
	@Override
	public List<LensColor> getAllLensColors() {
		return lensColorDao.getAllLensColors();
	}

	@Override
	public LensColor getById(int id) {
		return lensColorDao.getById(id);
	}

	@Override
	public boolean removeLensColor(int id) {
		return lensColorDao.removeLensColor(id);
	}

}
