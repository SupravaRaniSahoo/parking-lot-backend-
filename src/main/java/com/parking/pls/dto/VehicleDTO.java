package com.parking.pls.dto;

import java.time.LocalDateTime;

public class VehicleDTO {
	
	  private Integer parkingId;
	  private String category;
	  private String name;
	  private String vehicleNumber;
	  private LocalDateTime entryTime;
	  private String status;
	  private String exitTime;
	  private DetailsDTO detailsDTO;
	  
	  
	public Integer getParkingId() {
		return parkingId;
	}
	public void setParkingId(Integer parkingId) {
		this.parkingId = parkingId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getExitTime() {
		return exitTime;
	}
	public void setExitTime(String exitTime) {
		this.exitTime = exitTime;
	}
	public DetailsDTO getDetailsDTO() {
		return detailsDTO;
	}
	public void setDetailsDTO(DetailsDTO detailsDTO) {
		this.detailsDTO = detailsDTO;
	}
	
	@Override
	public String toString() {
		return "VehicleDTO [parkingId=" + parkingId + ", category=" + category + ", name=" + name + ", vehicleNumber="
				+ vehicleNumber + ", entryTime=" + entryTime + ", status=" + status + ", exitTime=" + exitTime
				+ ", detailsDTO=" + detailsDTO + "]";
	}	  
	  
}
