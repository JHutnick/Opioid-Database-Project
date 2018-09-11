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


@WebServlet("/CountyCreate")
public class CountyCreate extends HttpServlet {
	
	protected CountyDao countyDao;
	
	@Override
	public void init() throws ServletException {
		countyDao = CountyDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CountyCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countyName = req.getParameter("countyname");
        if (countyName == null || countyName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	
        	String state = req.getParameter("state");
        	String city = req.getParameter("city");
        	String zip = req.getParameter("zip");

	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	County county = new County(Integer.parseInt(zip), city, state, countyName);
	        	county = countyDao.create(county);
	        	messages.put("success", "Successfully created " + countyName);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }
}
