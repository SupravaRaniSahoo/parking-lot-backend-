package com.parking.pls.service;

import java.util.List;

import com.parking.pls.dto.VehicleDTO;
import com.parking.pls.entity.Vehicle;
import com.parking.pls.exception.ResourceNotFoundException;

//import java.util.List;

//import com.parking.pls.dto.VehicleDTO;

public interface VehicleDetailsService {
	public List<VehicleDTO> getAllVehicleDetails(Integer vehicleId) throws ResourceNotFoundException;
	public VehicleDTO getVehicleDetailsById(Integer vehicleId) throws ResourceNotFoundException;
	public Integer addVehicleDetails(VehicleDTO vehicleDTO) throws ResourceNotFoundException;
	public void changeStatusById(Integer detailsId)throws ResourceNotFoundException;
	public void deleteVehicle(Integer vehicleId)throws ResourceNotFoundException;
	public List<VehicleDTO> getAllVehicleDetails()throws ResourceNotFoundException;
	public Vehicle updateDetails(Integer vehicleId, Vehicle vehicle) throws ResourceNotFoundException;
	public List<VehicleDTO> getAllVehicleDetailsByName(String name) throws ResourceNotFoundException;
}
