package com.guidereservationservice.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;	

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GuideReservationDto {

	Integer reservationId;
	
	Date dateReservation;
	
	@JsonIgnore
	UserDto user;
	
	GuideDto guide;
	
	String type;
	
	List<PlaceDto> places;
}
