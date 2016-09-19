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
public class LensColor {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lensColor_id;
	
	private String lensColor_name;
	
	private boolean status;
	
	@OneToMany(mappedBy = "lensColor",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getLensColor_id() {
		return lensColor_id;
	}

	public void setLensColor_id(int lensColor_id) {
		this.lensColor_id = lensColor_id;
	}

	public String getLensColor_name() {
		return lensColor_name;
	}

	public void setLensColor_name(String lensColor_name) {
		this.lensColor_name = lensColor_name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
