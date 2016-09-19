package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.LensColor;

public interface LensColorSrv {
	public List<LensColor> getAllLensColors();
	
	public boolean lensColorSaveOrUpdate(LensColor lensColor);
	
	public LensColor getById(int id);
	
	public boolean removeLensColor(int id);
}
