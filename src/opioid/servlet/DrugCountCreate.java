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


@WebServlet("/DrugCountCreate")
public class DrugCountCreate extends HttpServlet {
	
	protected DrugCountDao drugcountDao;
	
	@Override
	public void init() throws ServletException {
		drugcountDao = DrugCountDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/DrugCountCreate.jsp").forward(req, resp);
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
        }
        else {
        	
        	String ersupply = req.getParameter("ersupply");
        	String opioidsupply = req.getParameter("opioidsupply");
        	String doctorid1 = req.getParameter("doctorid"); //don't know if this is right
        	
//        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        	String stringDob = req.getParameter("dob");
//        	Date dob = new Date();
//        	try {
//        		dob = dateFormat.parse(stringDob);
//        	} catch (ParseException e) {
//        		e.printStackTrace();
//				throw new IOException(e);
//        	}
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	DrugCount drugCount = new DrugCount(Integer.parseInt(doctorid1), Integer.parseInt(opioidsupply), Integer.parseInt(ersupply));
	        	drugCount = drugcountDao.create(drugCount);
	        	messages.put("success", "Successfully created " + drugCount);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DrugCountCreated.jsp").forward(req, resp);
    }
}
