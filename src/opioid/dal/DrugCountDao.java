//Nate Lee

package opioid.dal;
import opioid.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Data access object (DAO) class to interact with the underlying Users table in your
 * MySQL instance. This is used to store {@link Users} into your MySQL instance and 
 * retrieve {@link Users} from MySQL instance.
 */
public class DrugCountDao{ // extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	protected ConnectionManager connectionManager;
	
	private static DrugCountDao instance = null;

	
	protected DrugCountDao() {
		connectionManager = new ConnectionManager();
	}
	public static DrugCountDao getInstance() {
		if(instance == null) {
			instance = new DrugCountDao();
		}
		return instance;
	}
	public DrugCount create(DrugCount drugcount) throws SQLException {
		// Insert into the superclass table first.
//		create(new Persons(Users.getUserName(), Users.getFirstName(),
//		Users.getLastName()));

		String insertDD = "INSERT INTO DrugCount(DoctorId, OpioidSupply, ErSupply) "
				+ "VALUES(?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDD);
			insertStmt.setInt(1, drugcount.getDoctorId());
			insertStmt.setInt(2, drugcount.getOpioidSupply()); // cast this to Date
			insertStmt.setInt(3, drugcount.getErSupply());

			insertStmt.executeUpdate();
			return drugcount;
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

	public DrugCount getDDbyD_ID(int DoctorId) throws SQLException {
		String selectDD =
				"SELECT DoctorId, OpioidSupply, ErSupply" +
				"FROM DrugCount " +
				"WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDD);
			selectStmt.setInt(1, DoctorId);
			
			results = selectStmt.executeQuery();
			if(results.next()) {
				int doctor = results.getInt("DoctorId");
				int OS = results.getInt("OpioidSupply");
				int ES = results.getInt("ErSupply");

				DrugCount drugcount = new DrugCount(doctor, OS, ES);
				return drugcount;
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

	public List<DrugCount> getDrugCountbyOpioidSupply(int OpioidSupply)
			throws SQLException {
		List<DrugCount> drugcount = new ArrayList<DrugCount>();
		String selectDC =
				"SELECT DoctorId, OpioidSupply, ErSupply" +
				"FROM DrugCount " +
				"WHERE OpioidSupply=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDC);
			selectStmt.setInt(1, OpioidSupply);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int doctor = results.getInt("DoctorId");
				int os = results.getInt("OpioidSupply");
				int es = results.getInt("ErSupply");

				DrugCount d_count = new DrugCount(doctor, os, es);
				drugcount.add(d_count);
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
		return drugcount;
	}
	
	public List<DrugCount> getDrugCountabyEOR(double ers)
			throws SQLException {
		List<DrugCount> drugcount = new ArrayList<DrugCount>();
		String selectDD =
				"SELECT DoctorId, OpioidSupply, ErSupply" +
				"FROM DrugCount " +
				"WHERE ErSupply=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDD);
			selectStmt.setDouble(1, ers);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int doctor = results.getInt("DoctorId");
				int tds = results.getInt("OpioidSupply");
				int er = results.getInt("ErSupply");
				
				DrugCount d_count = new DrugCount(doctor, tds, er);
				drugcount.add(d_count);
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
		return drugcount;
	}
	
	
	public DrugCount delete(DrugCount drugcount) throws SQLException {

		String deleteDrugCount = "DELETE FROM DrugCount WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDrugCount);
			deleteStmt.setInt(1, drugcount.getDoctorId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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
	
	
}
