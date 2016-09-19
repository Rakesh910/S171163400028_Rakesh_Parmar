package com.niit.sunglasses.services;

import java.util.List;

import com.niit.sunglasses.model.FrameColor;

public interface FrameColorSrv {
	public List<FrameColor> getAllFrameColors();
	
	public boolean frameColorSaveOrUpdate(FrameColor frameColor);
	
	public FrameColor getById(int id);
	
	public boolean removeFrameColor(int id);
}
