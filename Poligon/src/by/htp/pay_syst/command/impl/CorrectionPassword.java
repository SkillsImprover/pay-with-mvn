package by.htp.pay_syst.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import by.htp.pay_syst.command.Command;
import by.htp.pay_syst.controller.JSPPageName;
import by.htp.pay_syst.controller.RequestParameterName;
import by.htp.pay_syst.entity.User;
import by.htp.pay_syst.service.Service;
import by.htp.pay_syst.service.ServiceException;
import by.htp.pay_syst.service.ServiceProvider;

public class CorrectionPassword implements Command {

	final static Logger logger = Logger.getLogger(CorrectionPassword.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Service servProvider = ServiceProvider.getInstance().getSelectService();
		HttpSession session = request.getSession();
		
		User user;
		String newPassword1;
		String newPassword2;
		String oldPassword;
		String resp;
		int idUser;
		
		user = (User)session.getAttribute("user");
		newPassword1 = request.getParameter(RequestParameterName.REQ_PARAM_CORRECT_NEW_PASSW_1);
		newPassword2 = request.getParameter(RequestParameterName.REQ_PARAM_CORRECT_NEW_PASSW_2);
		oldPassword = request.getParameter(RequestParameterName.REQ_PARAM_CORRECT_OLD_PASSW);
		idUser = user.getId();
		
		try{
			if(newPassword1.equals(newPassword2)){
				resp = servProvider.correctionPassword(newPassword1, oldPassword, idUser);
				session.setAttribute("correctionPassword", resp);
				response.sendRedirect("http://localhost:8080/Poligon/controller?command=GO_TO_CORRECTION_PASSWORD");
			}else {
				resp="newPassword1 not equals newPassword2, try again";
				request.setAttribute("mistake", resp);
				RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.USER_CORRECTION_PASSWORD);
				dispatcher.forward(request, response);
			}
			
//			request.setAttribute("resp2", resp);			
//			RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.ADMIN_GET_NAME_CARDS);
//			dispatcher.forward(request, response);
			
			
		}catch(ServiceException e){

			logger.error("CorrectionPassword exception from service =" + e);
		}
		
	}

}
