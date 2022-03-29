package com.me.travel_backend.DTO;


import com.me.travel_backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserDTO {

    private  String firstName;
    private String lastName;
    private  String email;
    private String identityCard;
    private  String phoneNumber;
    private  String address;
    private  String userName;
    private  String password;

    public User toEntity(){
        return new User(firstName, lastName, email, identityCard, phoneNumber, address, userName, password );
    }
}
