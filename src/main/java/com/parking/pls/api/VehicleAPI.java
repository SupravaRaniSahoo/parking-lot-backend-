package com.parking.pls.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
//import com.parking.pls.dto.VehicleDTO;
import com.parking.pls.entity.Vehicle;
import com.parking.pls.exception.ResourceNotFoundException;
import com.parking.pls.repository.DetailsRepository;
import com.parking.pls.repository.VehicleRepository;
//import com.parking.pls.service.VehicleService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="/parkingAPI")
public class VehicleAPI {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	@Autowired
	private DetailsRepository detailsRepository;
	
	@GetMapping(value="/getSomeVehicle")
	public List<Vehicle> getSomeBikeDetails(){
		return (List<Vehicle>) vehicleRepository.findAll();
	}
	
	@PostMapping(value="/addSomeVehicle")
	public Vehicle addSomeBikeDetails(@RequestBody VehicleDTO vehicleDTO){
		Vehicle vehicle = new Vehicle();
		vehicle.setParkingId(vehicleDTO.getParkingId());
		vehicle.setCategory(vehicleDTO.getCategory());
		vehicle.setEntryTime(vehicleDTO.getEntryTime());
		vehicle.setName(vehicleDTO.getName());
		vehicle.setVehicleNumber(vehicleDTO.getVehicleNumber());
		vehicle.setStatus("ACTIVE");
		
		Details details = new Details();
		details.setUserName(vehicleDTO.getName());
		details.setPassword(vehicleDTO.getName()+"123");
		
		
		vehicle.setDetails(details);
		
		return vehicleRepository.save(vehicle);
	}	
	
	@GetMapping(value="/getVehicle/{id}")
	public ResponseEntity<Vehicle> getVehicleDetailsById(@PathVariable Integer id){
		Vehicle vehicle = vehicleRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Vehicle not exist with id : "+id));
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}

	
	@PutMapping(value="/updateVehicle/{id}")
	public ResponseEntity<Vehicle> updateVehicleDetails(@PathVariable Integer id, @RequestBody Vehicle vehicle){
		Vehicle vehicles = vehicleRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Vehicle not exist with id : "+id));

		vehicles.setCategory(vehicle.getCategory());
		vehicles.setName(vehicle.getName());
		vehicles.setEntryTime(vehicle.getEntryTime());
		vehicles.setVehicleNumber(vehicle.getVehicleNumber());
		
		Vehicle updatedVehicle = vehicleRepository.save(vehicles);
		
		return new ResponseEntity<Vehicle>(updatedVehicle, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/updateVehicleStatus/{id}")
	public ResponseEntity<Vehicle> updateStatusVehicleDetails(@PathVariable Integer id){
		Vehicle vehicles = vehicleRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Vehicle not exist with id : "+id));

		vehicles.setStatus("INACTIVE");
		
		Vehicle updatedVehicle = vehicleRepository.save(vehicles);
		
		return new ResponseEntity<Vehicle>(updatedVehicle, HttpStatus.OK);
	}
	
	@PostMapping(value="/addVehicle/{id}")
	public ResponseEntity<Vehicle> addVehicleDetails(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO){
		Vehicle vehicles = vehicleRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Vehicle not exist with id : "+id));

		vehicles.setExitTime(vehicleDTO.getExitTime());
		
		Vehicle updatedVehicle = vehicleRepository.save(vehicles);
		
		return new ResponseEntity<Vehicle>(updatedVehicle, HttpStatus.OK);
	}
	
    @DeleteMapping(value="/deleteVehicle/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteVehicleDetails(@PathVariable Integer id){
    	Vehicle vehicles = vehicleRepository.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Vehicle not exist with id : "+id));
    	
    	vehicleRepository.delete(vehicles);
    	Map<String, Boolean> map = new HashMap<>();
    	map.put("deleted successfully", Boolean.TRUE);
    	
    	return ResponseEntity.ok(map);
    }
    
    @GetMapping(value="/getVehicleByStatus")
    public ResponseEntity<List<VehicleDTO>> getVehicleByStatus(){
    	Iterable<Vehicle> vehicles = vehicleRepository.findAll();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		
		vehicles.forEach(vehicle -> {
			VehicleDTO vehicleDTO = new VehicleDTO();
			vehicleDTO.setParkingId(vehicle.getParkingId());
			vehicleDTO.setName(vehicle.getName());
			vehicleDTO.setStatus(vehicle.getStatus());
			vehicleDTO.setEntryTime(vehicle.getEntryTime());
			vehicleDTO.setExitTime(vehicle.getExitTime());
			vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
			vehicleDTO.setCategory(vehicle.getCategory());
			if(vehicle.getStatus().equals("INACTIVE")) {
			vehicleDTOs.add(vehicleDTO);
			}
		});	
		return new ResponseEntity<List<VehicleDTO>>(vehicleDTOs, HttpStatus.OK);
    }
    
    @GetMapping(value="/getUserVehicleByName")
    public ResponseEntity<List<VehicleDTO>> getVehicleByName(){
    	Iterable<Vehicle> vehicles = vehicleRepository.findAll();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		
		vehicles.forEach(vehicle -> {
			VehicleDTO vehicleDTO = new VehicleDTO();
			vehicleDTO.setParkingId(vehicle.getParkingId());
			vehicleDTO.setName(vehicle.getName());
			vehicleDTO.setStatus(vehicle.getStatus());
			vehicleDTO.setEntryTime(vehicle.getEntryTime());
			vehicleDTO.setExitTime(vehicle.getExitTime());
			vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
			vehicleDTO.setCategory(vehicle.getCategory());
			
			Details details = vehicle.getDetails();
				DetailsDTO detailsDTO = new DetailsDTO();
				if(details != null) {
				detailsDTO.setDetailsId(details.getDetailsId());
				detailsDTO.setUserName(details.getUserName());
				detailsDTO.setPassword(details.getPassword());
				vehicleDTO.setDetailsDTO(detailsDTO);
			}					
			if(vehicleDTO.getName().equals(detailsDTO.getUserName())) {
			  vehicleDTOs.add(vehicleDTO);
			}
		});	
		return new ResponseEntity<List<VehicleDTO>>(vehicleDTOs, HttpStatus.OK);
    }  
    
    @GetMapping(value="getVByName/{name}")
    public ResponseEntity<List<VehicleDTO>> getVehicleByNameForUser(@PathVariable String name){
    	Iterable<Vehicle> vehicles = vehicleRepository.findAllByName(name);	
    	List<VehicleDTO> list = new ArrayList<>();
    	
    	vehicles.forEach(vehicle -> {
    	VehicleDTO vehicleDTO = new VehicleDTO();
		vehicleDTO.setParkingId(vehicle.getParkingId());
		vehicleDTO.setName(vehicle.getName());
		vehicleDTO.setStatus(vehicle.getStatus());
		vehicleDTO.setEntryTime(vehicle.getEntryTime());
		vehicleDTO.setExitTime(vehicle.getExitTime());
		vehicleDTO.setVehicleNumber(vehicle.getVehicleNumber());
		vehicleDTO.setCategory(vehicle.getCategory());	
		
		Details details = vehicle.getDetails();
		DetailsDTO detailsDTO = new DetailsDTO();
		if(details != null) {
		detailsDTO.setDetailsId(details.getDetailsId());
		detailsDTO.setUserName(details.getUserName());
		detailsDTO.setPassword(details.getPassword());
		vehicleDTO.setDetailsDTO(detailsDTO);
	  }					
		
		if(vehicleDTO.getName().equals(name)) {
			list.add(vehicleDTO);
		 }	
     });
//		Details details = detailsRepository.findAllByUserName(name).
//				orElseThrow(()->new ResourceNotFoundException("Details not exist with name : "+name));
//		
//			DetailsDTO detailsDTO = new DetailsDTO();
//			if(details != null) {
//			detailsDTO.setDetailsId(details.getDetailsId());
//			detailsDTO.setUserName(details.getUserName());
//			detailsDTO.setPassword(details.getPassword());
//		   }	
	
		return new ResponseEntity<List<VehicleDTO>>(list, HttpStatus.OK); 
    }
    
//    @GetMapping(value="/getAllV")
//	public List<Vehicle> getAllDetails(){
//    	List<Vehicle> lists = new ArrayList<>();
//    	List<Vehicle> list =  (List<Vehicle>) vehicleRepository.findAll();
//    	lists.add((Vehicle) list);
//		return null;
//	}

}
