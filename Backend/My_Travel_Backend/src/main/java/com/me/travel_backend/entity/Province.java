package com.me.travel_backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "province")
@NoArgsConstructor
@AllArgsConstructor
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private  int id;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
    private List<Tour> tours;

    @Column(name = "nameProvince", length = 30, nullable = false)
    private String nameProvince;

    public Province(String nameProvince) {
        this.nameProvince = nameProvince;
    }
}
