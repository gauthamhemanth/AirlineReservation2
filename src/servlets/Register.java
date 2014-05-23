package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javamodel.Usersjava;;



public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public Register() {
        super();
     
    }

    public void init() throws ServletException {
    	        
      }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usersjava u = new Usersjava();
		String uName=request.getParameter("unamereg");	
		String password=request.getParameter("passwordreg");
		String name=request.getParameter("Name");
		boolean registration_done;
		if(uName!=null&&password!=null)
		{
			try {
				registration_done=	u.Register(name,uName,password);
				if(registration_done)
				{
				response.sendRedirect("");
				}
				else
				{
					response.sendRedirect("");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
		}
		
		}
		
		}
	
