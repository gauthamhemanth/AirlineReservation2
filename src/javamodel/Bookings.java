package javamodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Bookings {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet rs;
	int userid;
	public ArrayList<Flights> getBookingHistory(String username) throws ClassNotFoundException, SQLException
	{
		
		ArrayList<Flights> BookingDetails=new ArrayList<Flights>();
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
		preparedStatement = con.prepareStatement("SELECT USERID FROM USERS where USERNAME = (?)");
		preparedStatement.setString(1, username);
		rs=preparedStatement.executeQuery();
		if(rs.next())
		{
		userid=rs.getInt(1);
		}
		preparedStatement = con.prepareStatement("SELECT * FROM RESERVATIONS, FLIGHTS, FLIGHTCLASS where FLIGHTS.FLIGHTID = FLIGHTCLASS.FLIGHTID and FLIGHTCLASS.FLIGHTCLASS=RESERVATIONS.FLIGHTCLASS AND USERID = (?) and RESERVATIONS.FLIGHTID=Flights.FLIGHTID");
		preparedStatement.setInt(1, userid);
		rs=preparedStatement.executeQuery();
		while(rs.next())
		{
			Flights fd=new Flights();
			fd.setSource(rs.getString("SOURCE"));
			fd.setDestination(rs.getString("DESTINATION"));
			fd.setSeatsBooked(rs.getInt("SEATSBOOKED"));
			fd.setDateOfBooking(rs.getString("DATEOFBOOKING"));
			fd.setDateoftravel(rs.getString("DATEOFTRAVEL"));
			fd.setTravelclass(rs.getString("FLIGHTCLASS"));
			fd.setFlightNumber(rs.getInt("FLIGHTID"));
			fd.setPrice(rs.getInt("AMOUNT"));
			fd.setDepttime(rs.getString("DEPARTURETIME"));
			fd.setArrtime(rs.getString("ARRIVALTIME"));
			fd.setNumberOfStops(rs.getInt("NUMBEROFSTOPS"));
			BookingDetails.add(fd);
			
		}
		return BookingDetails;
	}

	public void updatedatabase(ArrayList<Flights> ss,String username) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
		preparedStatement=con.prepareStatement("SELECT USERID from USERS where USERNAME=(?)");
		preparedStatement.setString(1, username);
		rs=preparedStatement.executeQuery();
		Calendar calendar = Calendar.getInstance();
		int maxid=0;
		int userid=0;
		while(rs.next())
		{
		userid=rs.getInt("USERID");
		}
		for(int i=0;i<ss.size();i++)
		{
		preparedStatement = con.prepareStatement("select max(RESERVATIONID) from RESERVATIONS");	
		rs=preparedStatement.executeQuery();
		while(rs.next()){
		 maxid=rs.getInt(1)+1;
		}
		//updating reservation table below
		preparedStatement = con.prepareStatement("insert into RESERVATIONS values((?),(?),(?),(?),(?),(?),(?))");
		preparedStatement.setInt(1, userid);
		preparedStatement.setInt(2, ss.get(i).getFlightNumber());
		preparedStatement.setString(3, ss.get(i).getTravelclass());
		preparedStatement.setString(4, calendar.getTime().toString());
		preparedStatement.setInt(5, maxid);
		preparedStatement.setInt(6, ss.get(i).getSeatsrequired());
		preparedStatement.setInt(7, ss.get(i).getCostperflight());
		preparedStatement.executeUpdate();
		//below code for updating number of seats in flight table
		preparedStatement = con.prepareStatement("select AVAILABLESEATS from FLIGHTS where FLIGHTID=(?)");
		preparedStatement.setInt(1, ss.get(i).getFlightNumber());
		rs = preparedStatement.executeQuery();
		int availableseats=0;
		while(rs.next())
		{
		availableseats=rs.getInt("AVAILABLESEATS");
		}
		
		preparedStatement = con.prepareStatement("update FLIGHTS set AVAILABLESEATS = (?) where FlightID=(?)");
		preparedStatement.setInt(1, availableseats-ss.get(i).getSeatsrequired());
		preparedStatement.setInt(2, ss.get(i).getFlightNumber());
		preparedStatement.executeUpdate();
		//below code for updating number of seats in flightclass table
		
		preparedStatement = con.prepareStatement("select AVAILABLESEATS1 from FLIGHTCLASS where FLIGHTID=(?) and FLIGHTCLASS=(?)");
		preparedStatement.setInt(1, ss.get(i).getFlightNumber());
		preparedStatement.setString(2, ss.get(i).getTravelclass());
		rs = preparedStatement.executeQuery();
		int availableseatsforclass=0;
		while(rs.next())
		{
			availableseatsforclass=rs.getInt("AVAILABLESEATS1");
		}
		int balance=0;
		preparedStatement = con.prepareStatement("update FLIGHTCLASS set AVAILABLESEATS1 = (?) where FlightID=(?) and FLIGHTCLASS=(?)");
		preparedStatement.setInt(1, availableseatsforclass-ss.get(i).getSeatsrequired());
		preparedStatement.setInt(2, ss.get(i).getFlightNumber());
		preparedStatement.setString(3, ss.get(i).getTravelclass());
		preparedStatement.executeUpdate();
		//Below code to deduct the amount spent in from the account details(update the new balance in the card)
		preparedStatement = con.prepareStatement("select BALANCE from ACCOUNTDETAILS where USERS=(?)");
		preparedStatement.setString(1, username);
		rs=preparedStatement.executeQuery();
		while(rs.next())
		{
		balance=rs.getInt("BALANCE");
		}
		preparedStatement = con.prepareStatement("update ACCOUNTDETAILS set BALANCE = (?) where USERS=(?)");
		preparedStatement.setInt(1, balance-ss.get(i).getCostperflight());
		preparedStatement.setString(2, username);
		preparedStatement.executeUpdate();
		}
	}
}
