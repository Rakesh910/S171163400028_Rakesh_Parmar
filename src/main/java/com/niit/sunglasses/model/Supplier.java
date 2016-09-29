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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table
public class Supplier implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int supplier_id;
	
	@NotBlank(message="Name Should Not be Blank")
	@Length(min=3,max=50,message="Name Must be a 3 to 50 Characters")
	private String supplier_name;
	
	@NotBlank(message="This Feild Should Not be Blank")
	@Pattern(regexp="(^$|[0-9]{10})",message="Enter a 10 digit valid phone no")
	private String supplier_contact;
	
	@NotBlank(message="Name Should Not be Blank")
	@Length(min=7,max=50,message="Email Must be a 7 to 50 Characters")
	@Email(message="Please enter a valid email Id")
	private String supplier_email;
	
	@NotBlank(message="Name Should Not be Blank")
	@Length(min=10,max=200,message="Address Must be a 10 to 200 Characters")
	private String supplier_address;
	
	private boolean status;
	
	@OneToMany(mappedBy = "supplier",fetch = FetchType.EAGER)
	private Set<Product> product;

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

	public int getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}

	public String getSupplier_name() {
		return supplier_name;
	}

	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	public String getSupplier_contact() {
		return supplier_contact;
	}

	public void setSupplier_contact(String supplier_contact) {
		this.supplier_contact = supplier_contact;
	}

	public String getSupplier_email() {
		return supplier_email;
	}

	public void setSupplier_email(String supplier_email) {
		this.supplier_email = supplier_email;
	}

	public String getSupplier_address() {
		return supplier_address;
	}

	public void setSupplier_address(String supplier_address) {
		this.supplier_address = supplier_address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
