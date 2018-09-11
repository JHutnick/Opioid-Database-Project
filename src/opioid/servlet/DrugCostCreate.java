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


@WebServlet("/DrugCostCreate")
public class DrugCostCreate extends HttpServlet {
	
	protected DrugCostDao drugcostDao;
	
	@Override
	public void init() throws ServletException {
		drugcostDao = DrugCostDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/DrugCostCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String doctorid = req.getParameter("doctorId");
        if (doctorid == null || doctorid.trim().isEmpty()) {
            messages.put("success", "Invalid DoctorId");
        }else {
        	
        	String DoctorId = req.getParameter("doctorid");
        	String TotalDrugCost = req.getParameter("totaldrugcost");
        	String BrandDrugCost = req.getParameter("branddrugcost");
        	String GenericDrugCost = req.getParameter("genericdrugcost");
        	String OpioidDrugCost = req.getParameter("opioiddrugcost");
        	String ErOpioidDrugCost = req.getParameter("eropioiddrugcost");   	
        	String AntiDrugCost = req.getParameter("antidrugcost"); 
        	
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	DrugCost drugcost = new DrugCost(Integer.parseInt(DoctorId), Double.parseDouble(TotalDrugCost), Double.parseDouble(BrandDrugCost), 
	        			Double.parseDouble(GenericDrugCost), Double.parseDouble(OpioidDrugCost), Double.parseDouble(ErOpioidDrugCost), Double.parseDouble(AntiDrugCost) );
	        	drugcost = drugcostDao.create(drugcost);
	        	messages.put("success", "Successfully created " + drugcost);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DrugCostCreate.jsp").forward(req, resp);
    }
}
