package com.parking.pls.repository;


import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.parking.pls.entity.Vehicle;


public interface VehicleRepository extends CrudRepository<Vehicle , Integer>{
	public Iterable<Vehicle> findAllByName(String name);
	public Iterable<Vehicle> findAllByStatus(String status);
}
