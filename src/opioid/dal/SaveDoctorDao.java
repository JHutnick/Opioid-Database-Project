package opioid.dal;
import opioid.model.*;
import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import opioid.model.*;


public class SaveDoctorDao {
	protected ConnectionManager connectionManager;

	private static SaveDoctorDao instance = null;
	protected SaveDoctorDao() {
		connectionManager = new ConnectionManager();
	}
	public static SaveDoctorDao getInstance() {
		if(instance == null) {
			instance = new SaveDoctorDao();
		}
		return instance;
	}

	public SaveDoctor create(SaveDoctor doc) throws SQLException {
		String insertReshare =
			"INSERT INTO SaveDoctor(UserName,DoctorId, Password) " +
			"VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReshare);
			insertStmt.setString(1, doc.getUserName());
			insertStmt.setInt(2, doc.getDoctorId());
			insertStmt.setString(3, doc.getPassWord());
			insertStmt.executeUpdate();

			return doc;
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

	/**
	 * Delete the Reshares instance.
	 * This runs a DELETE statement.
	 */
	public SaveDoctor delete(SaveDoctor doc) throws SQLException {
		String deleteDoctor = "DELETE FROM SaveDoctor WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDoctor);
			deleteStmt.setString(1, doc.getUserName());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
	 * Get the Reshares record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Reshares instance.
	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
	 * BlogPosts and BlogUsers instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Reshares, BlogPosts, BlogUsers tables and then build each object.
	 */
	public SaveDoctor getSaveByUserName(String userName) throws SQLException {
		String selectSave =
			"SELECT * " +
			"FROM SaveDoctor " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSave);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				int doctorId = results.getInt("DoctorId");
				String passWord= results.getString("PassWord");
				SaveDoctor saved = new SaveDoctor(resultUserName, doctorId, passWord);
				return saved;
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

	/**
	 * Get the all the Reshares for a user.
	 */
	public List<SaveDoctor> getSaveDoctor(SaveDoctor user) throws SQLException {
		List<SaveDoctor> saveDoct = new ArrayList<SaveDoctor>();
		String selectBlogPosts =
			"SELECT * " +
			"FROM SaveDoctor " +
			"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBlogPosts);
			selectStmt.setString(1, user.getUserName());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String userName = results.getString("UserName");
				int doctorId = results.getInt("DoctorId");
				String passWord = results.getString("PassWord");
				SaveDoctor save = new SaveDoctor(userName, doctorId, passWord);
				saveDoct.add(save);
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
		return saveDoct;
	}
}
