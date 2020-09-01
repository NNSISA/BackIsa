package com.dto;

public class CalendarEventsDTO {

	String title;
	String start;
	String end;
	Long id;
	String color;

	public String getTitle() {
		return title;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public Long getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public CalendarEventsDTO(String title, String start, String end, Long id, String color) {
		this.title = title;
		this.start = start;
		this.end = end;
		this.id = id;
		this.color = color;
	}

	@Override
	public String toString() {
		return "CalendarEventsDTO{" + "title='" + title + '\'' + ", start='" + start + '\'' + ", end='" + end + '\''
				+ ", id=" + id + ", color='" + color + '\'' + '}';
	}
}
