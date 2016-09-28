package com.niit.sunglasses.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table
public class FrameMaterial implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int frameMaterial_id;
	
	@NotBlank(message="Frame Material Name Should Not be Blank")
	private String frameMaterial_name;
	
	private boolean status;
	
	@OneToMany(mappedBy = "frameMaterial",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getFrameMaterial_id() {
		return frameMaterial_id;
	}

	public void setFrameMaterial_id(int frameMaterial_id) {
		this.frameMaterial_id = frameMaterial_id;
	}

	public String getFrameMaterial_name() {
		return frameMaterial_name;
	}

	public void setFrameMaterial_name(String frameMaterial_name) {
		this.frameMaterial_name = frameMaterial_name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
