package com.guidereservationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guidereservationservice.models.GuideEntity;

@Repository
public interface GuideDao extends JpaRepository<GuideEntity, Integer> {

}
