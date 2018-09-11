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


@WebServlet("/DrugDataCreate")
public class DrugDataCreate extends HttpServlet {
	
	protected DrugDataDao drugdataDao;
	
	@Override
	public void init() throws ServletException {
		drugdataDao = DrugDataDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/DrugDataCreate.jsp").forward(req, resp);
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
        	
        	String opioidpr = req.getParameter("opioidpresciberrate");
        	String totalds = req.getParameter("totaldaysupply");
        	String eropioidr = req.getParameter("eropioidrate");
        	String doctorid1 = req.getParameter("doctorid"); //don't know if this is right
   
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	DrugData drugdata = new DrugData(Integer.parseInt(doctorid1), Integer.parseInt(totalds), Integer.parseInt(opioidpr), Integer.parseInt(eropioidr));
	        	drugdata = drugdataDao.create(drugdata);
	        	messages.put("success", "Successfully created " + drugdataDao);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DrugDataCreate.jsp").forward(req, resp);
    }
}
