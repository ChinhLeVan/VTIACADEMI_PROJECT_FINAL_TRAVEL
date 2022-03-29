package com.me.travel_backend.DTO;

import com.me.travel_backend.entity.Tour;
import com.me.travel_backend.entity.TourDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TourDetailDTO {

    private  String title;

    private String dayDetail;

    private String dayDetailDescription;

    private  String image;

}
