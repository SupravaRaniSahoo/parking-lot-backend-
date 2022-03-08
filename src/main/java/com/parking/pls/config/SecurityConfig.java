package com.parking.pls.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Override
	   protected void configure(HttpSecurity http) throws Exception {
		
		http
		    
		     .csrf()
		     .disable()
		     .cors()
		     .disable()
		     .authorizeRequests()
		     .antMatchers("/authenticate").permitAll()
//		     .antMatchers(HttpMethod.GET,"parkingAPI/getSomeVehicle").permitAll()
		     .anyRequest().authenticated()
		     .and()
		     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	  }
	
	
	 @Override
	   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	      auth.userDetailsService(myUserDetailsService);
	   }
	 
	 @Bean
	 @Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	 	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
		return NoOpPasswordEncoder.getInstance();
	}
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(HttpMethod.GET,"/hello")
//                .antMatchers(HttpMethod.POST, "/authenticate");
//    }
	
//	@Override
//	protected void configure(HttpSecurity httpSecurity) throws Exception {
//		getHttp().authorizeRequests()
//        .antMatchers(HttpMethod.GET,"/hello").permitAll()
//        .anyRequest().fullyAuthenticated()
//        .and()
//        .httpBasic();
//       getHttp().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//       getHttp().cors();
//       getHttp().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//	}

	
}
