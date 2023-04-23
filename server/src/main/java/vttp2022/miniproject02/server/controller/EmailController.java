package vttp2022.miniproject02.server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vttp2022.miniproject02.server.service.EmailService;
import vttp2022.miniproject02.server.user.User;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailSvc;
    
    @PostMapping("/welcome")
    public ResponseEntity<String> sendWelcomeEmail(@RequestBody User user) {

        System.out.println("sendWelcomeEmail API called");
        System.out.println(user.getEmail());
        System.out.println(user.getName());

        String toEmail = user.getEmail();
        String subject = "Welcome | Thank you for subscribing";
        String message = "Hello " + user.getName() + ",\n\n" 
                                + "Thank you for subscribing!\n\n"
                                + "You may now use your email address as your username along with the created password to sign in. "
                                + "Start exploring great game deals and never have to pay full price again!\n\n"
                                + "Best Regards,\n"
                                + "cheapo Team";

        emailSvc.sendWelcomeEmail(toEmail, subject, message);
        // Create a map to hold the data
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "Email sent successfully");

        // Convert the map to JSON using Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = objectMapper.writeValueAsString(responseMap);
        } catch (JsonProcessingException e) {
            // Handle the exception
            jsonResponse = "{\"error\":\"Failed to create JSON response\"}";
        }
        return ResponseEntity.ok(jsonResponse);
    }

}
