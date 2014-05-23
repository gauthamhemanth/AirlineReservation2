<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "javamodel.Flights"%>
   <%@ page import="java.util.ArrayList" %>
       <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking History</title>
<link rel="shortcut icon" href="aero2.ico" >
<script type="text/javascript" 
                  src="Scripts/common.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="Scripts/common.css" media="screen" />
</head>
<body background="2.jpg" style="background-color:#B2B2B2">

<jsp:useBean id="flightbean" class="javamodel.Flights" scope="request"></jsp:useBean>

<div class="signin-box" style="float:left;width:96.5%;margin-top:0px;margin-left:0px;border:1px">
<font size=100 color="Black" style="margin-left:442px" face="verdana">Fly High Airlines
</font>
<a href="QueryPage.jsp">
<img src="Homeicon.jpg" width="32" height="32" style="margin-left:256px"></a>
<form action="LogoutServlet" style="margin-left: 1681px;margin-top: -24px" method="post">
<input type="submit"  name="search" value="Logout" width="50px" />
</form>
</div>
<br><br><br><br><br><br><br><br><br>
<%! 	Connection con;
PreparedStatement preparedStatement;
ResultSet rs;
String name; %>


 <%
 
 if(session.getAttribute("Username")== null)
		 {
	 response.sendRedirect("LoginPage.jsp");
		 }
 else
 {
    String username=(String)session.getAttribute("Username");
 
    Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//	con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
	preparedStatement = con.prepareStatement("SELECT NAME FROM USERS where USERNAME = (?)");
	preparedStatement.setString(1, username);
	rs=preparedStatement.executeQuery();
	if(rs.next())
	{
	name=rs.getString(1);
	}
	out.println("<h2>Welcome,  "+name+"</h2>");
 }
    %>

<h3><label style="color:#FBFBEF">Booking History</label></h3>

<%ArrayList<Flights> ss=(ArrayList<Flights>)request.getAttribute("data"); 
int i=0;
            if(ss.size()>0){%>
         <table border="1" bgcolor="#F1F1F1">
		<tr><td><h4>Flight Date</h4></td><td><h4>Flight Number</h4></td><td><h4>Source</h4></td><td><h4>Destination</h4></td><td><h4>Departure Time</h4></td><td><h4>Arrival Time</h4></td><td><h4>Number of Seats</h4></td><td><h4>Number of Stops</h4></td><td><h4>Date of Booking</h4></td><td><h4>Flight Class</h4></td><td><h4>Cost</h4></td></tr>
         <%
          for (i = 0; i < ss.size(); i++) {
          %>
             <tr>
            <td>
                <center>
                    <% out.println(ss.get(i).getDateoftravel()); %>
                </center>
            </td>
            
            <td>
                <center>
                    <% out.println(ss.get(i).getFlightNumber()); %>
                </center>
            </td>
            
                         <td>
                <center>
                    <% out.println(ss.get(i).getSource()); %>
                </center>
            </td>
            
                         <td>
                <center>
                    <% out.println(ss.get(i).getDestination()); %>
                </center>
            </td>
            
             <td>
                <center>
                    <% out.println(ss.get(i).getDepttime()); %>
                </center>
            </td>            

             <td>
                <center>
                    <% out.println(ss.get(i).getArrtime()); %>
                </center>
            </td>
            
             <td>
                <center>
                    <% out.println(ss.get(i).getSeatsBooked()); %>
                </center>
            </td>
            
             <td>
                <center>
                    <% out.println(ss.get(i).getNumberOfStops()); %>
                </center>
            </td>            

             <td>
                <center>
                    <% out.println(ss.get(i).getDateOfBooking()); %>
                </center>
            </td>

             <td>
                <center>
                    <% out.println(ss.get(i).getTravelclass()); %>
                </center>
            </td>

             <td>
                <center>
                    <% out.println(ss.get(i).getPrice()); %>
                </center>
            </td>
            </tr>
            <% }%>
            </table>
            <% } else {%>
            	<h3>You don't have any booking history!</h3> 
            	<% }%>
    





</body>
</html>
