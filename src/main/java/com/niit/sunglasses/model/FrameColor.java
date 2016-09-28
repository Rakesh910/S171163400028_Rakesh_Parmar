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
public class FrameColor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int frameColor_id;
	
	@NotBlank(message="Frame Color Name Should Not be Blank")
	private String frameColor_name;
	
	private boolean status;
	
	@OneToMany(mappedBy = "frameColor",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getFrameColor_id() {
		return frameColor_id;
	}

	public void setFrameColor_id(int frameColor_id) {
		this.frameColor_id = frameColor_id;
	}

	public String getFrameColor_name() {
		return frameColor_name;
	}

	public void setFrameColor_name(String frameColor_name) {
		this.frameColor_name = frameColor_name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
