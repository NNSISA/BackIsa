package com.dto;

import com.model.ClinicalCenter;

public class ClinicalCenterDTO {

	private String name;
	private Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClinicalCenterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClinicalCenterDTO(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public ClinicalCenterDTO(ClinicalCenter center) {
		super();
		this.name = center.getName();
		this.id = center.getId();
	}
}
