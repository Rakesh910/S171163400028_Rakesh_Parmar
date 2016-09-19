package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.FrameMaterial;

public interface FrameMaterialSrv {
	public List<FrameMaterial> getAllFrameMaterials();
	
	public boolean frameMaterialSaveOrUpdate(FrameMaterial frameMaterial);
	
	public FrameMaterial getById(int id);
	
	public boolean removeFrameMaterial(int id);
}
