package com.soficlinic.patientmanagement.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Visit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "VISIT_ID")
	@JsonIgnore
	private Long id;

	@Enumerated(EnumType.STRING)
	private VISIT_TYPE visitType;

	@UpdateTimestamp
	private LocalDateTime visitTime;

	@JsonIgnore
	private Long patientId;
	
	@JsonInclude(Include.NON_NULL)
	String reasonOfVisit;

	public enum VISIT_TYPE {
		HOME_VISIT, CLINIC_VISIT
	}

}
