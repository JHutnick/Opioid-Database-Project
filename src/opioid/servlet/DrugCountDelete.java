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


@WebServlet("/DrugCountDelete")
public class DrugCountDelete extends HttpServlet {
	
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete DrugCount");        
        req.getRequestDispatcher("/DrugCountDelete.jsp").forward(req, resp);
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
            messages.put("title", "Invalid DoctorId");
            messages.put("disableSubmit", "true");
        }  
        else {
        	
	        DrugCount drugcount = new DrugCount(Integer.parseInt(doctorid));
	        try {
	        	drugcount = drugcountDao.delete(drugcount);
	        	// Update the message.
		        if (drugcount == null) {
		            messages.put("title", "Successfully deleted " + drugcount);
		            messages.put("disableSubmit", "true");
		        } else {
		        	messages.put("title", "Failed to delete " + drugcount);
		        	messages.put("disableSubmit", "false");
		        }
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/DrugCountDelete.jsp").forward(req, resp);
    }
}
