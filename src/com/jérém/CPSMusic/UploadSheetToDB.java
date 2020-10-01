package com.jérém.CPSMusic;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.jérém.CPSMusic.DAO.SheetDAO;
import com.jérém.CPSMusic.objects.Users;

/**
 * Servlet implementation class UploadSheetToDB
 */
@WebServlet("/uploadSheet")
@MultipartConfig(maxFileSize = 20848820)
public class UploadSheetToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// database connection settings
    //private String dbURL = "jdbc:mysql://localhost:3306/AppDB";
    //private String dbUser = "root";
    //private String dbPass = "secret";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession( true );
		if ( session.getAttribute( "connectedUser" ) == null ) {
			response.sendRedirect( "login" );
			return;
		}
		
		request.getRequestDispatcher( "/uploadSheetToDB.jsp" ).forward( request, response );
	}
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession( true );
		if ( session.getAttribute( "connectedUser" ) == null ) {
			response.sendRedirect( "login" );
			return;
		}
        // gets values of text fields
        String sheetName = request.getParameter("sheetName");
        String instrumentType = request.getParameter("instrumentType");
        String originalArtistName = request.getParameter("originalArtistName");
        int price = Integer.parseInt(request.getParameter("price"));
        //Blob dataSheet = request.getInputStream(photo);
         
        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            //System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            //System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        
        Users connectedUser =  (Users) session.getAttribute("connectedUser");
        int idUser = connectedUser.getIdUser();
        
        SheetDAO.addSheet(sheetName, instrumentType, originalArtistName, inputStream, idUser, price);
        
        String message = "File uploaded and saved into database";  // message will be sent back to client
         
            // sets the message in request scope
            request.setAttribute("Message", message);
             
            // forwards to the message page
            getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    	//}
    }
}