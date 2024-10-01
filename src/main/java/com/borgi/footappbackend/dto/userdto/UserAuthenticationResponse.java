package com.borgi.footappbackend.dto.userdto;

import com.borgi.footappbackend.entities.user.TokenType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("access_token_expiry")
    private int accessTokenExpiry;

    @JsonProperty("token_type")
    private TokenType tokenType;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("user_full_name")

    private String userFullName;


}
