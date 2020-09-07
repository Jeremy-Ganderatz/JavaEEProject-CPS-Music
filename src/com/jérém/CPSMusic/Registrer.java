package com.jérém.CPSMusic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jérém.CPSMusic.DAO.UsersDAO;

/**
 * Servlet implementation class Registrer
 */
@WebServlet(urlPatterns = "/registrer", loadOnStartup=1)
public class Registrer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute( "login", "" );
		request.setAttribute( "password", "" );
		request.setAttribute( "errorMessage", "" );
		request.getRequestDispatcher( "/registrer.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter( "txtLogin" );
		String password = request.getParameter( "txtPassword" );
		
		request.setAttribute( "login", login );
		request.setAttribute( "password", password );
		
		UsersDAO.createAccount( login, password );
		request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

}
