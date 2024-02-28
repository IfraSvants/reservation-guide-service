package com.guidereservationservice.service.facade;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.guidereservationservice.dto.GuideDto;

public interface GuideService {
	
	List<GuideDto> findAll();
	
	GuideDto save( GuideDto guideDto );
	
	GuideDto update( GuideDto guideDto , Integer id ) throws NotFoundException;
	
	GuideDto findById( Integer id );
	
	void delete( Integer id );

}
