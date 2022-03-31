package com.me.travel_backend.service;

import com.me.travel_backend.DTO.TourDTO;
import com.me.travel_backend.entity.LogActive;
import com.me.travel_backend.entity.Search.LogActiveSearch;
import com.me.travel_backend.entity.filter.LogActiveFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ILogActiveService {

    Page<LogActive> getAllActives(Pageable pageable, LogActiveFilter logActiveFilter, LogActiveSearch logActiveSearch);

    void createActives(TourDTO tourCreateForm);

}
