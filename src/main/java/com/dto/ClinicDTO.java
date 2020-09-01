package com.dto;

import com.model.Clinic;

public class ClinicDTO {

	private Long id;
	private ClinicAdministratorDTO clinicAdministratorDTO;
	private String name;
	private String address;
	private int pricelist;
	private String description;
	private int profit;
	private int rating;
	private double longitude;
	private double lat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPricelist() {
		return pricelist;
	}

	public void setPricelist(int pricelist) {
		this.pricelist = pricelist;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProfit() {
		return profit;
	}

	public void setProfit(int profit) {
		this.profit = profit;
	}

	public ClinicAdministratorDTO getClinicAdministratorDTO() {
		return clinicAdministratorDTO;
	}

	public void setClinicAdministratorDTO(ClinicAdministratorDTO clinicAdministratorDTO) {
		this.clinicAdministratorDTO = clinicAdministratorDTO;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public ClinicDTO() {
		super();
	}

	public ClinicDTO(Clinic clinic) {
		this(clinic.getName(), clinic.getAddress(), clinic.getPricelist(), clinic.getDescription(), clinic.getProfit(),
				clinic.getRating());
	}

	public ClinicDTO(String name, String address, int pricelist, String description, int profit, int rating, long id) {
		super();
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
		this.rating = rating;
		this.id = id;
	}

	public ClinicDTO(Long id, String name, String address, int pricelist, String description, int profit, int rating,
			double longitude, double lat) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
		this.rating = rating;
		this.longitude = longitude;
		this.lat = lat;
	}

	public ClinicDTO(String name, String address, int pricelist, String description, int profit, int rating) {
		super();
		this.name = name;
		this.address = address;
		this.pricelist = pricelist;
		this.description = description;
		this.profit = profit;
		this.rating = rating;

	}

	@Override
	public String toString() {
		return "ClinicDTO{" + "id=" + id + ", name='" + name + '\'' + ", address='" + address + '\'' + ", pricelist="
				+ pricelist + ", description='" + description + '\'' + ", profit=" + profit + ", rating=" + rating
				+ ", longitude=" + longitude + ", lat=" + lat + '}';
	}
}
