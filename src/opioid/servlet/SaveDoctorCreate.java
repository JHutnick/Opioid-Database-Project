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


@WebServlet("/savedoctorcreate")
public class SaveDoctorCreate extends HttpServlet {
	
	protected SaveDoctorDao saveDoctorDao;
	
	@Override
	public void init() throws ServletException {
		saveDoctorDao = SaveDoctorDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/SaveDoctorCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String username = req.getParameter("username");
        if (username == null || username.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the BlogUser.
        	String doctorid = req.getParameter("doctorid");
        	String password = req.getParameter("password");
       
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	SaveDoctor saveDoctor = new SaveDoctor(username, Integer.parseInt(doctorid), password);
	        	saveDoctor = saveDoctorDao.create(saveDoctor);
	        	messages.put("success", "Successfully created " + username);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/SaveDoctorCreate.jsp").forward(req, resp);
    }
}
