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

@Entity
@Table
public class UserRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int user_role_id;
	
	private String user_role;
	
	/*@OneToOne(mappedBy = "userRole",fetch = FetchType.EAGER)
	private UserDetail userDetail;*/
	
	@OneToMany(mappedBy = "userRole",fetch = FetchType.EAGER)
	private Set<UserDetail> userDetail;

	public Set<UserDetail> getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(Set<UserDetail> userDetail) {
		this.userDetail = userDetail;
	}

	public int getUser_role_id() {
		return user_role_id;
	}

	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	/*public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}*/

}
