package com.niit.sunglasses.model;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class OrderDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int order_id;
	
	private float grandTotal;
	
	private Date orderDate;
	
	private boolean payment_status;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="user_id",unique = false, nullable = false)
	@JsonIgnore // @JsonIgnore ignore this field at the time of JSON file creation. Simply ignore it.
	private UserDetail user_detail;
	
	@OneToMany(mappedBy = "orderDetail",fetch = FetchType.EAGER)
	private Set<CartDetail> cartDetail;

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isPayment_status() {
		return payment_status;
	}

	public void setPayment_status(boolean payment_status) {
		this.payment_status = payment_status;
	}

	public UserDetail getUser_detail() {
		return user_detail;
	}

	public void setUser_detail(UserDetail user_detail) {
		this.user_detail = user_detail;
	}

	public Set<CartDetail> getCartDetail() {
		return cartDetail;
	}

	public void setCartDetail(Set<CartDetail> cartDetail) {
		this.cartDetail = cartDetail;
	}

}
