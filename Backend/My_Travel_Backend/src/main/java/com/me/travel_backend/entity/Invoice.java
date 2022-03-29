package com.me.travel_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="`invoice`")
@Getter
@Setter
@NoArgsConstructor

public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tourOrder_id",referencedColumnName = "id")
    private TourOrder tourOrder;

    @Column(name = "note", length = 200)
    private  String note;

    @Temporal(TemporalType.TIMESTAMP)
    //@CreationTimestamp
    @Column(name = "`settlementDate`", nullable = false)
    private Date settlementDate;

}

