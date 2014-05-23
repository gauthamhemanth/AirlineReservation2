package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javamodel.Bookings;
import javamodel.Flights;


public class BookingHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ArrayList<Flights> bookinghistory= new ArrayList<Flights>();   
 
    public BookingHistory() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	Bookings userbookings=new Bookings();
	HttpSession session = request.getSession();
	String uname=(String) session.getAttribute("Username");
	if(uname!=null)
	{
		try {
			bookinghistory = userbookings.getBookingHistory(uname);
			request.setAttribute("data",bookinghistory);
			RequestDispatcher rd=request.getRequestDispatcher("/BookingHistory.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
	
	
	}

}
