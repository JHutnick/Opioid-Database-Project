package opioid.servlet;

import opioid.dal.*;
import opioid.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * FindUsers is the primary entry point into the application.
 * 
 * Note the logic for doGet() and doPost() are almost identical. However, there is a difference:
 * doGet() handles the http GET request. This method is called when you put in the /findusers
 * URL in the browser.
 * doPost() handles the http POST request. This method is called after you click the submit button.
 * 
 * To run:
 * 1. Run the SQL script to recreate your database schema: http://goo.gl/86a11H.
 * 2. Insert test data. You can do this by running blog.tools.Inserter (right click,
 *    Run As > JavaApplication.
 *    Notice that this is similar to Runner.java in our JDBC example.
 * 3. Run the Tomcat server at localhost.
 * 4. Point your browser to http://localhost:8080/BlogApplication/findusers.
 */
@WebServlet("/finddoctor")
public class FindDoctor extends HttpServlet {
	
	protected DoctorDao	doctorDao;
	
	@Override
	public void init() throws ServletException {
		doctorDao = doctorDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Doctor> doctors = new ArrayList<Doctor>();
        
        String stateName = req.getParameter("state");
        if (stateName == null || stateName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid state name.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
            	doctors = doctorDao.getDoctorByState(stateName);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + stateName);
        	messages.put("Doctor", stateName);
        }
        req.setAttribute("doctors", doctors);
        
        req.getRequestDispatcher("/FindDoctor.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Doctor> doctors = new ArrayList<Doctor>();
        
        String stateName = req.getParameter("state");
        if (stateName == null || stateName.trim().isEmpty()) {
            messages.put("success", "Please enter a valid state name.");
        } else {

        	try {
        		doctors = doctorDao.getDoctorByState(stateName);
            	//blogUsers = blogUsersDao.getBlogUsersFromFirstName(firstName);
        		messages.put("success", "Displaying results for " + stateName);
            	messages.put("state", stateName);
            	
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
        req.setAttribute("doctors", doctors);
        
        req.getRequestDispatcher("/FindDoctor.jsp").forward(req, resp);
    }
}
