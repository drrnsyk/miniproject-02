package vttp2022.miniproject02.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import vttp2022.miniproject02.server.model.AuthenticationRequest;
import vttp2022.miniproject02.server.model.AuthenticationResponse;
import vttp2022.miniproject02.server.model.RegisterRequest;
import vttp2022.miniproject02.server.service.AuthenticationService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private final AuthenticationService authenticationSvc;
    
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        String response = authenticationSvc.register(request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/authenticate")
    @ResponseBody
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationSvc.authenticate(request));
    }

}
