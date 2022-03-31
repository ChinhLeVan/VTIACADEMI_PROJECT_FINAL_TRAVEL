package com.me.travel_backend.controller;

import com.me.travel_backend.DTO.TourDTO;
import com.me.travel_backend.entity.Search.TourSearch;
import com.me.travel_backend.entity.Tour;
import com.me.travel_backend.entity.filter.TourFilter;
import com.me.travel_backend.mapper.TourMapper;
import com.me.travel_backend.service.ITourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.function.Function;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/tours")
@Validated
public class TourController {

    @Autowired
    private ITourService tourService;
    private TourMapper tourMapper = new TourMapper();

    @GetMapping()
    public ResponseEntity<?> getAllTours(Pageable pageable, TourFilter tourFilter, TourSearch tourSearch){
        Page<Tour> tours = tourService.getAllTours(pageable, tourFilter, tourSearch);
        //convert entity to DTOs
        Page<TourDTO> dtoPage = tours.map(new Function<Tour, TourDTO>() {
            @Override
            public TourDTO apply(Tour tour) {
                TourDTO tourDTOs = tourMapper.toDTO(tour);
                return tourDTOs;
            }
        });
        return new ResponseEntity<>(dtoPage, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTourByID(@PathVariable(name = "id") int id) {
        Tour tour = tourService.getTourByID(id);
        TourDTO tourDTO = tourMapper.toDTO(tour);
        return new ResponseEntity<>(tourDTO, HttpStatus.OK);
    }

    @PostMapping("/admin/create-tour")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createTour(@RequestBody TourDTO tourCreateForm){
        tourService.createTour(tourCreateForm);
        return new ResponseEntity<String>("Created success !", HttpStatus.OK);
    }

    @PutMapping(value = "/admin/update/{id}")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> updateTour(@PathVariable(name = "id") short id, @RequestBody  TourDTO tourUpdateForm) {
        tourService.updateTour(id, tourUpdateForm);
        return new ResponseEntity<String>("Update successfully!", HttpStatus.OK);
    }


    @DeleteMapping("/admin/delete/{id}")
//    @Secured("User")
    public ResponseEntity<?> deleteTour(@PathVariable(name = "id") int id){
        tourService.deleteTourById(id);
        return new ResponseEntity<String>("Deleted success !", HttpStatus.OK);
    }
}
