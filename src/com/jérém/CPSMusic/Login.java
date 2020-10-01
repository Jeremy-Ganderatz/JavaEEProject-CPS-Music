package com.jérém.CPSMusic;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jérém.CPSMusic.DAO.DAOContext;
import com.jérém.CPSMusic.DAO.UsersDAO;
import com.jérém.CPSMusic.enumeration.Sorted;
import com.jérém.CPSMusic.objects.SheetBrowser;
import com.jérém.CPSMusic.objects.Users;

@WebServlet(urlPatterns="/login", loadOnStartup=1)
public class Login extends HttpServlet{
	
	private static final long serialVersionUID = -4319076288258537575L;


	@Override
	public void init() throws ServletException {
		DAOContext.init( this.getServletContext() );
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute( "login", "" );
		request.setAttribute( "password", "" );
		request.setAttribute( "errorMessage", "" );
		request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter( "txtLogin" );
		String password = request.getParameter( "txtPassword" );
		
		request.setAttribute( "login", login );
		request.setAttribute( "password", password );
		
		Users connectedUser = UsersDAO.isValidLogin( login, password );
		
		if ( connectedUser != null ) {
			Sorted sortParameter = Sorted.TOUS;
			HttpSession session = request.getSession( true );
			session.setAttribute( "connectedUser", connectedUser );
			session.setAttribute("instrumentType", sortParameter);
			try {
				session.setAttribute( "sheetBrowser", new SheetBrowser(sortParameter) );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher( "/viewSheetsDetails.jsp" ).forward( request, response );
		
		} else {
		
			request.setAttribute( "errorMessage", "Bad identity" );			
			request.getRequestDispatcher( "/login.jsp" ).forward( request, response );
			
		}
		
	}
}
