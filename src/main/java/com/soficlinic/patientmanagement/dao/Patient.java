package com.soficlinic.patientmanagement.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Patient {

	@Id
	@Column(name = "PATIENT_ID")
	Long socialSecurityNumber;
	
	String name;
	String surName;
	LocalDate dateOfBirth;
	String familyHistory;
	
	@OneToMany(cascade = { CascadeType.ALL })
	@NotNull
	List<Visit> visits = new ArrayList<>();
	
	public List<Visit> getVisits() {
		return new ArrayList<>(visits);
	}
	public void setVisits(List<Visit> visits) {
		this.visits.addAll(visits);
	}
	
	
	
	
}
