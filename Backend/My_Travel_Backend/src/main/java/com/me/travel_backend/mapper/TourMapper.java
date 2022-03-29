package com.me.travel_backend.mapper;

import com.google.common.annotations.Beta;
import com.me.travel_backend.DTO.*;
import com.me.travel_backend.entity.Province;
import com.me.travel_backend.entity.Tour;
import com.me.travel_backend.entity.TourDetail;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.stream.Collectors;

@NoArgsConstructor

public class TourMapper {
    public TourDTO toDTO(Tour tour){
        return new TourDTO(
                tour.getId(),
                new ProvinceDTO(tour.getProvince().getNameProvince()),
                new LocationDTO(tour.getLocation().getLocation(), tour.getLocation().getDescription()),
                new TourTypeDTO(tour.getTourType().getTypeName()),
                tour.getVehicle(),
                tour.getNumberOfTour(),
                tour.getTourName(),
                tour.getDayStart(),
                tour.getDayEnd(),
                tour.getAdultPrice(),
                tour.getChildPrice(),
                tour.getTourDescription(),
                tour.getTourDetails().stream()
                                .map(tourDetail -> new TourDetailDTO(tourDetail.getTitle(), tourDetail.getDayDetail(),
                                        tourDetail.getDayDetailDescription(), tourDetail.getImage()))
                                .collect(Collectors.toList()),
                tour.getActive());
    }

    public Tour toEntity(TourDTO tourDTO){
        return new Tour(
                tourDTO.getProvince().toEntity(),
                tourDTO.getLocation().toEntity(),
                tourDTO.getTourType().toEntity(),
                tourDTO.getVehicle(),
                tourDTO.getNumberOfTour(),
                tourDTO.getTourName(),
                tourDTO.getDayStart(),
                tourDTO.getDayEnd(),
                tourDTO.getAdultPrice(),
                tourDTO.getChildPrice(),
                tourDTO.getTourDescription(),
                tourDTO.getTourDetailDTOs().stream()
                        .map(tourDetailDTO -> new TourDetail(tourDetailDTO.getTitle(), tourDetailDTO.getDayDetail(),
                                tourDetailDTO.getDayDetailDescription(), tourDetailDTO.getImage()))
                        .collect(Collectors.toList())
                );
    }
}
