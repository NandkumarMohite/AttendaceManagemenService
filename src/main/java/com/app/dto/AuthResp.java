package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthResp {
		
		private Long id;
		private String email;
		private String userName;
		private String userRole;
		private long mobNo;

		
	}
