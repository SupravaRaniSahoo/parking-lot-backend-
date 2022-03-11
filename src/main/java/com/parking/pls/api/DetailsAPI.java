package com.parking.pls.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.pls.dto.DetailsDTO;
import com.parking.pls.dto.VehicleDTO;
import com.parking.pls.entity.Details;
import com.parking.pls.entity.Vehicle;
import com.parking.pls.exception.ResourceNotFoundException;
import com.parking.pls.repository.DetailsRepository;
//import com.parking.pls.repository.DetailsRepository;
import com.parking.pls.repository.VehicleRepository;

@RestController
@CrossOrigin
@RequestMapping(value="/detailsAPI")
public class DetailsAPI {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DetailsRepository detailsRepository;
	
	@GetMapping(value="/getAllDetails")
	public List<Details> getAllDetails(){
		return (List<Details>) detailsRepository.findAll();
	}

	
	@GetMapping(value="/getVehicleDetails/{id}")
	public ResponseEntity<VehicleDTO> getVehicleDetailsById(@PathVariable Integer id){
		Optional<Vehicle> optional = vehicleRepository.findById(id);
		Vehicle vehicle = optional.orElseThrow(()-> new ResourceNotFoundException("vehicle with id "+id+" not found"));
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
		return new ResponseEntity<VehicleDTO>(vehicleDTO, HttpStatus.OK);
	}
	
	
	@PostMapping(value="/addVehicleDetails")
	public ResponseEntity<Details> addNewVehicleDetails(@RequestBody DetailsDTO detailsDTO){
	
		Details details = new Details();
//		details.setDetailsId(detailsDTO.getDetailsId());
		details.setUserName(detailsDTO.getUserName());
		details.setPassword(detailsDTO.getPassword());
		detailsRepository.save(details);
		
		return new ResponseEntity<Details>(details, HttpStatus.CREATED);
	}
	
	@PutMapping(value="/updateVehicleStatus/{id}")
	public ResponseEntity<Vehicle> changeVehicleStatusById(@PathVariable Integer id){
		Optional<Vehicle> optional = vehicleRepository.findById(id);
		Vehicle vehicle = optional.orElseThrow(()->new ResourceNotFoundException("Vehicles with id: "+id+" not found"));
		  vehicle.setStatus("INACTIVE");
//        String message = "status with id: "+id+" updated successfully";
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}
	
	@PutMapping(value="/updateVehicleDetails/{id}")
	public ResponseEntity<String> deleteVehicleById(@PathVariable Integer id){
		Optional<Vehicle> optional = vehicleRepository.findById(id);
		Vehicle vehicle = optional.orElseThrow(()-> new ResourceNotFoundException("vehicle with id "+id+" not found"));
		vehicle.setDetails(null);
		vehicleRepository.delete(vehicle);
		String message = "Vehicle with id: "+id+" deleted successfully";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping(value="getAllVehicleDetails")
	public ResponseEntity<List<VehicleDTO>> getAllVehicleList(){
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
		
		return new ResponseEntity<List<VehicleDTO>>(vehicleDTOs, HttpStatus.OK);
	}
	
//	@GetMapping(value="getAllVehicleDetailsByName/{name}")
//	public ResponseEntity<List<VehicleDTO>> getAllVehicleListByName(@PathVariable String name){
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
//		});
//		if(vehicleDTOs.isEmpty())
//			throw new ResourceNotFoundException("Vehicle Not Found");
//		
//		return new ResponseEntity<List<VehicleDTO>>(vehicleDTOs, HttpStatus.OK);
//	}
	

}
