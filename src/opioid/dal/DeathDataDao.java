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
public class DeathDataDao extends UsersDao{ // extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	
	private static DeathDataDao instance = null;
	
//	protected DoctorDao() {
//		super();
//	}
	
	protected DeathDataDao() {
		super();
//		connectionManager = new ConnectionManager();
	}
	public static DeathDataDao getInstance() {
		if(instance == null) {
			instance = new DeathDataDao();
		}
		return instance;
	}
	public DeathData create(DeathData deathdata) throws SQLException {
		// Insert into the superclass table first.
//		create(new Persons(Users.getUserName(), Users.getFirstName(),
//		Users.getLastName()));

		String insertDD = "INSERT INTO DeathData(Year, Population, DeathRateRangeLow, County, State) VALUES(?,?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDD);
			insertStmt.setInt(1, deathdata.getYear());
			insertStmt.setLong(2, deathdata.getPopulation()); // cast this to Date
			insertStmt.setDouble(3, deathdata.getDeathRateRangeLow());
			insertStmt.setString(4, deathdata.getCounty());
			insertStmt.setString(5, deathdata.getState());

			insertStmt.executeUpdate();
			return deathdata;
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

	public DeathData getDDbyYear(int year) throws SQLException {
		String selectDD =
				"SELECT Year, Population, DeathRateRangeLow, County, State" +
				"FROM DeathData " +
				"WHERE Year=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDD);
			selectStmt.setInt(1, year);
			
			results = selectStmt.executeQuery();
			if(results.next()) {
				int year_ = results.getInt("year");
				long population_ = results.getLong("population");
				double deathrate = results.getDouble("deathraterangelow");
//				double deathrate_2 = results.getDouble("deathraterangehigh");
				String county_ = results.getString("county");
				String state_ = results.getString("state");

				DeathData deathdata = new DeathData(year_, population_, deathrate, county_, state_);
				return deathdata;
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

	public List<DeathData> getDeathDatabyState(String state)
			throws SQLException {
		List<DeathData> deathdatas = new ArrayList<DeathData>();
		String selectDD = "SELECT * FROM DeathData WHERE State LIKE \'%" + state + "%\' LIMIT 100;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDD);
			//selectStmt.setString(1, county);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int year = results.getInt("year");
				long population = results.getLong("population");
				double deathrate = results.getDouble("deathraterangelow");
//				double deathrate_2 = results.getDouble("deathraterangehigh");
				String county = results.getString("county");
				String state1 = results.getString("state");

				DeathData deathdata_new = new DeathData(year, population, deathrate, county, state1);
				deathdatas.add(deathdata_new);
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
		return deathdatas;
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
	public DeathData delete(DeathData deathdata) throws SQLException {

		String deleteBlogPost = "DELETE FROM DeathData WHERE County=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogPost);
			deleteStmt.setString(1, deathdata.getCounty());
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
