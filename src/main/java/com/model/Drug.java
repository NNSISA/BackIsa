package com.model;

import javax.persistence.*;

@Entity
public class Drug {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "quantity", nullable = false)
	private int quantity;
	@Column(name = "price", nullable = false)
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

	public Drug() {
	}

	@Override
	public String toString() {
		return "Drug{" + "id=" + id + ", name='" + name + '\'' + ", quantity=" + quantity + ", price=" + price + '}';
	}
}
