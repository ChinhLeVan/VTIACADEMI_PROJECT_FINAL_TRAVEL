package com.me.travel_backend.controller;

import com.me.travel_backend.DTO.LogActiveDTO;

import com.me.travel_backend.entity.LogActive;
import com.me.travel_backend.entity.Search.LogActiveSearch;

import com.me.travel_backend.entity.filter.LogActiveFilter;

import com.me.travel_backend.mapper.LogActiveMapper;

import com.me.travel_backend.service.ILogActiveService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.function.Function;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/actives")
@Validated
public class LogActiveController {

    @Autowired
    private ILogActiveService logActiveService;
    private LogActiveMapper logActiveMapper = new LogActiveMapper();

    @GetMapping()
    public ResponseEntity<?> getAllTours(Pageable pageable, LogActiveFilter logActiveFilter, LogActiveSearch logActiveSearch){
        Page<LogActive> actives = logActiveService.getAllActives(pageable, logActiveFilter, logActiveSearch);
        //convert entity to DTOs
        Page<LogActiveDTO> dtoPage = actives.map(new Function<LogActive, LogActiveDTO>() {
            @Override
            public LogActiveDTO apply(LogActive logActive) {
                LogActiveDTO activeDTOs = logActiveMapper.toDTO(logActive);
                return activeDTOs;
            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }
}
