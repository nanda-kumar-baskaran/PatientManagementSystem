package com.soficlinic.patientmanagement.constants;

public class RestResourceConstants {

	public final static String GET_PATIENT_DETAILS = "/getPatientDetails/socialSecurityNumber/{socialSecurityNumber}";

	public final static String ROOT_PATH = "/clinic";

	public final static String SOCIAL_SECURITY_NUMBER = "socialSecurityNumber";

	public final static String CREATE_PATIENT_VISIT = "/createPatientVisit";

	public final static String APPLICATION_JSON = "application/JSON";

	public final static String UPDATE_PATIENT_VISIT = "/updatePatientVisit/socialSecurityNumber/{socialSecurityNumber}";

	public static final String GET_ALL_PATIENT_VISITS = "/getAllPatientVisits/socialSecurityNumber/{socialSecurityNumber}";

	public static final String GET_ALL_PATIENT_DETAILS = "/getAllPatientDetails";

}
