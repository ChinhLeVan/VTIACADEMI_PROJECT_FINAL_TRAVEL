package com.me.travel_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@AllArgsConstructor
public class LoginInfoUser {
    private String token;

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

    private String role;

    private String status;

}
