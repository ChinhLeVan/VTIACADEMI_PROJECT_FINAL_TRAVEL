package com.me.travel_backend.repository;

import com.me.travel_backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



public interface ITourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {
    public Tour findTourByTourName(String tourName);

}
