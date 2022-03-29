package com.me.travel_backend.repository;

import com.me.travel_backend.entity.TourDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ITourDetailRepository extends JpaRepository<TourDetail, Integer>, JpaSpecificationExecutor<TourDetail> {

    @Modifying
    @Transactional
    @Query(value = "CALL delete_TourDetail_By_Tour_id(:tourId);", nativeQuery = true)
   public void deleteTourDetailByTourId(@Param("tourId") int tourId);
}
