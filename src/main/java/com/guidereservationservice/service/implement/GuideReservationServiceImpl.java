package com.guidereservationservice.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.guidereservationservice.dao.GuideReservationDao;
import com.guidereservationservice.dto.GuideReservationDto;
import com.guidereservationservice.exception.EntityNotFoundException;
import com.guidereservationservice.models.GuideReservationEntity;
import com.guidereservationservice.service.facade.GuideReservationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuideReservationServiceImpl implements GuideReservationService {

	private ModelMapper modelMapper;
	
	private GuideReservationDao guideReservationDao;
	
	@Override
	public List<GuideReservationDto> findAll() {
		// TODO Auto-generated method stub
		return guideReservationDao
			.findAll()
			.stream().map( el->modelMapper.map(el, GuideReservationDto.class) )
			.collect(Collectors.toList())
			;
	}

	@Override
	public GuideReservationDto save(GuideReservationDto guideReservationDto) {
		
		GuideReservationEntity guideReservationEntity = modelMapper.map(guideReservationDto, GuideReservationEntity.class);
		GuideReservationEntity saved = guideReservationDao.save(guideReservationEntity);
		
		return modelMapper.map(saved, GuideReservationDto.class);
	}

	@Override
	public GuideReservationDto update(GuideReservationDto guideReservationDto, Integer id) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuideReservationDto findById(Integer id) {

		GuideReservationEntity guideReservationEntity = guideReservationDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Reservation Not Found"));
		return modelMapper.map(guideReservationEntity , GuideReservationDto.class);
		
	}

	@Override
	public void delete(Integer id) {
		
		GuideReservationEntity guideReservationEntity = guideReservationDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Reservation Not Found"));
		guideReservationDao.delete(guideReservationEntity);
		
	}

}
