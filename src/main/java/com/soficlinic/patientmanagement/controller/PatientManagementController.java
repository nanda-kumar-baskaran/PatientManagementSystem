package com.soficlinic.patientmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soficlinic.patientmanagement.dao.Patient;
import com.soficlinic.patientmanagement.dao.Visit;
import com.soficlinic.patientmanagement.services.PatientDataServices;

import static com.soficlinic.patientmanagement.constants.RestResourceConstants.*;

/**
 * 
 * @author nanda.baskaran
 *
 */
@RequestMapping(ROOT_PATH)
@RestController
@ResponseBody
public class PatientManagementController {

	@Autowired
	PatientDataServices patientServices;

	/**
	 * Retrieve Patient Details based on their socialSecurityNumber
	 * @param socialSecurityNumber
	 * @return Patient Details
	 */

	@GetMapping(GET_PATIENT_DETAILS)
	public Patient getPatientDetails(
			@PathVariable(SOCIAL_SECURITY_NUMBER) Long socialSecurityNumber) {
		return patientServices.getPatient(socialSecurityNumber);
	}

	/**
	 * Create a new visit for an existing patient
	 * In case of a new patient, Persist the details of the new patient
	 * @param patient
	 * @return Success Message
	 */
	@PostMapping(value = CREATE_PATIENT_VISIT, consumes = { APPLICATION_JSON })
	public String createOrUpdatePatientVisit(@RequestBody Patient patient) {
		return patientServices.createOrUpdatePatientVisit(patient);
	}

	/**
	 * Add the current visit for a particular patient
	 * if the patient is known to be existing user
	 * @param socialSecurityNumber
	 * @param visit
	 * @return Success Message
	 */
	@PutMapping(value = UPDATE_PATIENT_VISIT, consumes = { APPLICATION_JSON })
	public String updatePatientVisit(
			@PathVariable(SOCIAL_SECURITY_NUMBER) Long socialSecurityNumber,
			@RequestBody Visit visit) {
		return patientServices.updatePatientVisit(socialSecurityNumber, visit);
	}

	/**
	 * Retrieve all the patients in the record so far
	 * @return All the Patients
	 */
	@GetMapping(GET_ALL_PATIENT_DETAILS)
	public List<Patient> getAllPatientDetails() {
		return patientServices.getAllPatientDetails();
	}

	/**
	 * Retrieve all the visit history of a particular patient
	 * with a given social security number
	 * @param socialSecurityNumber
	 * @return All the visits the patient has recorded so far
	 */
	@GetMapping(GET_ALL_PATIENT_VISITS)
	public List<Visit> getAllVisitsOfPatient(
			@PathVariable(SOCIAL_SECURITY_NUMBER) Long socialSecurityNumber) {
		return patientServices.getAllPatientVisits(socialSecurityNumber);
	}
}
