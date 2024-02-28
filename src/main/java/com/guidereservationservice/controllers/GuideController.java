package com.guidereservationservice.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.guidereservationservice.dto.GuideDto;
import com.guidereservationservice.service.facade.GuideService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/guides")
public class GuideController {

	final static Logger log = LogManager.getLogger(GuideController.class);
	
	@Autowired
	private GuideService guideService;
	
	@GetMapping("")
	public ResponseEntity<List<GuideDto>> findAll(){
		
		return new ResponseEntity<>(guideService.findAll(), HttpStatus.OK);

	}
	
	@PostMapping("")
	public ResponseEntity<GuideDto> save( @Valid @RequestBody GuideDto guideDto ){
		
		GuideDto saved = guideService.save(guideDto);
		return ResponseEntity.accepted().body(saved);

	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<GuideDto> update( @Valid @RequestBody GuideDto guideDto , @PathVariable("id") Integer id ) throws NotFoundException{
		
		GuideDto updated = guideService.update(guideDto,id);
		return ResponseEntity.accepted().body(updated);

	}
	
	@GetMapping("/id/{id}")
    public ResponseEntity<GuideDto> findById( @PathVariable("id") Integer id) {
		
		GuideDto guideDto = guideService.findById(id);
    	return ResponseEntity.ok(guideDto);
    	
    }
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> delete( @PathVariable("id") Integer id) {
		
		guideService.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
