package com.me.travel_backend.service.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.travel_backend.DTO.LoginInfoUser;
import com.me.travel_backend.entity.User;
import com.me.travel_backend.entity.enumerate.UserStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenService {
	
    private static final long EXPIRATION_TIME = 864000000; // 10 days
    private static final String SECRET = "123456";
    private static final String PREFIX_TOKEN = "Bearer";
    private static final String AUTHORIZATION = "Authorization";

    public static void addJWTTokenAndUserInfoToBody(HttpServletResponse response, User user) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(user.getUserName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        
       // convert user entity to user dto
        LoginInfoUser userDto = new LoginInfoUser(
                user.getStatus().equals(UserStatus.ACTIVE) ? JWT : null,
                user.getUserName(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().toString(),
                user.getStatus().toString());

        // convert object to json
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(userDto);

        // return json
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    public static Authentication parseTokenToUserInformation(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION);

        if (token == null) {
        	return null;
        }

        // parse the token
        String username = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(PREFIX_TOKEN, ""))
                .getBody()
                .getSubject();

        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList()) :
                null;
    }
}