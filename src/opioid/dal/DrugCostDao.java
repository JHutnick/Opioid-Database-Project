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
public class DrugCostDao{ // extends PersonsDao {
	// Single pattern: instantiation is limited to one object.
	protected ConnectionManager connectionManager;
	
	private static DrugCostDao instance = null;

	
	protected DrugCostDao() {
		connectionManager = new ConnectionManager();
	}
	public static DrugCostDao getInstance() {
		if(instance == null) {
			instance = new DrugCostDao();
		}
		return instance;
	}
	public DrugCost create(DrugCost drugcost) throws SQLException {
		// Insert into the superclass table first.

		String insertDD = "INSERT INTO DrugCount(DoctorId, TotalDrugCost, BrandDrugCost, GenericDrugCost, OpioidDrugCost,"
				+ "ErOpioidDrugCost, AntiDrugCost) "
				+ "VALUES(?,?,?, ?, ? ,? ,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDD);
			insertStmt.setInt(1, drugcost.getDoctorId());
			insertStmt.setDouble(2, drugcost.getTotalDrugCost());
			insertStmt.setDouble(3, drugcost.getBrandDrugCost());
			insertStmt.setDouble(4, drugcost.getBrandDrugCost());
			insertStmt.setDouble(5, drugcost.getGenericDrugCost());
			insertStmt.setDouble(6, drugcost.getOpioidDrugCost());
			insertStmt.setDouble(7, drugcost.getErOpioidDrugCost());
			insertStmt.setDouble(8, drugcost.getAntiDrugCost());
			
			insertStmt.executeUpdate();
			return drugcost;
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

	public List<DrugCost> getDrugCountbyTDC(int DoctorID)
			throws SQLException {
		List<DrugCost> drugcost = new ArrayList<DrugCost>();
		String selectDC =
				"SELECT DoctorId, TotalDrugCost, BrandDrugCost, GenericDrugCost, OpioidDrugCost,\"\r\n" + 
				"				+ \"ErOpioidDrugCost, AntiDrugCost" +
				"FROM DrugCost " +
				"WHERE TotalDrugCost=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDC);
			selectStmt.setInt(1, DoctorID);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("DoctorId");
				double tds = results.getDouble("TotalDrugCost");
				double bdc = results.getDouble("BrandDrugCost");
				double gdc = results.getDouble("GenericDrugCost");
				double odc = results.getDouble("OpioidDrugCost");
				double eodc = results.getDouble("ErOpioidDrugCost");
				double adc = results.getDouble("AntiDrugCost");
				
				DrugCost d_count = new DrugCost(id, tds, bdc, gdc,odc,eodc,adc);
				drugcost.add(d_count);
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
		return drugcost;
	}
	
	
	
	public DrugCost delete(DrugCost drugCost) throws SQLException {

		String deleteDrugCount = "DELETE FROM DrugCost WHERE DoctorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDrugCount);
			deleteStmt.setInt(1, drugCost.getDoctorId());
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
