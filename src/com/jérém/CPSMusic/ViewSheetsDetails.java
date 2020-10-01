package com.jérém.CPSMusic;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jérém.CPSMusic.enumeration.Sorted;
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
		
		Sorted instrumentType = (Sorted)session.getAttribute("instrumentType");
		Sorted price = (Sorted)session.getAttribute("price");
		
		//tri par instrument
		if (request.getParameter( "TOUS" ) != null  && session.getAttribute("instrumentType") != Sorted.TOUS) {
			session.setAttribute("instrumentType", Sorted.TOUS);
			instrumentType = (Sorted)session.getAttribute("instrumentType");
			newBrowser(request, instrumentType);
			System.out.println("Tous");
		}
		if (request.getParameter( "PIANO" ) != null) {
			session.setAttribute("instrumentType", Sorted.PIANO);
			instrumentType = (Sorted)session.getAttribute("instrumentType");
			newBrowser(request, instrumentType);
			System.out.println("Piano");
		}
		if (request.getParameter( "VIOLON" ) != null) {
			session.setAttribute("instrumentType", Sorted.VIOLON);
			instrumentType = (Sorted)session.getAttribute("instrumentType");
			newBrowser(request, instrumentType);
			System.out.println("Violon");
		}
		if (request.getParameter( "CLARINETTE" ) != null) {
			session.setAttribute("instrumentType", Sorted.CLARINETTE);
			instrumentType = (Sorted)session.getAttribute("instrumentType");
			newBrowser(request, instrumentType);
			System.out.println("Clarinette");
		}
		if (request.getParameter( "GUITARE" ) != null) {
			session.setAttribute("instrumentType", Sorted.GUITARE);
			instrumentType = (Sorted)session.getAttribute("instrumentType");
			newBrowser(request, instrumentType);
			System.out.println("Guitare");
		}
		
		// tri par prix
		
		if (request.getParameter( "ZEROACINQ" ) != null) {
			session.setAttribute("price", Sorted.ZEROACINQ);
			price = (Sorted)session.getAttribute("price");
			newBrowser(request, price);
			System.out.println("ZEROACINQ");
		}
		
		if (request.getParameter( "CINQADIX" ) != null) {
			session.setAttribute("price", Sorted.CINQADIX);
			price = (Sorted)session.getAttribute("price");
			newBrowser(request, price);
			System.out.println("CINQADIX");
		}
		
		if (request.getParameter( "DIXAVINGT" ) != null) {
			session.setAttribute("price", Sorted.DIXAVINGT);
			price = (Sorted)session.getAttribute("price");
			newBrowser(request, price);
			System.out.println("DIXAVINGT");
		}
		
		if (request.getParameter( "VINGTETPLUS" ) != null) {
			session.setAttribute("price", Sorted.VINGTETPLUS);
			price = (Sorted)session.getAttribute("price");
			newBrowser(request, price);
			System.out.println("VINGTETPLUS");
		}
		
		
		if ( request.getParameter( "btnPrevious" ) != null ) {
			try {
				browser.goPrevious();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ( request.getParameter( "btnNext" ) != null ) {
			try {
				browser.goNext();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ( request.getParameter( "btnAdd" ) != null ) {
			browser.addCurrentSheet();
		}
		
		
		request.getRequestDispatcher( "/viewSheetsDetails.jsp" ).forward( request, response );
	}
	
	private void newBrowser(HttpServletRequest request, Sorted object) throws IOException {
		HttpSession session = request.getSession( true );
		try {
			session.setAttribute( "sheetBrowser", new SheetBrowser(object) );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
