package by.htp.pay_syst.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.pay_syst.command.Command;
import by.htp.pay_syst.controller.RequestParameterName;
import by.htp.pay_syst.service.Service;
import by.htp.pay_syst.service.ServiceException;
import by.htp.pay_syst.service.ServiceProvider;

public class CorrectIdentificNumberPassport implements Command {

	final static Logger logger = Logger.getLogger(CorrectIdentificNumberPassport.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Service servProvider = ServiceProvider.getInstance().getSelectService();
		
		
	    
	    if (session != null) {
	    	
	      if (session.getAttribute("getAccounts") != null) {
	        session.removeAttribute("getAccounts");
	      }
	      
	      if (session.getAttribute("toUnlockAccount") != null) {
		        session.removeAttribute("toUnlockAccount");
		      }
		      
	      if (session.getAttribute("registrationAdmin") != null) {
		        session.removeAttribute("registrationAdmin");
		      }
		      
	      if (session.getAttribute("toLockAccount") != null) {
		        session.removeAttribute("toLockAccount");
		      }
		      
	      if (session.getAttribute("deleteNameCard") != null) {
		        session.removeAttribute("deleteNameCard");
		      }
		      
	      if (session.getAttribute("correctionSeriesNumberPassport") != null) {
		        session.removeAttribute("correctionSeriesNumberPassport");
		      }
		      
	      if (session.getAttribute("addNewNameCard") != null) {
		        session.removeAttribute("addNewNameCard");
		      }
		      
	      if (session.getAttribute("accountRefill") != null) {
		        session.removeAttribute("accountRefill");
		      }
		}
		
		
		String newIdentificNumbPassport;
		String resp;
		int idUser;
		
		newIdentificNumbPassport = request.getParameter(RequestParameterName.REQ_PARAM_CORRECT_IDENTIFIC_NUMB_PASSPORT);
		idUser = Integer.parseInt (request.getParameter(RequestParameterName.REQ_PARAM_CORRECT_IDENTIFIC_NUMB_PASSPORT_ID_USER));
		
//		HttpSession session = request.getSession();
//		User user = (User)session.getAttribute("user");
//		idUser = user.getId();
		
		try{
			
			resp = servProvider.correctionIdentificNumberPassport(newIdentificNumbPassport, idUser);
			
			session.setAttribute("correctIdentificNumbPassp", resp);
			response.sendRedirect("http://localhost:8080/Poligon/controller?command=GO_TO_ADMIN_CORRECTION_DATA");
			
//			request.setAttribute("resp1", resp);
//			RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.ADMIN_CORRECT_DATA_USER);
//			dispatcher.forward(request, response);
			
			
		}catch(ServiceException e){
			
			logger.error("CorrectIdentificNumberPassport exception from service =" + e);
		}
		
		
	}

}
