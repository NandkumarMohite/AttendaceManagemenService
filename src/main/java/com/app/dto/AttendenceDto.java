package com.app.dto;

import java.util.Date;

import com.app.pojo.User;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendenceDto {
	
	   
		private Date singInTime;
		
		private Date signOutTime;
		
		private Date date;
		
		private User user;
		
		
	}

