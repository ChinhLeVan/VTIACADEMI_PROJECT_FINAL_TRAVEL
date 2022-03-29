package com.me.travel_backend.service.impl;

import com.me.travel_backend.DTO.*;
import com.me.travel_backend.entity.*;
import com.me.travel_backend.entity.Search.TourSearch;
import com.me.travel_backend.entity.filter.TourFilter;
import com.me.travel_backend.mapper.TourMapper;
import com.me.travel_backend.repository.*;
import com.me.travel_backend.service.ITourService;
import com.me.travel_backend.specification.TourSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class TourService implements ITourService {

    @Autowired
    private ITourRepository tourRepository;

    private TourMapper tourMapper = new TourMapper();

    @Autowired
    private IProvinceRepository provinceRepository;

    @Autowired
    private ILocationRepository locationRepository;

    @Autowired
    private ITourTypeRepository tourTypeRepository;

    @Autowired
    private ITourDetailRepository tourDetailRepository;

    public Page<Tour> getAllTours(Pageable pageable, TourFilter tourFilter, TourSearch tourSearch) {
        TourSpecificationBuilder specificationBuilder = new TourSpecificationBuilder(tourFilter, tourSearch);
        return tourRepository.findAll(specificationBuilder.build(),pageable);
    }

    @Override
    public Tour getTourByID(int id) {
        return tourRepository.getById(id);
    }

    @Override
    @Transactional
    public void createTour(TourDTO tourCreateForm) {
        Tour tour = tourMapper.toEntity(tourCreateForm);
        //save Province
        Province province = tour.getProvince();
        if(provinceRepository.findByNameProvince(province.getNameProvince()) == null){
            provinceRepository.save(province);
        }
        tour.setProvince(provinceRepository.findByNameProvince(province.getNameProvince()));

//        //save Location
        Location location = tour.getLocation();
        if(locationRepository.findByLocation(location.getLocation()) == null){
            locationRepository.save(location);
        }
        tour.setLocation(locationRepository.findByLocation(location.getLocation()));

//        //save tour type
        TourType tourType = tour.getTourType();
        if(tourTypeRepository.findByTypeName(tourType.getTypeName()) == null){
            tourTypeRepository.save(tourType);
        }
        tour.setTourType(tourTypeRepository.findByTypeName(tourType.getTypeName()));

        // save tour
        if(tourRepository.findTourByTourName(tour.getTourName()) == null) {
            tourRepository.save(tour);
            // save tour detail
            for (TourDetail tourDetail: tour.getTourDetails()) {
                tourDetail.setTour(tour);
                tourDetailRepository.save(tourDetail);
            }
        }

    }

    @Transactional
    @Override
    public void updateTour(int id, TourDTO tourUpdateForm) {
        Tour tour = tourRepository.getById(id);
        List<TourDetail> tourDetails = tour.getTourDetails();

        //update Province
        ProvinceDTO provinceDTO = tourUpdateForm.getProvince();
        if(provinceRepository.findByNameProvince(provinceDTO.getNameProvince()) == null){
            provinceRepository.save(provinceDTO.toEntity());
        }
        tour.setProvince(provinceRepository.findByNameProvince(provinceDTO.getNameProvince()));

        //Update Location
        LocationDTO locationDTO = tourUpdateForm.getLocation();
        if(locationRepository.findByLocation(locationDTO.getLocation()) == null){
            locationRepository.save(locationDTO.toEntity());
        }
        tour.setLocation(locationRepository.findByLocation(locationDTO.getLocation()));

        //Update tour type
        TourTypeDTO tourTypeDTO = tourUpdateForm.getTourType();
        if(tourTypeRepository.findByTypeName(tourTypeDTO.getTypeName()) == null){
            tourTypeRepository.save(tourTypeDTO.toEntity());
        }
        tour.setTourType(tourTypeRepository.findByTypeName(tourTypeDTO.getTypeName()));

        tour.setVehicle(tourUpdateForm.getVehicle());
        tour.setNumberOfTour(tourUpdateForm.getNumberOfTour());
        tour.setTourName(tourUpdateForm.getTourName());
        tour.setDayStart(tourUpdateForm.getDayStart());
        tour.setDayEnd(tourUpdateForm.getDayEnd());
        tour.setAdultPrice(tourUpdateForm.getAdultPrice());
        tour.setChildPrice(tourUpdateForm.getChildPrice());
        tour.setTourDescription(tourUpdateForm.getTourDescription());

        List<TourDetailDTO> tourDetailDTOs = tourUpdateForm.getTourDetailDTOs();
        for (int i = 0; i < tourUpdateForm.getTourDetailDTOs().size(); i++) {
            tourDetailRepository.save(setDataForTourDetailFromTourDetailDTO(tourDetails.get(i), tourDetailDTOs.get(i)));
        }
        tourRepository.save(tour);
    }

    private TourDetail setDataForTourDetailFromTourDetailDTO(TourDetail tourDetail, TourDetailDTO tourDetailDTO){
        tourDetail.setTitle(tourDetailDTO.getTitle());
        tourDetail.setDayDetailDescription(tourDetailDTO.getDayDetailDescription());
        tourDetail.setImage(tourDetailDTO.getImage());
        return tourDetail;
    }
    @Override
    @Transactional
    public void deleteTourById(int tourId) {
        tourDetailRepository.deleteTourDetailByTourId(tourId);
        tourRepository.deleteById(tourId);
    }
}
