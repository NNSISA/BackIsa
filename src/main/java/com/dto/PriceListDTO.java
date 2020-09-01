package com.dto;

public class PriceListDTO {

	private int price;
	private Long id;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PriceListDTO() {
	}

	public PriceListDTO(int price, Long id) {
		this.price = price;
		this.id = id;
	}
}
