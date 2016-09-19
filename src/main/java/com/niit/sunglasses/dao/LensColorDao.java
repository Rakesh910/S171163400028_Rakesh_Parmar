package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.LensColor;

public interface LensColorDao {

	public List<LensColor> getAllLensColors();
	
	public boolean lensColorSaveOrUpdate(LensColor lensColor);
	
	public LensColor getById(int id);
	
	public boolean removeLensColor(int id);
}
