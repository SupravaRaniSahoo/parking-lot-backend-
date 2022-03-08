package com.parking.pls.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.parking.pls.entity.Login;



public interface LoginRepository extends JpaRepository<Login , Integer> {

	public Optional<Login> findByUsername(String usename);
}
