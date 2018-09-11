package opioid.dal;
import opioid.model.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import opioid.model.*;


public class ClaimInfoDao {
	protected ConnectionManager connectionManager;

	private static ClaimInfoDao instance = null;
	protected ClaimInfoDao() {
		connectionManager = new ConnectionManager();
	}
	public static ClaimInfoDao getInstance() {
		if(instance == null) {
			instance = new ClaimInfoDao();
		}
		return instance;
	}

	public ClaimInfo create(ClaimInfo claim) throws SQLException {
		String insertClaimInfo =
			"INSERT INTO ClaimInfo(DoctorId, TotalClaimCount, 30DayFill, BrandClaimCoiunt, GenericClaim, "
			+ "OpioidClaim, ErOpioidClaim, AntiClaim) " +
			"VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertClaimInfo);
			insertStmt.setInt(1, claim.getDoctorId());
			insertStmt.setInt(2, claim.getTotalClaimCount());
			insertStmt.setInt(3, claim.getDayFill30());
			insertStmt.setInt(4, claim.getBrandClaimCount());
			insertStmt.setInt(5, claim.getGenericClaim());
			insertStmt.setInt(6, claim.getOpioidClaim());
			insertStmt.setInt(7, claim.getErOpioidClaim());
			insertStmt.setInt(8, claim.getAntiClaim());
			insertStmt.executeUpdate();
			
			return claim;
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

	
	public ClaimInfo delete(ClaimInfo claim) throws SQLException {
		// Note: BlogComments has a fk constraint on BlogPosts with the reference option
		// ON DELETE CASCADE. So this delete operation will delete all the referencing
		// BlogComments.
		String deleteClaim = "DELETE FROM Claim WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteClaim);
			deleteStmt.setInt(1, claim.getDoctorId());
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
	public ClaimInfo getClaimbyDoctorID(int doctorId) throws SQLException {
		String selectClaim =
			"SELECT * " +
			"FROM ClaimInfo " +
			"WHERE DoctortId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectClaim);
			selectStmt.setInt(1, doctorId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultDocId = results.getInt("DoctorId");
				int totalClaim = results.getInt("TotalClaimCount");
				int dayFill = results.getInt("30DayFill");
				int brand = results.getInt("BrandClaimCount");
				int generic = results.getInt("GenericClaimCount");
				int opioid = results.getInt("OpioidClaim");
				int er = results.getInt("ErOpioidClaim");
				int anti = results.getInt("AntiClaim");
				ClaimInfo claim = new ClaimInfo(resultDocId, totalClaim, dayFill, brand, generic,
						opioid, er, anti);
				return claim;
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
