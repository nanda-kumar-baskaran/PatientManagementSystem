package com.soficlinic.patientmanagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.soficlinic.patientmanagement.constants.PatientDataServicesConstants;
import com.soficlinic.patientmanagement.dao.Patient;
import com.soficlinic.patientmanagement.dao.Visit;
import com.soficlinic.patientmanagement.dao.Visit.VISIT_TYPE;
import com.soficlinic.patientmanagement.exception.PatientNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientManagementControllerTest {

	private static final String MONTHLY_CHECK_UP = "Monthly Check up";

	private static final long SAMPLE_SOCIAL_SECURITY_NUMBER = 100456789L;

	private static final String PATIENT_NOT_FOUND = "Patient Not Found";

	private static final long DUMMY_SOCIAL_SECURITY_NUMBER = 123L;

	@Autowired
	PatientManagementController patientManagementController;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testGetPatientDetailsWithNoPatientRecord() {
		exceptionRule.expect(PatientNotFoundException.class);
		exceptionRule.expectMessage(PATIENT_NOT_FOUND);
		patientManagementController
				.getPatientDetails(DUMMY_SOCIAL_SECURITY_NUMBER);
	}

	@Test
	@DirtiesContext
	public void testCreateOrUpdatePatientVisit() {
		Visit visit = createVisit();
		List<Visit> visits = new ArrayList<>();
		visits.add(visit);
		Patient patient = createPatient(visits);
		String message = patientManagementController
				.createOrUpdatePatientVisit(patient);
		assertThat(
				PatientDataServicesConstants.PATIENT_VISITS_CREATED_SUCCESSFULLY)
				.isEqualTo(message);
		assertUpdatePatient(visits, patient);
	}

	@Test
	@DirtiesContext
	public void testupdateAndGetAllPatientVisit() {
		Visit visit = createVisit();
		List<Visit> visits = new ArrayList<>();
		visits.add(visit);
		Patient patient = createPatient(visits);
		patientManagementController.createOrUpdatePatientVisit(patient);
		visit.setReasonOfVisit(MONTHLY_CHECK_UP);
		Long socialSecurityNumber = patient.getSocialSecurityNumber();
		String message = patientManagementController.updatePatientVisit(
				socialSecurityNumber, visit);
		assertThat(
				PatientDataServicesConstants.PATIENT_VISITS_UPDATED_SUCCESSFULLY)
				.isEqualTo(message);
		assertgetAllPatientVisits(socialSecurityNumber);
	}

	private void assertgetAllPatientVisits(Long socialSecurityNumber) {
		List<Visit> resultVisits = patientManagementController
				.getAllVisitsOfPatient(socialSecurityNumber);
		assertTrue(resultVisits.stream()
				.anyMatch(
						visitObj -> visitObj.getReasonOfVisit() != null
								&& MONTHLY_CHECK_UP.equals(visitObj
										.getReasonOfVisit())));
	}

	@Test
	public void testupdatePatientVisitWithNoPatientFound() {
		exceptionRule.expect(PatientNotFoundException.class);
		exceptionRule.expectMessage(PATIENT_NOT_FOUND);
		patientManagementController.updatePatientVisit(
				SAMPLE_SOCIAL_SECURITY_NUMBER, createVisit());
	}

	@Test
	public void testgetAllVisitsOfPatientWithNoPatientFound() {
		exceptionRule.expect(PatientNotFoundException.class);
		exceptionRule.expectMessage(PATIENT_NOT_FOUND);
		patientManagementController
				.getAllVisitsOfPatient(SAMPLE_SOCIAL_SECURITY_NUMBER);
	}

	@Test
	public void testGetAllPatientDetails() {
		assertTrue(patientManagementController.getAllPatientDetails().isEmpty());
		Visit visit = createVisit();
		List<Visit> visits = new ArrayList<>();
		visits.add(visit);
		Patient patient = createPatient(visits);
		patientManagementController.createOrUpdatePatientVisit(patient);
		assertTrue(!patientManagementController.getAllPatientDetails()
				.isEmpty());
	}

	private void assertUpdatePatient(List<Visit> visits, Patient patient) {
		Visit visit = createVisit();
		visits.add(visit);
		patient.setVisits(visits);
		String message = patientManagementController
				.createOrUpdatePatientVisit(patient);
		assertThat(
				PatientDataServicesConstants.PATIENT_VISITS_UPDATED_SUCCESSFULLY)
				.isEqualTo(message);
	}

	private Patient createPatient(List<Visit> visits) {
		Patient patient = new Patient();
		patient.setName("Tom");
		LocalDate dob = LocalDate.parse("1979-11-22");
		patient.setDateOfBirth(dob);
		patient.setFamilyHistory("Having Type 2 Diabetes");
		patient.setSocialSecurityNumber(SAMPLE_SOCIAL_SECURITY_NUMBER);
		patient.setSurName("Cruise");
		patient.setVisits(visits);
		return patient;
	}

	private Visit createVisit() {
		Visit visit = new Visit();
		visit.setPatientId(SAMPLE_SOCIAL_SECURITY_NUMBER);
		visit.setVisitTime(LocalDateTime.now());
		visit.setVisitType(VISIT_TYPE.HOME_VISIT);
		visit.setReasonOfVisit("General Check Up");
		return visit;
	}

}
