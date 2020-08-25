package com.jérém.CPSMusic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleServlet
 */
@WebServlet(urlPatterns="/lifeCycleServlet", loadOnStartup=1)
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int counter;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    /*public LifeCycleServlet() {
        super();
        System.out.println("Servlet created");
    }
    */
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	counter = 0;
    	//String dataBaseURL = this.getServletContext().getInitParameter("DATABASE_URL");
    	System.out.println("in init---------");
    	}
    
    /*
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	super.service(req, resp);
    }
    */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Demo", "A value");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		counter++;
		System.out.println("in the doGet" + counter);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("in destroy");
	}

}
