package com.parking.pls.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;
    

//    private Logger logger = LoggerFactory.getLogger("JwtFilter.class");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        final String authHeader = request.getHeader("Authorization");
//
//        String userName = null;
//
//        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")){
//            userName = jwtService.extractClaim(authHeader.substring(7), Claims::getSubject);
//            logger.info("*********userName: " + userName);
//        }
//        if (Objects.nonNull(userName) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//            if (jwtService.validateToken(authHeader.substring(7), userDetails)){
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        }
//        filterChain.doFilter(request, response);
    	
    	
    	String requestTokenHeader = request.getHeader("Authorization");
    	String username = null;
    	String jwtToken = null;
    	
    	if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
    		jwtToken = requestTokenHeader.substring(7);
    		
    		try{
    			username = this.jwtService.extractUsername(jwtToken);
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    		UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
    	   if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
    		   
    		   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    		   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    		   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    	   }else {
    		   System.out.println("Token is not validated...");
    	   }
    	   
    	}
    	
    	 filterChain.doFilter(request, response);
    	
    	
    	
    }
}