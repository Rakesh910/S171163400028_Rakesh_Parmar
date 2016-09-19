package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.LensMaterial;

public interface LensMaterialDao {

	public List<LensMaterial> getAllLensMaterials();
	
	public boolean lensMaterialSaveOrUpdate(LensMaterial lensMaterial);
	
	public LensMaterial getById(int id);
	
	public boolean removeLensMaterial(int id);
}
