package com.dto;

public class AvailableHospitalRoomDTO {
	
    private Long id;
    private String date;
    private String name;
    private int room_num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoom_num() {
        return room_num;
    }

    public void setRoom_num(int room_num) {
        this.room_num = room_num;
    }

    @Override
    public String toString() {
        return "AvailableHospitalRoomDTO{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", room_num=" + room_num +
                '}';
    }
}
