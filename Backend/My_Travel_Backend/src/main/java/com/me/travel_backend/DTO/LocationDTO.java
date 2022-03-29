package com.me.travel_backend.DTO;

import com.me.travel_backend.entity.Location;
import com.me.travel_backend.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LocationDTO {
    private String location;

    private String description;

    public Location toEntity(){
        return new Location(location, description);
    }
}
