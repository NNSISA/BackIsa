package com.dto;

public class HolidayRequestDTO {
	
	private Long id;
	private String dateStart;
	private String dateEnd;
	private String username;
	private boolean confirmed;

	public Long getId() {
		return id;
	}

	public String getDateStart() {
		return dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public String getUsername() {
		return username;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public HolidayRequestDTO() {
	}

	public HolidayRequestDTO(Long id, String dateStart, String dateEnd, String username, boolean confirmed) {
		this.id = id;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.username = username;
		this.confirmed = confirmed;
	}

	@Override
	public String toString() {
		return "HolidayRequestDTO{" + "id=" + id + ", dateStart='" + dateStart + '\'' + ", dateEnd='" + dateEnd + '\''
				+ ", username='" + username + '\'' + '}';
	}
}
