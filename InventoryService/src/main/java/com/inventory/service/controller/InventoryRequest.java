package com.inventory.service.controller;

public class InventoryRequest {
	
	private Long productId;
	private int quantity;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public InventoryRequest(Long productId, int quantity) {
		super();
		this.productId = productId;
		this.quantity = quantity;
	}
	
	

}
