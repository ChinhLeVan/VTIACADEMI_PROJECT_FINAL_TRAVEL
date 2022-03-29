package com.me.travel_backend.entity;

import com.me.travel_backend.entity.enumerate.UserRole;
import com.me.travel_backend.entity.enumerate.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="`User`")
@Getter
@Setter
@NoArgsConstructor

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "`firstName`" ,length = 50,nullable = false)
    private String firstName;

    @Column(name = "`lastName`", length = 50,nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "identityCard", nullable = false)
    private  String identityCard;
    
    @Column(name = "`phoneNumber`", length = 11,nullable = false)
    private String phoneNumber;
    
    @Column(name = "`address`", length = 255,nullable = false)
    private String address;
    
    @Column(name = "`username`", length = 255,nullable = false)
    private String userName;
    
    @Column(name = "`password`", length = 255,nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "`role`", length = 255,nullable = false)
    private UserRole role = UserRole.CUSTOMER;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "`status`", nullable = false)
    private UserStatus status = UserStatus.NOT_ACTIVE;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TourOrder> tourOrders;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LogActive> logActives;

    public User(String firstName, String lastName, String email, String identityCard, String phoneNumber, String address, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.identityCard = identityCard;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.userName = userName;
        this.password = password;
    }
}

