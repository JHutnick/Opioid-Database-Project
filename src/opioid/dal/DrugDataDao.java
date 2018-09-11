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
public class DrugDataDao{ // extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	protected ConnectionManager connectionManager;
	
	private static DrugDataDao instance = null;

	
	protected DrugDataDao() {
		connectionManager = new ConnectionManager();
	}
	public static DrugDataDao getInstance() {
		if(instance == null) {
			instance = new DrugDataDao();
		}
		return instance;
	}
	public DrugData create(DrugData drugdata) throws SQLException {
		// Insert into the superclass table first.
//		create(new Persons(Users.getUserName(), Users.getFirstName(),
//		Users.getLastName()));

		String insertDD = "INSERT INTO DeathData(DoctorId, TotalDaySupply, OpioidPresciberRate, ErOpioidRate) VALUES(?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDD);
			insertStmt.setInt(1, drugdata.getDoctorId());
			insertStmt.setInt(2, drugdata.getTotalDaySupply()); // cast this to Date
			insertStmt.setDouble(3, drugdata.getOpioidPresciberRate());
			insertStmt.setDouble(3, drugdata.getErOpioidRate());

			insertStmt.executeUpdate();
			return drugdata;
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

	public DrugData getDDbyD_ID(int DoctorId) throws SQLException {
		String selectDD =
				"SELECT DoctorId, TotalDaySupply, OpioidPresciberRate, ErOpioidRate" +
				"FROM DrugData " +
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
				int TDS = results.getInt("TotalDaySupply");
				double OPR = results.getDouble("OpioidPresciberRate");
				double ErOR = results.getDouble("ErOpioidRate");

				DrugData drugData = new DrugData(doctor, TDS, OPR, ErOR);
				return drugData;
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

	public List<DrugData> getDrugDatabyOPR(double opr)
			throws SQLException {
		List<DrugData> drugdata = new ArrayList<DrugData>();
		String selectDD =
				"SELECT DoctorId, TotalDaySupply, OpioidPresciberRate, ErOpioidRate" +
				"FROM DrugData " +
				"WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDD);
			selectStmt.setDouble(1, opr);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int doctor = results.getInt("DoctorId");
				int tds = results.getInt("TotalDaySupply");
				double opr_ = results.getDouble("OpioidPresciberRate");
				double eor = results.getDouble("ErOpioidRate");

				DrugData drugdata_new = new DrugData(doctor, tds, opr_, eor);
				drugdata.add(drugdata_new);
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
		return drugdata;
	}
	
	public List<DrugData> getDrugDatabyEOR(double eor)
			throws SQLException {
		List<DrugData> drugdata = new ArrayList<DrugData>();
		String selectDD =
				"SELECT DoctorId, TotalDaySupply, OpioidPresciberRate, ErOpioidRate" +
				"FROM DrugData " +
				"WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDD);
			selectStmt.setDouble(1, eor);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int doctor = results.getInt("DoctorId");
				int tds = results.getInt("TotalDaySupply");
				double opr_ = results.getDouble("OpioidPresciberRate");
				double EOR = results.getDouble("ErOpioidRate");

				DrugData drugdata_new = new DrugData(doctor, tds, opr_, EOR);
				drugdata.add(drugdata_new);
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
		return drugdata;
	}
	//public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration)
//	public CreditCards updateExpiration(CreditCards creditCard, Date newExpiration) throws SQLException {
//		String updateBlogComment = "UPDATE CreditCards SET Expiration=? WHERE CardNumber=?;";
//		Connection connection = null;
//		PreparedStatement updateStmt = null;
//		try {
//			connection = connectionManager.getConnection();
//			updateStmt = connection.prepareStatement(updateBlogComment);
//			updateStmt.setDate(1, (java.sql.Date) newExpiration); 
//			updateStmt.setLong(2, creditCard.getCardNumber()); 
////			Date newCreatedTimestamp = new Date();
////			updateStmt.setTimestamp(2, new Timestamp(newCreatedTimestamp.getTime()));
//			
//			updateStmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(updateStmt != null) {
//				updateStmt.close();
//			}
//		}
//		return creditCard;
//	}
	
	
	
	//public CreditCards delete(CreditCards creditCard)
	public DrugData delete(DrugData drugdata) throws SQLException {

		String deleteDrugData = "DELETE FROM DrugData WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDrugData);
			deleteStmt.setInt(1, drugdata.getDoctorId());
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
