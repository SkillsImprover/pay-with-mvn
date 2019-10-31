package by.htp.pay_syst.command.impl;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.pay_syst.command.Command;
import by.htp.pay_syst.controller.RequestParameterName;
import by.htp.pay_syst.dao.impl.MySQLDAO;
import by.htp.pay_syst.service.Service;
import by.htp.pay_syst.service.ServiceException;
import by.htp.pay_syst.service.ServiceProvider;

public class AccountRefill implements Command {
	final static Logger logger = Logger.getLogger(AccountRefill.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		Service servProvider = ServiceProvider.getInstance().getSelectService();
		
		int idCard;
		int amount;
		String resp;
		
		idCard = Integer.parseInt (request.getParameter(RequestParameterName.REQ_PARAM_REFILL_ID_CARD));
		amount = Integer.parseInt (request.getParameter(RequestParameterName.REQ_PARAM_REFILL_ID_AMOUNT));
		
		
		try{
			resp = servProvider.accountFerill(idCard, amount);
			session.setAttribute("accountRefill", resp);
			response.sendRedirect("http://localhost:8080/Poligon/controller?command=GO_TO_REFILL");
			
		}catch(ServiceException e){
			logger.error("AccountRefill exception from service =" + e);
			
		}
		
		
//		try{
//			String resp = servProvider.accountFerill(idCard, amount);
//			request.setAttribute("resp", resp);
//			
//			
//			RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.USER_REFILL_PAGE);
//			dispatcher.forward(request, response);
			
			
//		}catch(ServiceException e){
//			
//		}
		
		
		
	}

}
