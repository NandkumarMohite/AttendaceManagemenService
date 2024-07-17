package com.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AttendenceDto;
import com.app.dto.AttendenceResp;
import com.app.dto.AuthRequest;
import com.app.dto.AuthResp;
import com.app.dto.Converterdto;
import com.app.dto.Response;
import com.app.dto.UserDto;
import com.app.pojo.Attendence;
import com.app.pojo.User;
import com.app.service.AttendenceService;
import com.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/attendence")
// @CrossOrigin("http://localhost:3000")
public class AttendenceController {

	@Autowired
	public AttendenceService attendenceService;
	
	@Autowired
	public ModelMapper mapper;
	
	Converterdto con;
	
	@GetMapping("/signin/{id}")
	public AttendenceResp signup(@PathVariable("id") int id) throws ParseException 
	{    System.out.print("in SignIn");
		AttendenceResp resp=new AttendenceResp();
		
	    Attendence at  = attendenceService.addAttendenceSignInTime(id);
        if(at != null) {
		    resp.setMsg("User SigIn succesfully ");
			return  resp;	
		  }else {
		    resp.setMsg("User SignIn failded ");
		    return resp;
		  }	
	}
	
	@GetMapping("/signout/{id}")
	public AttendenceResp signout(@PathVariable("id") int id) throws ParseException {
		System.out.print("in SignOUT");
		AttendenceResp resp=new AttendenceResp();
		    Attendence at = attendenceService.addAttendenceSignOutInTime(id);
		    if(at != null) {
		   	
		    resp.setMsg("User SignOUT succesfully ");
			return  resp;	
		  }else {
		    resp.setMsg("User SignOUT failded ");
		    return resp;
		  }
	}

	@GetMapping("/allAttendence/{id}")
	public ResponseEntity<?> getAllUser(@PathVariable("id") int id){
		  List<Attendence> list = attendenceService.findAllAtendenceById(id);
		  System.out.println("inside Attendences Controller : "+list.toString());
		  return Response.success(list);
	}
	
	 @GetMapping("/lastRecord/{id}")
	    public Attendence getLastRecordByUserId(@PathVariable("id") int  id) {
	        return attendenceService.getLastRecordByUserId(id);
	    }
	
	
}
