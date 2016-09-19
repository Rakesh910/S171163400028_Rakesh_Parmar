package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.FrameMaterialDao;
import com.niit.sunglasses.model.FrameMaterial;

@Service
@Transactional
public class FrameMaterialSrvImpl implements FrameMaterialSrv {

	@Autowired
	private FrameMaterialDao frameMaterialDao;
	@Override
	public boolean frameMaterialSaveOrUpdate(FrameMaterial frameMaterial) {
		return frameMaterialDao.frameMaterialSaveOrUpdate(frameMaterial);
	}
	
	@Override
	public List<FrameMaterial> getAllFrameMaterials() {
		return frameMaterialDao.getAllFrameMaterials();
	}

	@Override
	public FrameMaterial getById(int id) {
		return frameMaterialDao.getById(id);
	}

	@Override
	public boolean removeFrameMaterial(int id) {
		return frameMaterialDao.removeFrameMaterial(id);
	}

}
