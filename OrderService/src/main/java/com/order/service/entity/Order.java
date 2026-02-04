package com.order.service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "orders")
public class Order {

	@Id
	private int id;
	private Long ProductId;
	private String status;
	private int quantity;
	private double totalAmount;
	
	
	
	public Order( Long productId, int quantity, String status, double totalAmount) {
		super();
		
		ProductId = productId;
		this.quantity = quantity;
		this.status = status;
		this.totalAmount = totalAmount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Long getProductId() {
		return ProductId;
	}
	public void setProductId(Long productId) {
		ProductId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", ProductId=" + ProductId + ", quantity=" + quantity + ", status=" + status
				+ ", totalAmount=" + totalAmount + "]";
	}
	
	
}
