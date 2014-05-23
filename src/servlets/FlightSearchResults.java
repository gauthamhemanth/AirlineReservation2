package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javamodel.Flights;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class FlightSearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Flights> flightresult= new ArrayList<Flights>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FlightSearchResults() {
        super();
        
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Flights flight=new Flights();
		HttpSession session=request.getSession();
		int flightid = Integer.parseInt(request.getParameter("viewandbook"));
		String classtype=(String) session.getAttribute("travelclass");//Integer.parseInt(request.getParameter(123));
		flight.setFlightNumber(flightid);
		
		flight.setTravelclass(classtype);
		try {
			flightresult=flight.getcompletedetails();
			session.setAttribute("flightid", flightid);
			request.setAttribute("data1",flightresult);
			//response.sendRedirect("/AirlineReservation2/viewandbook.jsp");
			RequestDispatcher rd=request.getRequestDispatcher("/viewandbook.jsp");
			rd.forward(request, response);
			return;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
