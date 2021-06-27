package com.soficlinic.patientmanagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soficlinic.patientmanagement.dao.Patient;

@Transactional
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	Patient findBySocialSecurityNumber(Long socialSecurityNumber);

}
