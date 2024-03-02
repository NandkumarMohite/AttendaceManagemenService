package com.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AttendenceDao;
import com.app.dao.UserDao;
import com.app.dto.AttendenceDto;
import com.app.dto.Converterdto;
import com.app.dto.UserDto;
import com.app.dto.UserDtoForAdmin;
import com.app.pojo.Attendence;
import com.app.pojo.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AttendenceService {
    
	@Autowired
	public AttendenceDao attendenceDao;
	
	@Autowired
	public UserDao userDao;

	@Autowired
	public ModelMapper mapper;
	
	
	public Attendence addAttendenceSignInTime(int id) throws ParseException {
        User u = userDao.findById(id);
        
		Converterdto dto = new Converterdto();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//create new date for comparision with all date in orders 
		Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
		System.out.println(dateWithoutTime);
		
		long longTime = System.currentTimeMillis();
		Date singInTime = new Date(longTime);
		
		AttendenceDto d = new AttendenceDto();
		d.setDate(dateWithoutTime);
		d.setSingInTime(singInTime);
		d.setUser(u);
		d.setSignOutTime(null);
		Attendence at = dto.toAttendence(d);
		Attendence aaa = attendenceDao.save(at);
		
	    return aaa;
	}

	
	public Attendence addAttendenceSignOutInTime(int id) throws ParseException {
	    User u = userDao.findById(id);
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
	    System.out.println(dateWithoutTime);
	    
	    Date currentDate = new Date();
	    System.out.println(currentDate);
	    
	    List<Attendence> attendanceList = attendenceDao.findByUserAndDate(u, dateWithoutTime);
	    
	    
	    if (attendanceList.isEmpty()) {
	    	
	        // Save the new attendance record  if attendance is not present
	        Attendence newAttendance = new Attendence();
	        newAttendance.setUser(u);
	        newAttendance.setDate(dateWithoutTime);
	        newAttendance.setSingInTime(currentDate);
	        newAttendance.setSignOutTime(null);
	        
	    
	        return attendenceDao.save(newAttendance);
	    } else {
	        // find the first attendance record without a sign-out time / update its sign-out time =============
	        for (Attendence attendance : attendanceList) {
	            if (attendance.getSignOutTime() == null) {
	                attendance.setSignOutTime(currentDate);
	                
	                // so ..................this is updated attendance i
	                return attendenceDao.save(attendance);
	            }
	        }
	        
	        // If no attendance record without a sign-out time is found, create a new one attendace foe user
	        Attendence newAttendance = new Attendence();
	        newAttendance.setUser(u);
	        newAttendance.setDate(dateWithoutTime);
	        newAttendance.setSingInTime(currentDate);
	        newAttendance.setSignOutTime(null);
	        
	        // Save the new attendance record
	        return attendenceDao.save(newAttendance);
	    }
	}

	
	public List<Attendence> findAllAtendenceById(int id){
		  User u = userDao.findById(id);
		  System.out.println("uuuuu----"+u);
//		  List<Attendence> at = attendenceDao.findByUser(u);
//		  List<AttendenceDto> list = new ArrayList<>();
//		  list = Arrays.asList(mapper.map(at, AttendenceDto[].class));
//		  return list;
		  List<Attendence> at = u.getAttendence();
		  System.out.println("sssss-----"+at.size());
		  at.forEach(x->System.out.println(x));
	        List<Attendence> sortedList = at.stream()
	                .sorted(Comparator.comparing(Attendence::getDate))
	                .collect(Collectors.toList());
//		  List<AttendenceDto> list = new ArrayList<>();
//		  list = Arrays.asList(mapper.map(at, AttendenceDto[].class));
		  return sortedList;
	}
	public Attendence getLastRecordByUserId(int  id) {
        return attendenceDao.findTopByUserIdOrderByidDesc(id);
    }
}
