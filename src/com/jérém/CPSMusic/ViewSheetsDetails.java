package com.jérém.CPSMusic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jérém.CPSMusic.objects.SheetBrowser;

@WebServlet( "/viewSheetsDetails" )
public class ViewSheetsDetails extends HttpServlet {

	private static final long serialVersionUID = 550038282401302959L;


	@Override
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession( true );
		if ( session.getAttribute( "connectedUser" ) == null ) {
			response.sendRedirect( "login" );
			return;
		}
		
		request.getRequestDispatcher( "/viewSheetsDetails.jsp" ).forward( request, response );
	}
	
	@Override
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session = request.getSession( true );
		if ( session.getAttribute( "connectedUser" ) == null ) {
			response.sendRedirect( "login" );
			return;
		}

		SheetBrowser browser = (SheetBrowser) session.getAttribute( "sheetBrowser" );
		
		if ( request.getParameter( "btnPrevious" ) != null ) {
			browser.goPrevious();
		} else if ( request.getParameter( "btnNext" ) != null ) {
			browser.goNext();
		} else if ( request.getParameter( "btnAdd" ) != null ) {
			browser.addCurrentSheet();
		}
		
		request.getRequestDispatcher( "/viewSheetsDetails.jsp" ).forward( request, response );
	}
	
}
