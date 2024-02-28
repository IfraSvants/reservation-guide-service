package com.guidereservationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guidereservationservice.models.PlacesEntity;


@Repository
public interface PlaceDao extends JpaRepository<PlacesEntity, Integer> {
	
	PlacesEntity findByName(String name);
}