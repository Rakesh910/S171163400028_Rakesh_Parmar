package com.niit.sunglasses.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.niit.sunglasses.dao.LensMaterialDao;
import com.niit.sunglasses.model.LensMaterial;

@Service
@Transactional
public class LensMaterialSrvImpl implements LensMaterialSrv {

	@Autowired
	private LensMaterialDao lensMaterialDao;
	@Override
	public boolean lensMaterialSaveOrUpdate(LensMaterial lensMaterial) {
		return lensMaterialDao.lensMaterialSaveOrUpdate(lensMaterial);
	}
	
	@Override
	public List<LensMaterial> getAllLensMaterials() {
		return lensMaterialDao.getAllLensMaterials();
	}

	@Override
	public LensMaterial getById(int id) {
		return lensMaterialDao.getById(id);
	}

	@Override
	public boolean removeLensMaterial(int id) {
		return lensMaterialDao.removeLensMaterial(id);
	}

}
