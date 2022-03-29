package com.me.travel_backend.service;

import com.me.travel_backend.DTO.TourDTO;
import com.me.travel_backend.entity.Search.TourSearch;
import com.me.travel_backend.entity.Tour;
import com.me.travel_backend.entity.filter.TourFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITourService  {

    Page<Tour> getAllTours(Pageable pageable, TourFilter tourFilter, TourSearch tourSearch);

    void createTour(TourDTO tourCreateForm);

    void deleteTourById(int tourId);

    Tour getTourByID(int id);

    void updateTour(int id, TourDTO tourUpdateForm);
}
