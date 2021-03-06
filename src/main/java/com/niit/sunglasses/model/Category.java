package com.niit.sunglasses.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cat_id;
	
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="brand_id",unique = false, nullable = false)
	@JsonIgnore // @JsonIgnore ignore this field at the time of JSON file creation. Simply ignore it.
	private Brand brand;
	
	@NotBlank(message="Category Name Should Not be Blank")
	@Length(min=3,max=20,message="Name Must be a 3 to 20 Characters")
	private String cat_name;
	
	
	private boolean status;
	
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
