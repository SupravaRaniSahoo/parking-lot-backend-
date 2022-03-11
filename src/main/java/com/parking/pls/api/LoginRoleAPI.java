package com.parking.pls.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.pls.entity.Login;
import com.parking.pls.entity.Role;
import com.parking.pls.repository.LoginRepository;
import com.parking.pls.repository.RoleRepository;


@RestController
@CrossOrigin
@RequestMapping(value="/login")
public class LoginRoleAPI {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping(value="/getLogin")
	public List<Login> getLoginDetails(){
		return (List<Login>) loginRepository.findAll();
	}
	
	@GetMapping(value="/getRole")
	public List<Role> getRoleDetails(){
		return (List<Role>) roleRepository.findAll();
	}
	

}
