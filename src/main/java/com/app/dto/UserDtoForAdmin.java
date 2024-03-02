package com.app.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
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
	@Component
	public class UserDtoForAdmin {
		
		
		private Long id;
		
		@Email
		private String email;

		private String userName;
		
		private String userRole;
		
		private long mobNo;
		
	}
