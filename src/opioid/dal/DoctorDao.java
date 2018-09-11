package opioid.dal;
import opioid.model.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opioid.model.*;


/**
 * Data access object (DAO) class to interact with the underlying BlogUsers table in your
 * MySQL instance. This is used to store {@link Doctor} into your MySQL instance and 
 * retrieve {@link Doctor} from MySQL instance.
 */
public class DoctorDao extends UsersDao {
	// Single pattern: instantiation is limited to one object.
	private static DoctorDao instance = null;
	protected DoctorDao() {
		super();
	}
	public static DoctorDao getInstance() {
		if(instance == null) {
			instance = new DoctorDao();
		}
		return instance;
	}

	public Doctor create(Doctor doctor) throws SQLException {
		// Insert into the superclass table first.

		String insertDoctor = "INSERT INTO Doctor(DoctorId, LastName, FirstName, Credentials,"
				+ "Street1, City, Zip, State, Speciality) VALUES(?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDoctor);
			insertStmt.setInt(1, doctor.getDoctorId());
			insertStmt.setString(2, doctor.getLastName());
			insertStmt.setString(3, doctor.getFirstName());
			insertStmt.setString(4, doctor.getCredentials());
			insertStmt.setString(5, doctor.getStreet1());
			insertStmt.setString(6, doctor.getCity());
			insertStmt.setInt(7, doctor.getZip());
			insertStmt.setString(8, doctor.getState());
			insertStmt.setString(9, doctor.getSpeciality());
			insertStmt.executeUpdate();
			return doctor;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	/**
	 * Delete the BlogUsers instance.
	 * This runs a DELETE statement.
	 */
	public Doctor delete(Doctor doctor) throws SQLException {
		String deleteDoctor = "DELETE FROM Doctor WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDoctor);
			deleteStmt.setInt(1, doctor.getDoctorId());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for DoctorId=" + doctor.getDoctorId());
			}

			// Then also delete from the superclass.
			// Notes:
			// 1. Due to the fk constraint (ON DELETE CASCADE), we could simply call
			//    super.delete() without even needing to delete from Administrators first.
			// 2. BlogPosts has a fk constraint on BlogUsers with the reference option
			//    ON DELETE SET NULL. If the BlogPosts fk reference option was instead
			//    ON DELETE RESTRICT, then the caller would need to delete the referencing
			//    BlogPosts before this BlogUser can be deleted.
			//    Example to delete the referencing BlogPosts:
			//    List<BlogPosts> posts = BlogPostsDao.getBlogPostsForUser(blogUser.getUserName());
			//    for(BlogPosts p : posts) BlogPostsDao.delete(p);

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Doctor getDoctorByLastName(String lastName) throws SQLException {
		// To build an BlogUser object, we need the Persons record, too.
		String selectDoctor =
			"SELECT * " +
			"FROM Doctor " +
			"WHERE LastName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDoctor);
			selectStmt.setString(1, lastName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int doctorId = results.getInt("DoctorId");
				String resultLastName = results.getString("LastName");
				String firstName = results.getString("FirstName");
				String credentials = results.getString("Credentials");
				String street1 = results.getString("Street1");
				String city = results.getString("City");
				int zip = results.getInt("Zip");
				String state = results.getString("State");
				String speciality = results.getString("Speciality");
				
				Doctor doctor = new Doctor(doctorId, resultLastName, firstName, credentials, street1,
						city, zip, state, speciality);
				return doctor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	public List<Doctor> getDoctorByState(String state) throws SQLException {
		List<Doctor> doctors = new ArrayList<Doctor>();
		String selectDoctor = "SELECT * FROM Doctor WHERE State LIKE \'%" + state + "%\' LIMIT 100;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDoctor);
//			selectStmt.setString(1, state);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int doctorId = results.getInt("doctorid");
				String lastName = results.getString("lastname");
				String firstName = results.getString("firstname");
				String credentials = results.getString("credentials");
				String street1 = results.getString("street1");
				String city = results.getString("city");
				int zip = results.getInt("zip");
				String state1 = results.getString("state");
				String speciality = results.getString("speciality");
				
				Doctor doctor_new = new Doctor(doctorId, lastName, firstName, credentials, street1,
						city, zip, state1, speciality);
				doctors.add(doctor_new);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return doctors;
	}
}
