package com.guidereservationservice.dto;

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
public class GuideDto {

	Integer guideId;
	
	String name;

	String last_name;

	String phone;

	@JsonIgnore
	List<GuideReservationDto> guideReservation ;
	
}
