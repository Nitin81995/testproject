package com.lcwd.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
	
	@GetMapping("/{userId}")
	@RateLimiter(name="userRateLimiter", fallbackMethod="ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable int userId){
		User user1 = userService.getUser(userId);
		return ResponseEntity.ok(user1);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> user1 = userService.getAllUser();
		return ResponseEntity.ok(user1);
	}
	
	public ResponseEntity<User> ratingHotelFallback(){
		User user = User.builder().email("testuser@gmail.com")
				.name("test")
				.about("This user is created beacause some service is down")
				.userId(141234)
				.build();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
