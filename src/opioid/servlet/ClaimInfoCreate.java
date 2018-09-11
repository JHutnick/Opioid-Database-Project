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


@WebServlet("/claiminfocreate")
public class ClaimInfoCreate extends HttpServlet {
	
	protected ClaimInfoDao claiminfoDao;
	
	@Override
	public void init() throws ServletException {
		claiminfoDao = ClaimInfoDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/ClaimInfoCreate.jsp").forward(req, resp);
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
        	String total = req.getParameter("totalclaimcount");
        	String dayfill30 = req.getParameter("30dayfill");
         	String brand = req.getParameter("brandclaimcount");
         	String generic = req.getParameter("genericclaim");
         	String opioid = req.getParameter("opioidclaim");
         	String er = req.getParameter("eropioidclaim");
         	String anti = req.getParameter("anticlaim");
       
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	ClaimInfo claim = new ClaimInfo(Integer.parseInt(doctorid), Integer.parseInt(total), Integer.parseInt(dayfill30),
	        			Integer.parseInt(brand), Integer.parseInt(generic), Integer.parseInt(opioid), Integer.parseInt(er),
	        			Integer.parseInt(anti));
	        	claim = claiminfoDao.create(claim);
	        	messages.put("success", "Successfully created " + doctorid);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/ClaimInfoCreate.jsp").forward(req, resp);
       
    }
}

