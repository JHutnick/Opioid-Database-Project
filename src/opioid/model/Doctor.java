package opioid.model;

/**
 * BlogUsers is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Users}).
 */
public class Doctor {
	protected int doctorId;
	protected String lastName;
	protected String firstName;
	protected String credentials;
	protected String street1;
	protected String city;
	protected int zip;
	protected String state;
	protected String speciality;

	
	public Doctor(int doctorId, String lastName, String firstName, String credentials, String street1,
			String city, int zip, String state, String speciality) {
		this.doctorId = doctorId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.credentials = credentials;
		this.street1 = street1;
		this.city = city;
		this.zip = zip;
		this.state = state;
		this.speciality = speciality;
	}

	public Doctor(int doctorId) {
		this.doctorId = doctorId;
	}

	/** Getters and setters. */
	
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}
	
	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
}
