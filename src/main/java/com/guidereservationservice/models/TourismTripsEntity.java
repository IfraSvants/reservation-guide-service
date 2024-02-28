package com.guidereservationservice.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table( name = "tourism_trip")
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourismTripsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trip_id")
	Integer tripId;
	
//	@Temporal(TemporalType.TIMESTAMP)
//    @Column( name = "trip_date", nullable = false, updatable = false)
//	Date tripDate;
	
	@Column( name = "trip_date", nullable = false )
	String tripDate;
	
	@Column( name = "trip_time", nullable = false )
	String tripTime;
	
	@Column( nullable = false )
	String ville;
	
	@OneToMany(mappedBy = "trip" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<TripReservationEntity> tripsReservation;
	
	@OneToMany(mappedBy = "trip" ,cascade = CascadeType.ALL , fetch = FetchType.LAZY )
	List<RatingTripEntity> ratingTripEntities;
	
	@Column( nullable = false )
	String places;
	
}
