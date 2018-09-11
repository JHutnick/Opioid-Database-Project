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


@WebServlet("/DeathDataCreate")
public class DeathDataCreate extends HttpServlet {
	
	protected DeathDataDao deathdatadao;
	
	@Override
	public void init() throws ServletException {
		deathdatadao = DeathDataDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/DeathDataCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String countyName = req.getParameter("getcounty");
        if (countyName == null || countyName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	
        	String state = req.getParameter("state");
        	String county = req.getParameter("county");
        	String year = req.getParameter("year"); //not sure if this is right..
        	String population = req.getParameter("population");
        	String deathdatarate = req.getParameter("deathraterangelow"); //don't know if this is right
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	DeathData deathdata = new DeathData(Integer.parseInt(year), Integer.parseInt(population), Double.parseDouble(deathdatarate), county, state);
	        	deathdata = deathdatadao.create(deathdata);
	        	messages.put("success", "Successfully created " + deathdata);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeathDataCreate.jsp").forward(req, resp);
    }
}
