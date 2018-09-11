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

//import blog.model.BlogUsers;


@WebServlet("/findcounty")
public class FindCounty extends HttpServlet {
	
	protected CountyDao countyDao;
	
	@Override
	public void init() throws ServletException {
		countyDao = countyDao.getInstance();
	}
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
//        County county = null;
        List<County> counties = new ArrayList<County>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String countyname = req.getParameter("countyname");
        if (countyname == null || countyname.trim().isEmpty()) {
            messages.put("success", "Please enter a valid county name.");
        } else {

        	try {
        		counties = countyDao.getCountyByCountyName(countyname);

            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + countyname);
        	messages.put("County", countyname);
        }
        req.setAttribute("counties", counties);
        
        req.getRequestDispatcher("/FindCounty.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<County> counties = new ArrayList<County>();
        
        // Retrieve and validate name.
        String countyname = req.getParameter("countyname");
        if (countyname == null || countyname.trim().isEmpty()) {
            messages.put("success", "Invalid countyname lol");
        } else {
     	
	        try {

	        	counties = countyDao.getCountyByCountyName(countyname);

		        messages.put("success", "Displaying teh results for " + countyname);
		        messages.put("county name", countyname);

	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.setAttribute("counties", counties);
        
        req.getRequestDispatcher("/FindCounty.jsp").forward(req, resp);
    }
}
