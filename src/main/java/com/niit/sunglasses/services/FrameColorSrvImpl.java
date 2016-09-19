package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.FrameColorDao;
import com.niit.sunglasses.model.FrameColor;

@Service
@Transactional
public class FrameColorSrvImpl implements FrameColorSrv {

	@Autowired
	private FrameColorDao frameColorDao;
	@Override
	public boolean frameColorSaveOrUpdate(FrameColor frameColor) {
		return frameColorDao.frameColorSaveOrUpdate(frameColor);
	}
	
	@Override
	public List<FrameColor> getAllFrameColors() {
		return frameColorDao.getAllFrameColors();
	}

	@Override
	public FrameColor getById(int id) {
		return frameColorDao.getById(id);
	}

	@Override
	public boolean removeFrameColor(int id) {
		return frameColorDao.removeFrameColor(id);
	}

}
