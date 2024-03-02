package com.app.dto;

import com.app.pojo.Attendence;
import com.app.pojo.User;

public class Converterdto {
     
	
	public User toUser(UserDto dto)
	{
		User entity = new User();
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setUserName(dto.getUserName());
		entity.setUserRole(dto.getUserRole());
		entity.setMobNo(dto.getMobNo());
		
		
		return entity;
		
	}
	
	
	public Attendence toAttendence(AttendenceDto dto)
	{
//		User entity = new User();
//		entity.setId(dto.getId());
//		entity.setEmail(dto.getEmail());
//		entity.setPassword(dto.getPassword());
//		entity.setUserName(dto.getUserName());
//		entity.setUserRole(dto.getUserRole());
//		entity.setMobNo(dto.getMobNo());
		
		Attendence entity = new Attendence();
		entity.setDate(dto.getDate());
		entity.setUser(dto.getUser());
		entity.setSignOutTime(dto.getSignOutTime());
		entity.setSingInTime(dto.getSingInTime());
		
		
		return entity;
		
	}
	
	public User toUserForAdmin(UserDto dto)
	{
		User entity = new User();
		
		entity.setUserName(dto.getUserName());
		return entity;
		
	}
}
