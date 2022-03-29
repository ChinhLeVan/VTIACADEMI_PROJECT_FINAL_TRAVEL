package com.me.travel_backend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tourType")
@NoArgsConstructor
public class TourType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private  int id;

    @Column(name = "typeName", length = 30, nullable = false)
    private String typeName;

    public TourType(String typeName) {
        this.typeName = typeName;
    }
}
