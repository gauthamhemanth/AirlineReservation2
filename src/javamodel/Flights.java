
package javamodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Flights {
private String source;
private String destination;
private String dateoftravel;
private String travelclass;
private int seatsrequired;
ArrayList<Flights> flight;
private int price;
private String depttime;
private String arrtime;
private int flightnumber;
private int numberofstops;
private int seatsbooked;
private String dateofbooking;
private int costperflight;

public String getSource()
{
return source;
}

public void setSource(String source)
{
this.source=source;
}

public int getFlightNumber()
{
	return flightnumber;
}

public void setFlightNumber(int flightnumber)
{
	this.flightnumber = flightnumber;
}

public String getDateOfBooking()
{
	return dateofbooking;
}

public void setDateOfBooking(String dateofbooking)
{
	this.dateofbooking = dateofbooking;
}

public int getSeatsBooked()
{
	return seatsbooked;
}

public void setSeatsBooked(int seatsbooked)
{
	this.seatsbooked = seatsbooked;
}

public int getNumberOfStops()
{
	return numberofstops;
}

public void setNumberOfStops(int numberofstops)
{
	this.numberofstops=numberofstops;
}

public String getDepttime()
{
return depttime;
}

public void setDepttime(String depttime)
{
this.depttime=depttime;
}

public String getArrtime()
{
return arrtime;
}

public void setArrtime(String arrtime)
{
this.arrtime=arrtime;
}

public void setPrice(int price)
{
	this.price=price;
}

public int getPrice()
{
	return price;
}

public ArrayList<Flights> getFlight()
{
return flight;

}
 
public void setFlight(ArrayList<Flights> flight)
{
this.flight=flight;
}

public String getDestination()
{
return destination;

}
 
public void setDestination(String destination)
{
this.destination=destination;
}
 
public String getDateoftravel()
{
return dateoftravel;

}
 
public void setDateoftravel(String dateoftravel)
{
this.dateoftravel=dateoftravel;
}
 
public String getTravelclass()
{
return travelclass;

}
 
public void setTravelclass(String travelclass)
{
this.travelclass=travelclass;
}
 
public int getSeatsrequired()
{
return seatsrequired;

}
 
public void setSeatsrequired(int seatsrequired)
{
this.seatsrequired=seatsrequired;
}
public int getCostperflight() {
	return costperflight;
}

public void setCostperflight(int costperflight) {
	this.costperflight = costperflight;
}
 
public ArrayList<Flights> searchflights() throws SQLException, ClassNotFoundException
{ 
flight=new ArrayList<Flights>();
Connection con;
PreparedStatement preparedStatement;
ResultSet rs;
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection(
		"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
preparedStatement = con.prepareStatement("SELECT * FROM FLIGHTS, FLIGHTCLASS WHERE SOURCE = (?) and DESTINATION=(?) and DATEOFTRAVEL=(?)   and FLIGHTCLASS.FLIGHTID=FLIGHTS.FLIGHTID and FLIGHTCLASS.FLIGHTCLASS=(?) and FLIGHTCLASS.AVAILABLESEATS1>(?)");
preparedStatement.setString(1, getSource());
preparedStatement.setString(2, getDestination());
preparedStatement.setString(3, getDateoftravel());
preparedStatement.setString(4, getTravelclass());
preparedStatement.setInt(5, getSeatsrequired());
rs=preparedStatement.executeQuery();
while(rs.next())
{
	Flights fd=new Flights();
	fd.setFlightNumber(rs.getInt(1));
	fd.setSource(rs.getString("SOURCE"));
	fd.setDestination(rs.getString("DESTINATION"));
	fd.setDateoftravel(rs.getString("DATEOFTRAVEL"));
	fd.setSeatsrequired(rs.getInt("AVAILABLESEATS1"));
	fd.setPrice(rs.getInt("COST"));
	fd.setDepttime(rs.getString("DEPARTURETIME"));
	fd.setArrtime(rs.getString("ARRIVALTIME"));
	fd.setNumberOfStops(rs.getInt("NUMBEROFSTOPS"));
	flight.add(fd);
	
}

return flight;
}


public ArrayList<Flights> getcompletedetails() throws SQLException, ClassNotFoundException
{
	flight=new ArrayList<Flights>();
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet rs;
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//	con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
	preparedStatement = con.prepareStatement("SELECT * FROM FLIGHTS, FLIGHTCLASS WHERE FLIGHTS.FLIGHTID=(?) AND FLIGHTCLASS.FLIGHTCLASS=(?) AND FLIGHTCLASS.FLIGHTID=FLIGHTS.FLIGHTID");
	preparedStatement.setInt(1, getFlightNumber());
	preparedStatement.setString(2, getTravelclass());
	rs=preparedStatement.executeQuery();
	while(rs.next())
	{
		Flights fd=new Flights();
		fd.setFlightNumber(rs.getInt(1));
		fd.setSource(rs.getString("SOURCE"));
		fd.setDestination(rs.getString("DESTINATION"));
		fd.setDateoftravel(rs.getString("DATEOFTRAVEL"));
		fd.setSeatsrequired(rs.getInt("AVAILABLESEATS1"));
		fd.setPrice(rs.getInt("COST"));
		fd.setDepttime(rs.getString("DEPARTURETIME"));
		fd.setArrtime(rs.getString("ARRIVALTIME"));
		fd.setTravelclass(rs.getString("FLIGHTCLASS"));
		fd.setNumberOfStops(rs.getInt("NUMBEROFSTOPS"));
		flight.add(fd);
		
	}

	return flight;
	
	

	
}

public ArrayList<Flights> checkavailableseats(int flightid, int seatsselected ,String classtype) throws SQLException, ClassNotFoundException
{
	flight=new ArrayList<Flights>();
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet rs;
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//	con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
	preparedStatement = con.prepareStatement("SELECT * FROM FLIGHTS, FLIGHTCLASS WHERE FLIGHTS.FLIGHTID=(?) AND FLIGHTCLASS.FLIGHTCLASS=(?) AND FLIGHTCLASS.FLIGHTID=FLIGHTS.FLIGHTID");
	preparedStatement.setInt(1, flightid);
	preparedStatement.setString(2, classtype);
	rs=preparedStatement.executeQuery();
	while(rs.next())
	{
		if(rs.getInt("SEATS")<seatsselected)
		{
			return flight;
		}
		else
		{
			
		Flights fd=new Flights();
		fd.setCostperflight(rs.getInt("COST")*seatsselected);
		fd.setFlightNumber(rs.getInt(1));
		fd.setSource(rs.getString("SOURCE"));
		fd.setDestination(rs.getString("DESTINATION"));
		fd.setDateoftravel(rs.getString("DATEOFTRAVEL"));
		fd.setSeatsrequired(seatsselected);
		fd.setPrice(rs.getInt("COST"));
		fd.setDepttime(rs.getString("DEPARTURETIME"));
		fd.setArrtime(rs.getString("ARRIVALTIME"));
		fd.setTravelclass(rs.getString("FLIGHTCLASS"));
		fd.setNumberOfStops(rs.getInt("NUMBEROFSTOPS"));
		flight.add(fd);
		
	}
	}
	return flight;
	
	

	
}




}
