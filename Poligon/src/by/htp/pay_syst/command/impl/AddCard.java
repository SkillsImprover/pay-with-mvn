package by.htp.pay_syst.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.pay_syst.command.Command;
import by.htp.pay_syst.controller.RequestParameterName;
import by.htp.pay_syst.entity.AddCardParameters;
import by.htp.pay_syst.entity.User;
import by.htp.pay_syst.service.Service;
import by.htp.pay_syst.service.ServiceException;
import by.htp.pay_syst.service.ServiceProvider;

public class AddCard implements Command{
	final static Logger logger = Logger.getLogger(AddCard.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Service servProvider = ServiceProvider.getInstance().getSelectService();		
		HttpSession session = request.getSession();
		AddCardParameters param = new AddCardParameters();
		
		String resp;
		int idUser;
		User user;
		
		user = (User)session.getAttribute("user");
		idUser = user.getId();
		
		param.setIdUser(idUser);
		param.setCurrency(Integer.parseInt (request.getParameter(RequestParameterName.REQ_PARAM_ADDCARD_CURRENCY)));
		param.setPaySystCard(Integer.parseInt (request.getParameter(RequestParameterName.REQ_PARAM_ADDCARD_PAYM_SYST_CARD)));
		param.setNameCard(Integer.parseInt (request.getParameter(RequestParameterName.REQ_PARAM_ADDCARD_NAME_CARD)));
		
//		param.setLogin(request.getParameter(RequestParameterName.REQ_PARAM_ADDCARD_LOGIN));
//		param.setPassword(request.getParameter(RequestParameterName.REQ_PARAM_ADDCARD_PASSW));


		try {
			resp = servProvider.addCard(param);
			session.setAttribute("addCard", resp);
			response.sendRedirect("http://localhost:8080/Poligon/controller?command=GO_TO_PAGE_ADD_CARD");
			
			
		} catch (ServiceException e) {
			logger.error("AddCard exception =" + e);
			
			//log
			
		}
		
		
		
//		try {
//			resp = servProvider.addCard(param);
//			request.setAttribute("resp", resp);
//		
//		
//		RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.USER_ADDCARD_PAGE);
//		dispatcher.forward(request, response);
//			
//		} catch (ServiceException e1) {
//			
//			e1.printStackTrace();
//			//log
//			
//		}
		
		
		
		
	}

}
