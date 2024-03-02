package com.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.app.dao.AttendenceDao;
import com.app.dao.UserDao;
import com.app.pojo.Attendence;
import com.app.pojo.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RepeteCallService {
	
	@Autowired
	public AttendenceDao attendenceDao;
	
	@Autowired
	public UserDao userDao;

	@Autowired
	public ModelMapper mapper;
	

	 @Scheduled(cron = "0 * * * * ?") // Cron expression for midnight every day
	    public void checkAndAddSignInForYesterdayScheduled() {
		 System.out.println("i called my self after 1 minute");
	        // Assuming you have a list of user IDs, you can iterate over them and call the method
	        List<String> emailList = getAllUserIds(); // Implement this method to get all user IDs

	        for (String email : emailList) {
	            try {
	                checkAndAddSignInForYesterday(email);
	            } catch (ParseException e) {
	                // Handle the exception as needed
	                e.printStackTrace();
	            }
	        }
	    }

	  public  List<String> getAllUserIds() {
		  List<User> userList = userDao.findAll();
	        List<String> emailList = new ArrayList<>();
	        for (User user : userList) {
	            String email = user.getEmail();
	            emailList.add(email);
	        }
	        System.out.println(emailList);
	        return emailList;
	        }
	    // Other methods...

	    
	    public void checkAndAddSignInForYesterday(String email) throws ParseException {
	        User user = userDao.findByEmail(email);

	        // Get the current date without time
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Date currentDate = sdf.parse(sdf.format(new Date()));

	        // Get yesterday's date without time
	        Date yesterday = new Date(currentDate.getTime() - 1 * 24 * 60 * 60 * 1000);

	        // Check if there is already a sign-in entry for yesterday
	        List<Attendence> attendanceList = attendenceDao.findByUserAndDate(user, yesterday);

	        if (attendanceList.isEmpty()) {
	            // If no sign-in entry for yesterday, add a null sign-in entry
	        	Date NullValidDate = null;
		        Attendence newAttendance = new Attendence(NullValidDate,NullValidDate ,yesterday,user);
//		        newAttendance.setUser(user);
//		        newAttendance.setDate(dateWithoutTime);
//		        newAttendance.setSingInTime(null);
//		        newAttendance.setSignOutTime(null);
		        
		    
		        attendenceDao.save(newAttendance);

	          
	        }
	    }
}
