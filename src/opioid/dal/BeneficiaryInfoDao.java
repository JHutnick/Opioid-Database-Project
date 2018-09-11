package opioid.dal;
import opioid.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import opioid.model.*;


public class BeneficiaryInfoDao {
	protected ConnectionManager connectionManager;

	private static BeneficiaryInfoDao instance = null;
	protected BeneficiaryInfoDao() {
		connectionManager = new ConnectionManager();
	}
	public static BeneficiaryInfoDao getInstance() {
		if(instance == null) {
			instance = new BeneficiaryInfoDao();
		}
		return instance;
	}

	public BeneficiaryInfo create(BeneficiaryInfo ben) throws SQLException {
		String insertBen =
			"INSERT INTO BeneficiaryInfo(DoctorId, BeneCount, OpiBenCount, ErOpiBenCount, AntiBioBenCount, BenAgeL65,"
			+ "BenAgeG65, AveAgeBen, BenF, BenM, BenNDual, BenDual, BenAvgRisk) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertBen);
			insertStmt.setInt(1, ben.getDoctorId());
			insertStmt.setInt(2, ben.getBeneCount());
			insertStmt.setInt(3, ben.getOpiBenCount());
			insertStmt.setInt(4, ben.getErOpiBenCount());
			insertStmt.setInt(5, ben.getAntiBioBenCount());
			insertStmt.setInt(6, ben.getBenAgeL65());
			insertStmt.setDouble(7, ben.getBenAgeG65());
			insertStmt.setInt(8, ben.getAvgAgeBen());
			insertStmt.setInt(9, ben.getBenF());
			insertStmt.setInt(10, ben.getBenM());
			insertStmt.setInt(11, ben.getBenNDual());
			insertStmt.setInt(12, ben.getBenDual());
			insertStmt.setDouble(13, ben.getBenAvgRisk());
			insertStmt.executeUpdate();
			
			return ben;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	
	public BeneficiaryInfo delete(BeneficiaryInfo ben) throws SQLException {
		// Note: BlogComments has a fk constraint on BlogPosts with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteClaim = "DELETE FROM BeneficiaryInfo WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteClaim);
			deleteStmt.setInt(1, ben.getDoctorId());
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

	/**
	 * Get the BlogPosts record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single BlogPosts instance.
	 * Note that we use BlogUsersDao to retrieve the referenced BlogUsers instance.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the BlogPosts, BlogUsers tables and then build each object.
	 */
	public BeneficiaryInfo getBenbyDoctorID(int doctorId) throws SQLException {
		String selectBen =
			"SELECT * " +
			"FROM BeneficiaryInfo " +
			"WHERE DoctortId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBen);
			selectStmt.setInt(1, doctorId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultDocId = results.getInt("DoctorId");
				int benCount = results.getInt("BeneCount");
				int opi = results.getInt("OpiBenCount");
				int er = results.getInt("ErOpiBenCount");
				int anti = results.getInt("AntibioCount");
				int ageL = results.getInt("BenAgeL65");
				double ageG = results.getDouble("BenAgeG65");
				int ageAvg= results.getInt("AveAgeBen");
				int benF = results.getInt("BenF");
				int benM = results.getInt("BenM");
				int nDual = results.getInt("BenNDual");
				int dual= results.getInt("BenDual");
				double risk = results.getDouble("BenAvgRisk");
				BeneficiaryInfo ben = new BeneficiaryInfo(resultDocId, benCount, opi, er, anti, ageL, ageG, ageAvg, benF, 
						benM, nDual, dual, risk);
				return ben;
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

}
