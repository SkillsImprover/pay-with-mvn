package by.htp.pay_syst.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.pay_syst.command.Command;
import by.htp.pay_syst.controller.RequestParameterName;
import by.htp.pay_syst.entity.User;
import by.htp.pay_syst.service.Service;
import by.htp.pay_syst.service.ServiceException;
import by.htp.pay_syst.service.ServiceProvider;

public class CorrectionResidenceRegistration implements Command {

	final static Logger logger = Logger.getLogger(CorrectionResidenceRegistration.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Service servProvider = ServiceProvider.getInstance().getSelectService();
		HttpSession session = request.getSession();
		
		if (session != null) {
	    	
		      if (session.getAttribute("correctionPhoneNumber") != null) {
		        session.removeAttribute("correctionPhoneNumber");
		      }
		      
		      if (session.getAttribute("correctionPassword") != null) {
			        session.removeAttribute("correctionPassword");
			      }
			      
		      if (session.getAttribute("correctionName") != null) {
			        session.removeAttribute("correctionName");
			      }
			      
		      if (session.getAttribute("correctionLogin") != null) {
			        session.removeAttribute("correctionLogin");
			      }
			      
		      if (session.getAttribute("correctionCodeWord") != null) {
			        session.removeAttribute("correctionCodeWord");
			      }
			      
		      if (session.getAttribute("correctionAddress") != null) {
			        session.removeAttribute("correctionAddress");
			      }
			      
		      if (session.getAttribute("addCard") != null) {
			        session.removeAttribute("addCard");
			      }
			      
		      if (session.getAttribute("correctionSurname") != null) {
			        session.removeAttribute("correctionSurname");
			      }
		      
		      if (session.getAttribute("getAccountsAndCards") != null) {
			        session.removeAttribute("getAccountsAndCards");
			      }
		      
		      if (session.getAttribute("payment") != null) {
			        session.removeAttribute("payment");
			      }
		      
		      if (session.getAttribute("transferBetweenUsersCards") != null) {
			        session.removeAttribute("transferBetweenUsersCards");
			      }
		      
		      
			}
		
		
		User user;
		String resp;
		String newResidenceRegistr;
		int idUser;
		
		user = (User)session.getAttribute("user");
		idUser = user.getId();
		newResidenceRegistr = request.getParameter(RequestParameterName.REQ_PARAM_CORRECT_RESIDENCE_REGISTR); 
		
		try{
			resp = servProvider.correctionResidenceRegistr(newResidenceRegistr, idUser);
			session.setAttribute("correctionResidenceRegistr", resp);
			response.sendRedirect("http://localhost:8080/Poligon/controller?command=GO_TO_CORRECT_DATA");
			
			
//			request.setAttribute("resp2", resp);			
//			RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.ADMIN_GET_NAME_CARDS);
//			dispatcher.forward(request, response);
			
			
		}catch(ServiceException e){

			logger.error("CorrectionResidenceRegistration exception from service =" + e);
		}
		
		
	}

}
