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
public class CountyDao{ // extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	protected ConnectionManager connectionManager;

	private static CountyDao instance = null;
	
	protected CountyDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CountyDao getInstance() {
		if(instance == null) {
			instance = new CountyDao();
		}
		return instance;
	}
	public County create(County county) throws SQLException {

		//String Zip, String City, String State, String CountyName
		String insertCounty = "INSERT INTO County(Zip, City, State, CountyName) VALUES(?, ?, ?, ?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCounty);
			insertStmt.setInt(1, county.getZip());
			insertStmt.setString(2, county.getCity());
			insertStmt.setString(2, county.getState());
			insertStmt.setString(2, county.getCountyName());
			
			insertStmt.executeUpdate();
			return county;
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

//public Companies getCompanyByCompanyName(String companyName)
	
//	public County getCountyByCountyName(String countyname) throws SQLException {
//		
//		String selectCounty = "SELECT * FROM County WHERE CountyName LIKE \'%" + countyname + "%\' LIMIT 100;";
//		Connection connection = null;
//		PreparedStatement selectStmt = null;
//		ResultSet results = null;
//		try {
//			connection = connectionManager.getConnection();
//			selectStmt = connection.prepareStatement(selectCounty);
////			selectStmt.setString(1, countyname);
//
//			results = selectStmt.executeQuery();
//			if(results.next()) {
////				String zipName = results.getString("Zip");
//				String countyName = results.getString("countyname");
//				int zipName = results.getInt("zip");
//				String stateName = results.getString("state");
//				String cityName = results.getString("city");
//				
//				County county = new County(zipName, cityName, stateName, countyName);
////				Date dob = new Date(results.getTimestamp("DoB").getTime());
////				Users.StatusLevel statusLevel = Users.StatusLevel.valeOf(
////						results.getString("StatusLevel"));
////				County county = new County(countyName);
//				return county;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if(connection != null) {
//				connection.close();
//			}
//			if(selectStmt != null) {
//				selectStmt.close();
//			}
//			if(results != null) {
//				results.close();
//			}
//		}
//		return null;
//	}
	
	public List<County> getCountyByCountyName(String countyname) throws SQLException {
		List<County> counties = new ArrayList<County>();
		String selectCounty = "SELECT * FROM County WHERE CountyName LIKE \'%" + countyname + "%\' LIMIT 100;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCounty);
//			selectStmt.setString(1, blogUser.getUserName());
			results = selectStmt.executeQuery();
			while(results.next()) {
//				String zipName = results.getString("Zip");
				String countyName = results.getString("countyname");
				int zipName = results.getInt("zip");
				String stateName = results.getString("state");
				String cityName = results.getString("city");
				
				County county_new = new County(zipName, cityName, stateName, countyName);

				counties.add(county_new);
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
		return counties;
	}


	public County delete(County county) throws SQLException {
		String delete = "DELETE FROM County WHERE CountyName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(delete);
			deleteStmt.setString(1, county.getCountyName());
			int affectedRows = deleteStmt.executeUpdate();
			if (affectedRows == 0) {
				throw new SQLException("No records available to delete for Company=" + county.getCountyName());
			}
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
