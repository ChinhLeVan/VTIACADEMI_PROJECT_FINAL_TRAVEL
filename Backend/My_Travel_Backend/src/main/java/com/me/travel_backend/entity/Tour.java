package com.me.travel_backend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="`tour`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tour implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id",referencedColumnName = "id")
    private Location location;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "tourType_id")
    private TourType tourType;

    @Column(name = "vehicle", length = 255, nullable = false)
    private  String vehicle;
    
    @Column(name = "`numberOfTour`", length = 2,nullable = false)
    private int numberOfTour;
    
    @Column(name = "`tourName`", length = 255,nullable = false)
    private String tourName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "`dayStart`", nullable = false)
    private Date dayStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`dayEnd`", nullable = false)
    @CreationTimestamp
    private Date dayEnd;

    @Column(name = "`adultPrice`")
    private  float adultPrice;

    @Column(name = "`childPrice`")
    private  float childPrice;

    @Column(name = "`tourDescription`", nullable = false)
    private  String tourDescription;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "`createDate`", nullable = false)
    @CreationTimestamp
    private Date createDate;

    @Column(name = "`active`",nullable = false, columnDefinition = "boolean default false")
    private Boolean active = true;

    @OneToMany(mappedBy = "tour", fetch = FetchType.LAZY)
    private List<TourDetail> tourDetails;


    public Tour(Province province,
                Location location,
                TourType tourType,
                String vehicle,
                int numberOfTour,
                String tourName,
                Date dayStart,
                Date dayEnd,
                float adultPrice,
                float childPrice,
                String tourDescription,
                List<TourDetail> tourDetails) {
        this.province = province;
        this.location = location;
        this.tourType = tourType;
        this.vehicle = vehicle;
        this.numberOfTour = numberOfTour;
        this.tourName = tourName;
        this.dayStart = dayStart;
        this.dayEnd = dayEnd;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
        this.tourDescription = tourDescription;
        this.tourDetails = tourDetails;
    }

}

