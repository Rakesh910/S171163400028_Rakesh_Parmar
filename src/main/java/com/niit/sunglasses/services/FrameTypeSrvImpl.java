package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.FrameTypeDao;
import com.niit.sunglasses.model.FrameType;

@Service
@Transactional
public class FrameTypeSrvImpl implements FrameTypeSrv {

	@Autowired
	private FrameTypeDao frameTypeDao;
	@Override
	public boolean frameTypeSaveOrUpdate(FrameType frameType) {
		return frameTypeDao.frameTypeSaveOrUpdate(frameType);
	}
	
	@Override
	public List<FrameType> getAllFrameTypes() {
		return frameTypeDao.getAllFrameTypes();
	}

	@Override
	public FrameType getById(int id) {
		return frameTypeDao.getById(id);
	}

	@Override
	public boolean removeFrameType(int id) {
		return frameTypeDao.removeFrameType(id);
	}

}
