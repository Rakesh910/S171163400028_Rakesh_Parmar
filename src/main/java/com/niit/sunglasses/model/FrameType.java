package com.niit.sunglasses.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class FrameType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int frameType_id;
	
	private String frameType_name;
	
	private boolean status;
	
	@OneToMany(mappedBy = "frameType",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getFrameType_id() {
		return frameType_id;
	}

	public void setFrameType_id(int frameType_id) {
		this.frameType_id = frameType_id;
	}

	public String getFrameType_name() {
		return frameType_name;
	}

	public void setFrameType_name(String frameType_name) {
		this.frameType_name = frameType_name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}