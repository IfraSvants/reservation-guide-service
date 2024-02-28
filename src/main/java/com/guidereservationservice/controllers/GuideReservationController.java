package com.guidereservationservice.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guidereservationservice.dao.PlaceDao;
import com.guidereservationservice.dao.UserDao;
import com.guidereservationservice.dto.GuideDto;
import com.guidereservationservice.dto.GuideReservationDto;
import com.guidereservationservice.dto.PlaceDto;
import com.guidereservationservice.dto.Reservation;
import com.guidereservationservice.dto.UserDto;
import com.guidereservationservice.models.PlacesEntity;
import com.guidereservationservice.models.UserEntity;
import com.guidereservationservice.service.facade.GuideReservationService;
import com.guidereservationservice.service.facade.GuideService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/reservations")
public class GuideReservationController {
	
	final static Logger log = LogManager.getLogger(GuideController.class);
	
	@Autowired
	private GuideReservationService guideReservationService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PlaceDao placeDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private GuideService guideService;
	
	@GetMapping("")
	public ResponseEntity<List<GuideReservationDto>> findAll(){
		
		return new ResponseEntity<>(guideReservationService.findAll(), HttpStatus.OK);

	}
	
	@PostMapping("")
	public ResponseEntity<?> save( @Valid @RequestBody Reservation reservation ){
		
		Date dateReservation ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateReservation = dateFormat.parse(reservation.getDateReservation());
	    } catch (ParseException e) {
	        // Gérer l'erreur de format de date invalide
	        return ResponseEntity.badRequest().body("Format de date invalide. Format valide : yyyy-MM-dd");
	    }
		
		System.out.println("dateReservation : "+dateReservation+" : tesssssssssssssst : "+reservation.getUserId());
		
		Optional<UserEntity> userEntity = userDao.findById(reservation.getUserId());
		UserDto userDto = new UserDto();
		if(userEntity.isPresent()) {
			userDto = modelMapper.map(userEntity.get(), UserDto.class);
		}
		
		String type;
		GuideDto guideDto = new GuideDto();
		if(reservation.getGuideId() == null) {
			type = "NOT_GUIDE";
		}else {
			type = "GUIDE";
			guideDto = guideService.findById(reservation.getGuideId());
		}
		
		List<PlaceDto> places = new ArrayList<>();
		if(reservation.getPlaces() != null && !reservation.getPlaces().isEmpty() ) {
			
			for(String place : reservation.getPlaces()) {
				
				PlacesEntity entity = placeDao.findByName(place);
				places.add( modelMapper.map( entity , PlaceDto.class ));
				
			}
			
		}
		
		
		GuideReservationDto guideReservationDto = new GuideReservationDto(null, dateReservation, userDto, guideDto, type, places);
		
		GuideReservationDto saved = guideReservationService.save(guideReservationDto);
		return ResponseEntity.accepted().body(saved);

	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<GuideReservationDto> update( @Valid @RequestBody GuideReservationDto guideReservationDto , @PathVariable("id") Integer id ) throws NotFoundException{
		
		GuideReservationDto updated = guideReservationService.update(guideReservationDto,id);
		return ResponseEntity.accepted().body(updated);

	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<GuideReservationDto> findById( @PathVariable("id") Integer id) {
		
		GuideReservationDto guideReservationDto = guideReservationService.findById(id);
    	return ResponseEntity.ok(guideReservationDto);
    	
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		
		guideReservationService.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
