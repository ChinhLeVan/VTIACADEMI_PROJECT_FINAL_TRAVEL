package com.me.travel_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="`tourOrder`")
@Getter
@Setter
@NoArgsConstructor

public class TourOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id", referencedColumnName = "id")
    private Tour tour;

    @Temporal(TemporalType.TIMESTAMP)
    //@CreationTimestamp
    @Column(name = "`dateOder`", nullable = false)
    @Past
    private Date dateOder;

    @Column(name = "adultNumber", nullable = false)
    private  int adultNumber;
    
    @Column(name = "`childNumber`" ,nullable = false)
    private int childNumber;
    
    @Column(name = "`orderNote`")
    private String orderNote;

    @Temporal(TemporalType.TIMESTAMP)
    //@CreationTimestamp
    @Column(name = "`dayStart`", nullable = false)
    private Date dayStart;

    @Column(name = "`totalAmount`")
    private  float totalAmount;

    @Column(name = "`active`",nullable = false, columnDefinition = "boolean default false")
    private Boolean active;

    @OneToOne(mappedBy = "tourOrder")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}

