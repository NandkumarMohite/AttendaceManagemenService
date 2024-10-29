package com.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimezoneController {

    private static String timezone = "UTC";

    @GetMapping("/timezone")
    public String getTimezone() {
        return timezone;
    }

    @PostMapping("/timezone")
    public String setTimezone(@RequestParam String newTimezone) {
        timezone = newTimezone;
        return "Timezone updated to: " + timezone;
    }
}
