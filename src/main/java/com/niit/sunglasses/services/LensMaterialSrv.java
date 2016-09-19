package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.LensMaterial;

public interface LensMaterialSrv {
	public List<LensMaterial> getAllLensMaterials();
	
	public boolean lensMaterialSaveOrUpdate(LensMaterial lensMaterial);
	
	public LensMaterial getById(int id);
	
	public boolean removeLensMaterial(int id);
}
