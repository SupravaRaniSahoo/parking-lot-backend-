package com.parking.pls.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.parking.pls.entity.ERole;
import com.parking.pls.entity.Role;


public interface RoleRepository extends CrudRepository<Role , Integer> {

//	Optional<Role> findByName(ERole name);
}
