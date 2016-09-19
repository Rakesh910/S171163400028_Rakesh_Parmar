package com.niit.sunglasses.dao;

import java.util.List;

import com.niit.sunglasses.model.FrameType;

public interface FrameTypeDao {

	public List<FrameType> getAllFrameTypes();
	
	public boolean frameTypeSaveOrUpdate(FrameType frameType);
	
	public FrameType getById(int id);
	
	public boolean removeFrameType(int id);
}
