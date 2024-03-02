package com.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.Converterdto;
import com.app.dto.UserDto;
import com.app.dto.UserDtoForAdmin;
import com.app.pojo.User;

import custom_exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
	
@Service
@Transactional
public class UserService  {

	@Autowired
	public UserDao userdao;
	

	@Autowired
	public ModelMapper mapper;

	
	
	public User addUser(UserDto user) {

		Converterdto dto = new Converterdto();
		
		User u = dto.toUser(user);
		User u1 = userdao.save(u);
		return u1;
	}

	
	public AuthResp Signin(AuthRequest req) {
		User user = userdao.findByEmailAndPassword(req.getEmail(),req.getPassword())
				.orElseThrow(() -> new ResourceNotFoundException("invalid email or Password....!"));
		System.out.println(user);
		return mapper.map(user, AuthResp.class);
	}
	
	public List<UserDtoForAdmin> getAllUser(){
		List<User> original = userdao.findByUserRole("User");
		List<UserDtoForAdmin> list = new ArrayList<>();
		list = Arrays.asList(mapper.map(original, UserDtoForAdmin[].class));
		
		return list;
		
	}

	
	
	
}
