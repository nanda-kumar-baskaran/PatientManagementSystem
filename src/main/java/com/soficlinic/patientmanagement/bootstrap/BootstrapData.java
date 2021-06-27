package com.soficlinic.patientmanagement.bootstrap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.soficlinic.patientmanagement.dao.Patient;
import com.soficlinic.patientmanagement.dao.Visit;
import com.soficlinic.patientmanagement.dao.Visit.VISIT_TYPE;
import com.soficlinic.patientmanagement.repository.PatientRepository;

@Component
@Slf4j
public class BootstrapData implements CommandLineRunner {

	private static final String BOOTSTRAPPING_DATA_INFO = "Bootstrapping data begins...";

	private static final String GENERAL_CHECK_UP = "General Check Up";

	private static final String CRUISE = "Cruise";

	private static final String FAMILY_HISTORY = "Having Type 2 Diabetes";

	private static final String DOB = "1979-11-22";

	private static final String TOM = "Tom";

	private boolean isBootStrapNeeded = false;

	@Autowired
	PatientRepository patientRepo;

	/**
	 * Change the boolean isBootStrapNeeded to true to enable data bootstrap
	 * Caution: This might break some unit tests. This class is just for
	 * development purposes and not to be used in production code
	 */
	@Override
	public void run(String... args) throws Exception {
		if (isBootStrapNeeded) {

			log.info(BOOTSTRAPPING_DATA_INFO);
			List<Visit> visits = getVisits();
			Patient patient = createPatients(visits);
			patientRepo.save(patient);
		}

	}

	private Patient createPatients(List<Visit> visits) {
		Patient patient = new Patient();
		patient.setName(TOM);
		LocalDate dob = LocalDate.parse(DOB);
		patient.setDateOfBirth(dob);
		patient.setFamilyHistory(FAMILY_HISTORY);
		patient.setSocialSecurityNumber(123456789L);
		patient.setSurName(CRUISE);
		patient.setVisits(visits);
		return patient;
	}

	private List<Visit> getVisits() {
		Visit visit = new Visit();
		visit.setPatientId(123456789L);
		visit.setVisitTime(LocalDateTime.now());
		visit.setVisitType(VISIT_TYPE.HOME_VISIT);
		visit.setReasonOfVisit(GENERAL_CHECK_UP);

		List<Visit> visits = new ArrayList<>();
		visits.add(visit);
		return visits;
	}

}
