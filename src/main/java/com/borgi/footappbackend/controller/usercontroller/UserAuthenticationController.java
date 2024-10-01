package com.borgi.footappbackend.controller.usercontroller;

import com.borgi.footappbackend.dto.userdto.UserAuthenticationRequest;
import com.borgi.footappbackend.dto.userdto.UserAuthenticationResponse;
import com.borgi.footappbackend.services.userservices.UserServiceInterface;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Authentication/User")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class UserAuthenticationController {

    private final UserServiceInterface userServiceInterface;

    @PostMapping("/userAuthentication")
    public UserAuthenticationResponse userAuthentication(@RequestBody UserAuthenticationRequest userAuthenticationRequest) {
    return userServiceInterface.userAuthentication(userAuthenticationRequest);
    }
    }
