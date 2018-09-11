package opioid.model;

public class SaveDoctor {
	protected String userName;
	protected int doctorId;
	protected String passWord;
	
	public SaveDoctor(String userName, int doctorId, String passWord) {
		this.userName = userName;
		this.doctorId = doctorId;
		this.passWord = passWord;
	}
	
	public SaveDoctor(String userName, String passWord) {
		this.userName = userName;
		this.passWord = passWord;
	}

	public SaveDoctor(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
}
