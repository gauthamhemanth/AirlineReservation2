package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javamodel.Flights;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewandBook
 */
@WebServlet("/ViewandBook")
public class ViewandBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Flights> flightresult= new ArrayList<Flights>();
    /**
     * Default constructor. 
     */
    public ViewandBook() {
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		String buttonselected=request.getParameter("common");
		
	
		 if(buttonselected.equalsIgnoreCase("Select"))
		
		{
		Flights flight=new Flights();
		int flightid=(int) session.getAttribute("flightid");
		
		String classtype=(String) session.getAttribute("travelclass");
		int SeatsSelected=Integer.parseInt(request.getParameter("seatsvalue"));
		try {
			flightresult=flight.checkavailableseats(flightid,SeatsSelected,classtype);
			if(flightresult.size()>0)
			{
			
				request.setAttribute("data", flightresult);
				if(session.getAttribute("FlightDetailsList")==null)
				{
				session.setAttribute("FlightDetailsList", flightresult);
				}
				else
				{
					
					flightresult.addAll((ArrayList<Flights>)session.getAttribute("FlightDetailsList"));
					session.setAttribute("FlightDetailsList", flightresult);
					
				}
				RequestDispatcher rd=request.getRequestDispatcher("/Transaction.jsp"); 
				rd.forward(request, response);
				return;
					
				
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		else if(buttonselected.equalsIgnoreCase("AddMoreFlights"))
		{
			
			Flights flight=new Flights();
			int flightid=(int) session.getAttribute("flightid");
			
			String classtype=(String) session.getAttribute("travelclass");
			int SeatsSelected=Integer.parseInt(request.getParameter("seatsvalue"));
			try {
				flightresult=flight.checkavailableseats(flightid,SeatsSelected,classtype);
				
				if(flightresult.size()>0)
				{
					
					request.setAttribute("data", flightresult);
					if(session.getAttribute("FlightDetailsList")==null)
					{
					session.setAttribute("FlightDetailsList", flightresult);
					}
					else
					{
						
						flightresult.addAll((ArrayList<Flights>)session.getAttribute("FlightDetailsList"));
						session.setAttribute("FlightDetailsList", flightresult);
						
					}
					RequestDispatcher rd=request.getRequestDispatcher("/QueryPage.jsp"); 
					rd.forward(request, response);
					return;
						
					
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
