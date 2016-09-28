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
public class LensMaterial implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int lensMaterial_id;
	
	@NotBlank(message="Lens Material Name Should Not be Blank")
	private String lensMaterial_name;
	
	private boolean status;
	
	@OneToMany(mappedBy = "lensMaterial",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getLensMaterial_id() {
		return lensMaterial_id;
	}

	public void setLensMaterial_id(int lensMaterial_id) {
		this.lensMaterial_id = lensMaterial_id;
	}

	public String getLensMaterial_name() {
		return lensMaterial_name;
	}

	public void setLensMaterial_name(String lensMaterial_name) {
		this.lensMaterial_name = lensMaterial_name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
