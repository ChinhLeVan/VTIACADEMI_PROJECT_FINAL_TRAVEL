package com.me.travel_backend.repository;

import com.me.travel_backend.entity.Province;
import com.me.travel_backend.entity.TourType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ITourTypeRepository extends JpaRepository<TourType, Integer>, JpaSpecificationExecutor<TourType> {

    TourType findByTypeName(String typename);
}
