package com.dto;

import com.model.HospitalRoom;

public class HospitalRoomDTO {

	private Long id;
	private ClinicDTO clinicDTO;
	private String name;
	private int room_number;

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

	public ClinicDTO getClinicDTO() {
		return clinicDTO;
	}

	public void setClinicDTO(ClinicDTO clinicDTO) {
		this.clinicDTO = clinicDTO;
	}

	public int getRoom_number() {
		return room_number;
	}

	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}

	public HospitalRoomDTO(String name, int room_number) {
		this.name = name;
		this.room_number = room_number;
	}

	public HospitalRoomDTO() {
		super();
	}

	public HospitalRoomDTO(HospitalRoom hospitalRoom) {
		super();
		this.id = hospitalRoom.getId();
		this.clinicDTO = new ClinicDTO(hospitalRoom.getClinic());
	}

	public HospitalRoomDTO(Long id, ClinicDTO clinicDTO, String name, int number) {
		this.id = id;
		this.clinicDTO = clinicDTO;
		this.name = name;
		this.room_number = number;
	}

	public HospitalRoomDTO(Long id, ClinicDTO clinicDTO) {
		super();
		this.id = id;
		this.clinicDTO = clinicDTO;
	}
}
