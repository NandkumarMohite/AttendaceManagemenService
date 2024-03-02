package com.app.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class AuthRequest {

	@NotBlank(message = "Email must be supplied")
	private String email;
	
	@Pattern(regexp = "((?=.\\d)(?=.[a-z])(?=.[#@$]).{5,20})", message = "Invalid password format")
	private String password;
}
