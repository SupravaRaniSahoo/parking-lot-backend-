package com.parking.pls.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.parking.pls.dto.LoginDTO;
import com.parking.pls.entity.Login;
import com.parking.pls.repository.LoginRepository;

@RestController
@CrossOrigin(origins = "*")
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
//	@Autowired
//	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JwtService jwtService;

	@RequestMapping({"/hello"})
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping({"/info"})
	public String info() {
		return "Info is fully up and running..";
	}
	
	@RequestMapping(value="/getUser", method=RequestMethod.GET)
	public List<Login> getLoginDetails(){
		return (List<Login>) loginRepository.findAll();
	}
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
        	e.printStackTrace();
            throw new Exception("Incorrect Username or password...", e);
        }
       
		UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtService.generateToken(userDetails);
        System.out.println("JWT"+ jwt);
        
        return ResponseEntity.ok(new JswResponse(jwt));

	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody LoginDTO user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}
	

}
