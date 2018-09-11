package opioid.servlet;

import opioid.dal.*;
import opioid.model.*;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/DeathDataDelete")
public class DeathDataDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete DeathData");        
        req.getRequestDispatcher("/DeathDataDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String deathrate = req.getParameter("deathRateRangeLow");
        if (deathrate == null || deathrate.trim().isEmpty()) {
            messages.put("title", "Invalid countyName");
            messages.put("disableSubmit", "true");
        } else {
        	
	        DeathData deathdata = new DeathData(Double.parseDouble(deathrate)); //should I leave this in here....? not sure if I"m doing this correctly
	        try {
	        	deathdata = deathdatadao.delete(deathdata);
	        	// Update the message.
		        if (deathdata == null) {
		            messages.put("title", "Successfully deleted " + deathdata);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + deathdata);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DeathDataDelete.jsp").forward(req, resp);
    }
}
