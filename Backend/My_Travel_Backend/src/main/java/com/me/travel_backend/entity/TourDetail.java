package com.me.travel_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="`tourDetail`")
@Getter
@Setter
@NoArgsConstructor

public class TourDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_Id")
    private Tour tour;

    @Column(name = "title", nullable = false)
    private  String title;
    
    @Column(name = "`dayDetail`", length = 1,nullable = false)
    private String dayDetail;
    
    @Column(name = "`dayDetailDescription`",nullable = false)
    private String dayDetailDescription;

    @Column(name = "`image`")
    private  String image;


    @Column(name = "`active`",nullable = false, columnDefinition = "boolean default false")
    private Boolean active = true;

    public TourDetail(String title, String dayDetail, String dayDetailDescription, String image) {
        this.title = title;
        this.dayDetail = dayDetail;
        this.dayDetailDescription = dayDetailDescription;
        this.image = image;
    }

}

