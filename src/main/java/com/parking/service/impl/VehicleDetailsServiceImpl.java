package com.parking.service.impl;

import com.parking.pls.dto.DetailsDTO;
import com.parking.pls.dto.VehicleDTO;
import com.parking.pls.entity.Details;
import com.parking.pls.entity.Vehicle;
import com.parking.pls.exception.ResourceNotFoundException;
import com.parking.pls.repository.DetailsRepository;
import com.parking.pls.repository.VehicleRepository;
import com.parking.pls.service.VehicleDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value="vehicleDetailsService")
@Transactional
public class VehicleDetailsServiceImpl implements VehicleDetailsService{
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DetailsRepository detailsRepository;

	public VehicleDetailsServiceImpl(VehicleRepository vehicleRepository, DetailsRepository detailsRepository) {
		super();
		this.vehicleRepository = vehicleRepository;
		this.detailsRepository = detailsRepository;
	}
	
	@Override
	public List<VehicleDTO> getAllVehicleDetails(Integer vehicleId) throws ResourceNotFoundException{
		
		return null;
	}
	
	@Override
	public VehicleDTO getVehicleDetailsById(Integer vehicleId) throws ResourceNotFoundException{
		Optional<Vehicle> optional = vehicleRepository.findById(vehicleId);
		Vehicle vehicle = optional.orElseThrow(()-> new ResourceNotFoundException("vehicle with id "+vehicleId+" not found"));
		VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setParkingId(vehicle.getParkingId());
		vehicleDTO.setCategory(vehicle.getCategory());
		vehicleDTO.setName(vehicle.getName());
		vehicleDTO.setEntryTime(vehicle.getEntryTime());
		vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
		vehicleDTO.setStatus(vehicle.getStatus());
		
		Details details = vehicle.getDetails();
		if(details != null) {
			DetailsDTO detailsDTO = new DetailsDTO();
			detailsDTO.setDetailsId(details.getDetailsId());
			detailsDTO.setUserName(details.getUserName());
			detailsDTO.setPassword(details.getPassword());
			vehicleDTO.setDetailsDTO(detailsDTO);
		}
		return vehicleDTO;
	}
	
	@Override
	public Integer addVehicleDetails(VehicleDTO vehicleDTO) throws ResourceNotFoundException{
		Vehicle vehicle = new Vehicle();
		vehicle.setParkingId(vehicleDTO.getParkingId());
		vehicle.setCategory(vehicleDTO.getCategory());
		vehicle.setEntryTime(vehicleDTO.getEntryTime());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
		vehicle.setStatus("ACTIVE");
		DetailsDTO detailsDTO = vehicleDTO.getDetailsDTO();
		Details details = new Details();
		details.setDetailsId(detailsDTO.getDetailsId());
		details.setUserName(details.getUserName());
		details.setPassword(detailsDTO.getPassword());
		vehicle.setDetails(details);
		vehicleRepository.save(vehicle);
		
		return vehicle.getParkingId();
	}
	
	@Override
	public void changeStatusById(Integer detailsId)throws ResourceNotFoundException{
		Optional<Vehicle> optional = vehicleRepository.findById(detailsId);
		Vehicle vehicle = optional.orElseThrow(()->new ResourceNotFoundException("Vehicles with "+detailsId+" not found"));
		vehicle.setStatus("INACTIVE");
	}
	
	@Override
	public void deleteVehicle(Integer vehicleId)throws ResourceNotFoundException{
		Optional<Vehicle> optional = vehicleRepository.findById(vehicleId);
		Vehicle vehicle = optional.orElseThrow(()-> new ResourceNotFoundException("vehicle with id "+vehicleId+" not found"));
		vehicle.setDetails(null);
		vehicleRepository.delete(vehicle);
	}
	
	@Override
	public List<VehicleDTO> getAllVehicleDetails()throws ResourceNotFoundException{
		Iterable<Vehicle> vehicles = vehicleRepository.findAll();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		vehicles.forEach(vehicle -> {
			VehicleDTO vehicleDTO = new VehicleDTO();
			vehicleDTO.setParkingId(vehicle.getParkingId());
			vehicleDTO.setName(vehicle.getName());
			vehicleDTO.setStatus(vehicle.getStatus());
			vehicleDTO.setEntryTime(vehicle.getEntryTime());
			vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
			vehicleDTO.setCategory(vehicle.getCategory());
			
			DetailsDTO detailsDTO = new DetailsDTO();
			detailsDTO.setDetailsId(vehicle.getDetails().getDetailsId());
			detailsDTO.setUserName(vehicle.getDetails().getUserName());
			detailsDTO.setPassword(vehicle.getDetails().getPassword());
			vehicleDTO.setDetailsDTO(detailsDTO);
			vehicleDTOs.add(vehicleDTO);
		});
		if(vehicleDTOs.isEmpty())
			throw new ResourceNotFoundException("Vehicle Not Found");
		
		  return vehicleDTOs;
	}
	
	@Override
	public Vehicle updateDetails(Integer vehicleId, Vehicle vehicle) throws ResourceNotFoundException{
		Vehicle vehicles = vehicleRepository.findById(vehicleId).
				orElseThrow(()->new ResourceNotFoundException("Vehicle not exist with id : "+vehicleId));

		vehicles.setName(vehicle.getName());
		
		vehicleRepository.save(vehicles);
		return null;
	}
	
	@Override
	public List<VehicleDTO> getAllVehicleDetailsByName(String name) throws ResourceNotFoundException{
//		Iterable<Vehicle> vehicles = vehicleRepository.findAllByName(name);
//		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
//		vehicles.forEach(vehicle -> {
//			VehicleDTO vehicleDTO = new VehicleDTO();
//			vehicleDTO.setParkingId(vehicle.getParkingId());
//			vehicleDTO.setName(vehicle.getName());
//			vehicleDTO.setStatus(vehicle.getStatus());
//			vehicleDTO.setEntryTime(vehicle.getEntryTime());
//			vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
//			vehicleDTO.setCategory(vehicle.getCategory());
//			
//			DetailsDTO detailsDTO = new DetailsDTO();
//			detailsDTO.setDetailsId(vehicle.getDetails().getDetailsId());
//			detailsDTO.setUserName(vehicle.getDetails().getUserName());
//			detailsDTO.setPassword(vehicle.getDetails().getPassword());
//			vehicleDTO.setDetailsDTO(detailsDTO);
//			vehicleDTOs.add(vehicleDTO);
//		});
//		if(vehicleDTOs.isEmpty())
//			throw new ResourceNotFoundException("Vehicle Not Found");
//		
		  return null;
	}
	
	
	
	

//	@Override
//	public List<VehicleDTO> findAllVehicles(){
//		Iterable<Vehicle> vehicles = vehicleRepository.findAll();
//		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
//		
//		vehicles.forEach(vehicle -> {
//			VehicleDTO vehicleDTO = new VehicleDTO();
//			vehicleDTO.setParkingId(vehicle.getParkingId());
//			vehicleDTO.setCategory(vehicle.getCategory());
//			vehicleDTO.setName(vehicle.getName());
//			vehicleDTO.setEntryTime(vehicle.getEntryTime());
//			vehicleDTO.setDuration(vehicle.getDuration());
//			vehicleDTOs.add(vehicleDTO);
//		});
//		if(vehicleDTOs.isEmpty()) {
//			System.out.println("VEHICLES NOT FOUND");
//		}
//		return null;
//	}
//	
//	@Override
//	public VehicleDTO getVeichleDetails(String category){
//		Optional<Vehicle> optional = vehicleRepository.findByCategory(category); 
//		if (!optional.isPresent()) {
//			System.out.println("VEHICLES NOT FOUND");
//		}
//		
//		Vehicle vehicle = new Vehicle();
//		VehicleDTO vehicleDTO = new VehicleDTO();
//		vehicleDTO.setParkingId(vehicle.getParkingId());
//		vehicleDTO.setCategory(vehicle.getCategory());
//		vehicleDTO.setEntryTime(vehicle.getEntryTime());
//		vehicleDTO.setName(vehicle.getName());
//		vehicleDTO.setDuration(vehicle.getDuration());
//		
//		return vehicleDTO;
//	}
//	
//	@Override
//	public Integer addVehicle(VehicleDTO vehicle) {
//		Optional<Vehicle> optional = vehicleRepository.findById(vehicle.getParkingId());
//		if (optional.isPresent()) {
//			System.out.println("VEHICLE FOUND");
//		}
//		
//		Vehicle vehicles = new Vehicle();
//		vehicles.setParkingId(vehicle.getParkingId());
//		vehicles.setCategory(vehicle.getCategory());
//		vehicles.setName(vehicle.getName());
//		vehicles.setEntryTime(vehicle.getEntryTime());
//		vehicles.setDuration(vehicle.getDuration());
//		
//		vehicleRepository.save(vehicles);
//		
//		return vehicles.getParkingId();
//	}
}
