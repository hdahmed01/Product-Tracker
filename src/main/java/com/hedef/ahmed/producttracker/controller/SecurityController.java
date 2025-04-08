package com.hedef.ahmed.producttracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/notAutorized")
    public String notAutorized() {
        return "notAutorized";
    }
}
