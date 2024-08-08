package com.app.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin("https://3.91.203.60:8080")
public class HealthcheckController {
    
    @GetMapping()
	public String signup() {    
        return "I am Alive Radhika";
	}
}
