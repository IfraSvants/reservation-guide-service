package com.guidereservationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guidereservationservice.models.GuideReservationEntity;

@Repository
public interface GuideReservationDao extends JpaRepository<GuideReservationEntity, Integer>  {

}
