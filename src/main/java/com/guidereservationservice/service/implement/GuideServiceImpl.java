package com.guidereservationservice.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.guidereservationservice.dao.GuideDao;
import com.guidereservationservice.dto.GuideDto;
import com.guidereservationservice.exception.EntityNotFoundException;
import com.guidereservationservice.models.GuideEntity;
import com.guidereservationservice.service.facade.GuideService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuideServiceImpl implements GuideService {

	private GuideDao guideDao;
	
	private ModelMapper modelMapper;
	
	@Override
	public List<GuideDto> findAll() {
		return guideDao
				.findAll()
				.stream().map( el->modelMapper.map(el, GuideDto.class) )
				.collect(Collectors.toList())
				;
	}

	@Override
	public GuideDto save(GuideDto guideDto) {
		
		GuideEntity guideEntity = guideDao.save(modelMapper.map(guideDto, GuideEntity.class));
		return modelMapper.map(guideEntity, GuideDto.class);
		
	}

	@Override
	public GuideDto update(GuideDto guideDto, Integer id) throws NotFoundException {
		
		guideDto.setGuideId(id);
		GuideEntity guideEntity = guideDao.save(modelMapper.map(guideDto, GuideEntity.class));
		return modelMapper.map(guideEntity, GuideDto.class);
		
	}

	@Override
	public GuideDto findById(Integer id) {
		
		GuideEntity guideEntity = guideDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Guide Not Found"));
		return modelMapper.map(guideEntity , GuideDto.class);
		
	}

	@Override
	public void delete(Integer id) {
		
		GuideEntity guideEntity = guideDao.findById(id).orElseThrow( ()->new EntityNotFoundException("Guide Not Found"));
		guideDao.delete(guideEntity);
		
	}

}
