package com.guidereservationservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guidereservationservice.models.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer> {
	
}