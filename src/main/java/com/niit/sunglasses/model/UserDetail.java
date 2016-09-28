package com.niit.sunglasses.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class UserDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_id;
	
	@NotBlank(message="This Feild Should Not be Blank")
	private String user_fname;
	
	@NotBlank(message="This Feild Should Not be Blank")
	private String user_lname;
	
	@NotBlank(message="This Feild Should Not be Blank")
	private String user_address;
	
	@NotBlank(message="This Feild Should Not be Blank")
	private String user_contact;
	
	@NotBlank(message="This Feild Should Not be Blank")
	@Email
	private String user_email;
	
	@NotBlank(message="This Feild Should Not be Blank")
	private String user_password;
	
	private boolean enabled;
	
	/*@OneToMany(mappedBy = "user_detail",fetch = FetchType.EAGER)
	private Set<OrderDetail> orderDetail;
	
	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}*/

	public boolean isStatus() {
		return enabled;
	}

	public void setStatus(boolean enabled) {
		this.enabled = enabled;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_role_id",unique = false, nullable = false)
	@JsonIgnore
	private UserRole userRole;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_fname() {
		return user_fname;
	}

	public void setUser_fname(String user_fname) {
		this.user_fname = user_fname;
	}

	public String getUser_lname() {
		return user_lname;
	}

	public void setUser_lname(String user_lname) {
		this.user_lname = user_lname;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_contact() {
		return user_contact;
	}

	public void setUser_contact(String user_contact) {
		this.user_contact = user_contact;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
