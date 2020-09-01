package com.dto;

public class DrugDTO {
	
	private Long id;
	private String name;
	private int quantity;
	private int price;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public DrugDTO() {
	}

	public DrugDTO(Long id, String name, int quantity, int price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	@Override
	public String toString() {
		return "DrugDTO{" + "id=" + id + ", name='" + name + '\'' + ", quantity=" + quantity + ", price=" + price + '}';
	}
}
