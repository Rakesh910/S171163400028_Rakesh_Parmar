package com.niit.sunglasses.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class CartDetail {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int cart_id;

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="product_id",unique = false, nullable = false)
	@JsonIgnore // @JsonIgnore ignore this field at the time of JSON file creation. Simply ignore it.
	private Product product;

	private int quantity;
	
	private float total;
	
	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name="order_id",unique = false, nullable = false)
	@JsonIgnore // @JsonIgnore ignore this field at the time of JSON file creation. Simply ignore it.
	private OrderDetail orderDetail;

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
}
