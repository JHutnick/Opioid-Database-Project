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


@WebServlet("/beneficiaryinfocreate")
public class BeneficiaryInfoCreate extends HttpServlet {
	
	protected BeneficiaryInfoDao beninfoDao;
	
	@Override
	public void init() throws ServletException {
		beninfoDao = BeneficiaryInfoDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/BeneficiaryInfoCreate.jsp").forward(req, resp);
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
        	String ben = req.getParameter("benecount");
        	String opi = req.getParameter("opibencount");
         	String er = req.getParameter("eropibencount");
         	String anti = req.getParameter("antibiobencount");
         	String ageL = req.getParameter("benagel65");
         	String ageG = req.getParameter("benageg65");
         	String avgAge = req.getParameter("avgageben");
         	String benF = req.getParameter("benf");
         	String benM = req.getParameter("benm");
         	String nDual = req.getParameter("benndual");
         	String dual = req.getParameter("bendual");
         	String risk = req.getParameter("benavgrisk");
       
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	BeneficiaryInfo benF1 = new BeneficiaryInfo(Integer.getInteger(doctorid), Integer.getInteger(ben), Integer.getInteger(opi),
	        			Integer.getInteger(er), Integer.getInteger(anti), Integer.getInteger(ageL), Double.parseDouble(ageG),
	        			Integer.getInteger(avgAge),Integer.getInteger(benF), Integer.getInteger(benM), Integer.getInteger(nDual), Integer.getInteger(dual),
	        			Double.parseDouble(risk));
	        	benF1 = beninfoDao.create(benF1);
	        	messages.put("success", "Successfully created " + doctorid);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/BeneficiaryInfoCreate.jsp").forward(req, resp);
       
    }
}

