package com.lcwd.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.entities.Hotel;
import com.lcwd.user.service.entities.Rating;
import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		return userRepository.save(user);
		
	}
	
	@Override
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	
	
	@Override
	public User getUser(int userId){
		//get user from database using repository
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + userId));
		
	/*	logger.info("abc: {}");
		//get rating of the above user from Rating Service
		Rating[] userRatings = restTemplate.getForObject("http://RATINGSERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("User Ratings: {}", userRatings);
		List<Rating> ratings = Arrays.stream(userRatings).toList();
		List<Rating> ratingList = ratings.stream().map(rating ->{
			//API call to hotel service to get the hotel
			ResponseEntity<Hotel> hotelEntity = restTemplate.getForEntity("http://HOTELSERVICE/hotels/"+rating.getHotelId(), Hotel.class);
		    Hotel hotel = hotelEntity.getBody();
		    logger.info("Response status code: {}", hotelEntity.getStatusCode());
		    
		    rating.setHotel(hotel);
			
			return rating; // Ensure to return the object
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList); */
		
		return user;
	}

}
