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


@WebServlet("/doctorcreate")
public class DoctorCreate extends HttpServlet {
	
	protected DoctorDao doctorDao;
	
	@Override
	public void init() throws ServletException {
		doctorDao = DoctorDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/DoctorCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String doctorid = req.getParameter("doctorid");
        if (doctorid == null || doctorid.trim().isEmpty()) {
            messages.put("success", "Invalid DoctorId");
        } else {
        	// Create the BlogUser.
        	String lastName = req.getParameter("lastname");
        	String firstName = req.getParameter("firstname");
         	String credentials = req.getParameter("credentials");
         	String street1 = req.getParameter("street1");
         	String city = req.getParameter("city");
         	String zip = req.getParameter("zip");
         	String state = req.getParameter("state");
         	String speciality = req.getParameter("speciality");
       
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Doctor doctor = new Doctor(Integer.parseInt(doctorid), lastName, firstName, credentials, street1, city, Integer.parseInt(zip), state, speciality);
	        	doctor = doctorDao.create(doctor);
	        	messages.put("success", "Successfully created " + doctorid);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DoctorCreate.jsp").forward(req, resp);
    }
}

