package com.borgi.footappbackend.services.userservices;

import com.borgi.footappbackend.dto.userdto.UserAuthenticationRequest;
import com.borgi.footappbackend.dto.userdto.UserAuthenticationResponse;

public interface UserServiceInterface {


    UserAuthenticationResponse userAuthentication(UserAuthenticationRequest userAuthenticationRequest);
}
