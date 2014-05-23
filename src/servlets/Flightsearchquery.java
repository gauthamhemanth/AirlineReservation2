package servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javamodel.Flights;
import javax.servlet.http.HttpSession;
public class Flightsearchquery extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String source;
    String destination;
    String dateoftravel;
    String flightclass;
    int numberofseats;
    ResultSet rs;
    ArrayList<Flights> flightresult= new ArrayList<Flights>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Flightsearchquery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	HttpSession session = request.getSession();
	
	Flights flight=new Flights();
	
	if (request.getParameter("search") != null){
	source=	request.getParameter("source");
	destination=	request.getParameter("destination");
	dateoftravel=	request.getParameter("dateoftravel");
	numberofseats= Integer.parseInt(request.getParameter("seatsvalue"));
	flightclass=	request.getParameter("classvalue");
	
	flight.setSource(source);
	flight.setDestination(destination);
	flight.setDateoftravel(dateoftravel);
	flight.setSeatsrequired(numberofseats);
	flight.setTravelclass(flightclass);
	session.setAttribute("travelclass", flightclass);	
	try {
		flightresult=flight.searchflights();
		
			
			request.setAttribute("data",flightresult);
			RequestDispatcher rd=request.getRequestDispatcher("/flightsearchresults.jsp");
			rd.forward(request, response);
			return;
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	/*PrintWriter out= response.getWriter();*/ 
					    
			    
		}
		else if(request.getParameter("bookinghistory") != null)
		{
			response.sendRedirect("BookingHistory.jsp");
		}
	}

}
