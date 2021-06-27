package com.soficlinic.patientmanagement.services;

import static com.soficlinic.patientmanagement.constants.PatientDataServicesConstants.PATIENT_VISITS_CREATED_SUCCESSFULLY;
import static com.soficlinic.patientmanagement.constants.PatientDataServicesConstants.PATIENT_VISITS_UPDATED_SUCCESSFULLY;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soficlinic.patientmanagement.dao.Patient;
import com.soficlinic.patientmanagement.dao.Visit;
import com.soficlinic.patientmanagement.exception.PatientNotFoundException;
import com.soficlinic.patientmanagement.repository.PatientRepository;
import com.soficlinic.patientmanagement.repository.VisitRepository;

@Service
@Slf4j
public class PatientDataServices {

	@Autowired
	PatientRepository patientRepo;

	@Autowired
	VisitRepository visitRepo;

	/**
	 * 
	 * @param socialSecurityNumber
	 * @return Patient
	 */
	public Patient getPatient(Long socialSecurityNumber) {
		Patient patient = patientRepo
				.findBySocialSecurityNumber(socialSecurityNumber);
		if (patient == null) {
			throw new PatientNotFoundException();
		}
		return patient;
	}

	/**
	 * 
	 * @param inPatient
	 * @return Creation or Update Message
	 */
	public String createOrUpdatePatientVisit(Patient inPatient) {
		try {
			Patient patient = getPatient(inPatient.getSocialSecurityNumber());
			patient.setVisits(inPatient.getVisits());
			patientRepo.save(patient);
			return PATIENT_VISITS_UPDATED_SUCCESSFULLY;
		} catch (PatientNotFoundException patientNotFoundException) {
			log.debug("Patient Not Found In Our Records. Hence Creating a New Entry");
			patientRepo.save(inPatient);
			return PATIENT_VISITS_CREATED_SUCCESSFULLY;
		}
	}

	/**
	 * 
	 * @param socialSecurityNumber
	 * @param visit
	 * @return Successful update message
	 */
	public String updatePatientVisit(Long socialSecurityNumber, Visit visit) {
		Patient patient = getPatient(socialSecurityNumber);
		if (patient == null) {
			throw new PatientNotFoundException();
		}

		List<Visit> visits = new ArrayList<>(1);
		visits.add(visit);
		patient.setVisits(visits);
		patientRepo.save(patient);
		return PATIENT_VISITS_UPDATED_SUCCESSFULLY;
	}

	/**
	 * 
	 * @return All the Patients in the clinic
	 */
	public List<Patient> getAllPatientDetails() {
		return patientRepo.findAll();
	}

	/**
	 * 
	 * @param socialSecurityNumber
	 * @return All the visits the patient has recorded so far
	 */
	public List<Visit> getAllPatientVisits(Long socialSecurityNumber) {
		Patient patient = getPatient(socialSecurityNumber);
		if (patient == null) {
			throw new PatientNotFoundException();
		}
		return patient.getVisits();
	}

}
