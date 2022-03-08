package com.parking.pls.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "parking")
public class Vehicle {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
   private Integer parkingId;
   @NotNull(message = "Category is required")
   @Column(name="veichle_type", nullable = false)
   private String category;
   @NotNull(message = "Username is required")
   @Column(name="name", nullable = false)
   private String name;
   @NotNull(message = "Vehicle Number is required")
   @Column(name="vehicle_number", nullable = false)
   private String vehicleNumber;
   @Column(name="time_of_entry", nullable = false)
//   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private LocalDateTime entryTime;
   @Column(name="time_of_exit", nullable = true)
   private String exitTime;
   @Column(name="status", nullable = true)
   private String status;
   @ManyToOne(cascade=CascadeType.ALL)
   @JoinColumn(name="detail_id")
   private Details details;
   
   
public Vehicle() {
	   
   }

public Vehicle(String category, String name, String vehicleNumber, LocalDateTime entryTime, String exitTime,
		String status, Details details) {
	super();
	this.category = category;
	this.name = name;
	this.vehicleNumber = vehicleNumber;
	this.entryTime = entryTime;
	this.exitTime = exitTime;
	this.status = status;
	this.details = details;
}




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
public String getVehicleNumber() {
	return vehicleNumber;
}
public void setVehicleNumber(String vehicleNumber) {
	this.vehicleNumber = vehicleNumber;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public Details getDetails() {
	return details;
}
public void setDetails(Details details) {
	this.details = details;
}
public String getExitTime() {
	return exitTime;
}
public void setExitTime(String exitTime) {
	this.exitTime = exitTime;
}


@Override 
public int hashCode() {
	return Objects.hash(parkingId);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Vehicle other = (Vehicle) obj;
	return Objects.equals(parkingId, other.parkingId);
}

}

