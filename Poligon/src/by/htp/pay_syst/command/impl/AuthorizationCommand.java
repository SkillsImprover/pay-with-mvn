package by.htp.pay_syst.command.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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



public class AuthorizationCommand implements Command {

	final static Logger logger = Logger.getLogger(AuthorizationCommand.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		Service servProvider = ServiceProvider.getInstance().getSelectService();
		Map<Integer, RequestDispatcher> command = new HashMap<Integer, RequestDispatcher>();
		
		User user;
		
		String login;
		String password;
		
		login = request.getParameter(RequestParameterName.REQ_PARAM_LOGIN);
		password = request.getParameter(RequestParameterName.REQ_PARAM_PASS);
		
		try{
			user = servProvider.authorization(login, password);
			
			
			if(user==null) {
				
				String resp = "Your account haven't found. You need to register.";
				request.setAttribute("noSuchUser", resp);
				RequestDispatcher dispatcher= request.getRequestDispatcher(JSPPageName.START_PAGE_INDEX);
				dispatcher.forward(request, response);
				
			}else {
				
				HttpSession session = request.getSession(true);
				
				session.setAttribute("user", user);
				
				request.setAttribute("user", user);
				command.put(1, request.getRequestDispatcher(JSPPageName.ADMIN_AUTH_PAGE));
				command.put(2, request.getRequestDispatcher(JSPPageName.USER_AUTH_PAGE));
				
				RequestDispatcher dispatcher = command.get(user.getRole());
				dispatcher.forward(request, response);
			}

		}catch(ServiceException e){
			logger.error("AuthorizationCommand exception from service =" + e);
			//log
			
		}
		
	}
	
	

}


//System.out.println(request.getParameter("command"));
//System.out.println(request.getParameter("login"));
//System.out.println(request.getParameter("pass"));

