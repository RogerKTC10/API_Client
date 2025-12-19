package com.example.APIClient;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/hello") 
    public String sayHello() { 
        return "Bonjour Roger 🚀, ton API Spring Boot est bien en marche !"; 
    }
}
