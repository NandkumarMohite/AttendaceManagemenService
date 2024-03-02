package com.app.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "user")
@Table(name = "attendence")
public class Attendence extends CommonEntity{
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date singInTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date signOutTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date")
	private Date date;  
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
