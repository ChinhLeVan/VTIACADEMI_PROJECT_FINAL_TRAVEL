package com.me.travel_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="`tourReview`")
@Getter
@Setter
@NoArgsConstructor

public class TourReview implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_Id",referencedColumnName = "id")
    private Tour tour;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id",referencedColumnName = "id")
    private  User user;

    @Column(name = "rate")
    private  int rate;

    @Column(name = "comment")
    private  String comment;

    @Temporal(TemporalType.TIMESTAMP)
    //@CreationTimestamp
    @Column(name = "`reviewDate`", nullable = false)
    private Date reviewDate;

    @Column(name = "`active`",nullable = false, columnDefinition = "boolean default false")
    private Boolean active;

}

