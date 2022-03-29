package com.me.travel_backend.DTO;

import com.me.travel_backend.entity.Province;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProvinceDTO {
    private String nameProvince;

    public Province toEntity(){
        return new Province(nameProvince);
    }
}
