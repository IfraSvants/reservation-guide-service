package com.guidereservationservice.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.guidereservationservice.dto.GuideReservationDto;

public interface GuideReservationService {

	List<GuideReservationDto> findAll();
	
	GuideReservationDto save( GuideReservationDto guideReservationDto );
	
	GuideReservationDto update( GuideReservationDto guideReservationDto , Integer id ) throws NotFoundException;
	
	GuideReservationDto findById( Integer id );
	
	void delete( Integer id );
	
}
