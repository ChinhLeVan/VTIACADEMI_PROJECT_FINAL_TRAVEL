package com.me.travel_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "location")
@NoArgsConstructor
public class Location implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private  int id;

    @Column(name = "location", length = 30, nullable = false)
    private String location;

    @Column(name = "description", nullable = false)
    private String description;

    public Location(String location, String description) {
        this.location = location;
        this.description = description;
    }
}
