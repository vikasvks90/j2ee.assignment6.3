package assignment6.session3.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateSession
 */
@WebServlet("/SessionInfo")
public class SessionInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
        PrintWriter out = response.getWriter();
        String a,b,c;
        a = request.getParameter("firstname");
        b = request.getParameter("lastname");
        c = request.getParameter("email");
          
        //create session
        HttpSession session=request.getSession();
        //print session details
        out.println( "<br>Session Id: " + session.getId() );
        out.println( "<br>Session Creation Time: " + session.getCreationTime() );
        out.println( "<br>Session Last Access Time: " + session.getLastAccessedTime() );
        out.println( "<br>Session Max Inactive Interval: " + session.getMaxInactiveInterval() );
        
        //redirecting to Servlet2, Servlet chaining demo
        out.println("Redirecting to second servlet");
        RequestDispatcher rd = getServletConfig().getServletContext().getRequestDispatcher("/Servlet2");

        if(!(a.equals("") || b.equals("") || c.equals(""))) {
          request.setAttribute("first_name", a);
          request.setAttribute("last_name", b);
          request.setAttribute("email_id", c);
          // include the content of current servlet 
          rd.include(request , response);
        } else {
          //if  any field is null give error
          response.sendError(HttpServletResponse.SC_BAD_REQUEST,"All the given fields are mandatory");
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
