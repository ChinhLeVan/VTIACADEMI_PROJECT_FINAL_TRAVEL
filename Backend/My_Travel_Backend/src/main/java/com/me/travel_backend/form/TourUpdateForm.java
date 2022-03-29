package com.me.travel_backend.form;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.me.travel_backend.DTO.LocationDTO;
import com.me.travel_backend.DTO.ProvinceDTO;
import com.me.travel_backend.DTO.TourTypeDTO;
import com.me.travel_backend.entity.TourDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourUpdateForm {
    private int tourID;
    private TourTypeDTO tourType;
    private  String vehicle;
    private int numberOfTour;
    private String tourName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayEnd;
    private  float adultPrice;
    private  float childPrice;
    private  String tourDescription;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TourDetail> tourDetails;
    private Boolean active;


}
