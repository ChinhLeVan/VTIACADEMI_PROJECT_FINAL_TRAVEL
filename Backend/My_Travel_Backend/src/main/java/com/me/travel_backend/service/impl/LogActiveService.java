package com.me.travel_backend.service.impl;

import com.me.travel_backend.DTO.*;
import com.me.travel_backend.entity.*;
import com.me.travel_backend.entity.Search.LogActiveSearch;
import com.me.travel_backend.entity.Search.TourSearch;
import com.me.travel_backend.entity.filter.LogActiveFilter;
import com.me.travel_backend.entity.filter.TourFilter;
import com.me.travel_backend.mapper.TourMapper;
import com.me.travel_backend.repository.*;
import com.me.travel_backend.service.ILogActiveService;
import com.me.travel_backend.service.ITourService;
import com.me.travel_backend.specification.LogActiveSpecificationBuilder;
import com.me.travel_backend.specification.TourSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class LogActiveService implements ILogActiveService {

    @Autowired
    ILogActiveRepository logActiveRepository;

    @Override
    public Page<LogActive> getAllActives(Pageable pageable, LogActiveFilter logActiveFilter, LogActiveSearch logActiveSearch ) {
        LogActiveSpecificationBuilder specificationBuilder = new LogActiveSpecificationBuilder(logActiveFilter, logActiveSearch);
        return logActiveRepository.findAll(specificationBuilder.build(),pageable);
    }

    @Override
    public void createActives(TourDTO tourCreateForm) {
    }
}
