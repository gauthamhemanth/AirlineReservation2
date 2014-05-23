package servlets;
import javamodel.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
        super();
       
    }
	  public void init() throws ServletException {
	       
	        
	      }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	int checkstatus;
	String uName=request.getParameter("uname");	
	String password=request.getParameter("password");
	String unamesession=(String) session.getAttribute("Username");

	Usersjava u=new Usersjava();
	if(password!=null && uName!=null){
		checkstatus=u.getUserName(uName,password);

		if(checkstatus==1)
		{
			if(unamesession==null)
			{
				session.setAttribute("Username", uName);
			}
			response.sendRedirect("/AirlineReservation2/QueryPage.jsp");
			
			return;
		
		}	
	
		if(checkstatus==-1)
		{
			response.sendRedirect("/AirlineReservation2/LoginPage.jsp?Error=usernamedoesnotexist");
			return;
		}
		if(checkstatus==0)
		{
			response.sendRedirect("/AirlineReservation2/LoginPage.jsp?Error=wrongpassword");
			return;
		}
	
	}
	}
}


