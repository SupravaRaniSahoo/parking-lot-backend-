package com.parking.pls.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.parking.pls.entity.Details;
import com.parking.pls.entity.Vehicle;

public interface DetailsRepository extends CrudRepository<Details, Integer>{
	
	public Optional<Details> findAllByUserName(String uName);
}
