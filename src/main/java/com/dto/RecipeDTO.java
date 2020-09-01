package com.dto;

import java.util.List;

public class RecipeDTO {

	private long id;
	private String description;
	private List<Long> drugs;
	private String drugString = "";

	public String getDrugString() {
		return drugString;
	}

	public void setDrugString(String drugString) {
		this.drugString = drugString;
	}

	public List<Long> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Long> drugs) {
		this.drugs = drugs;
	}

	public long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "RecipeDTO{" + "id=" + id + ", description='" + description + '\'' + ", drugs=" + drugs + '}';
	}
}
