package com.me.travel_backend.DTO;

import com.me.travel_backend.entity.Province;
import com.me.travel_backend.entity.TourType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TourTypeDTO {
    private String typeName;

    public TourType toEntity(){
        return new TourType(typeName);
    }
}
