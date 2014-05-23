<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import = "javamodel.Flights"%>
   <%@ page import="java.util.ArrayList" %>
     <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction</title>
<link rel="shortcut icon" href="aero2.ico" >
<script type="text/javascript" 
                  src="Scripts/common.js"></script>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="Scripts/common.css" media="screen" />
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
   <script>
   $(function() {
    $( "#expirydate" ).datepicker({ minDate: 0, maxDate: "+5Y +1M +10D" });
  });
   </script>
</head>
<body background="2.jpg" style="background-color:#B2B2B2">



<div class="signin-box" style="float:left;width:100%;margin-top:0px;margin-left:0px;border:1px">
<font size=100 color="Black" style="margin-left:442px">Fly High Airlines
</font>

<a href="QueryPage.jsp">
<img src="Homeicon.jpg" width="32" height="32" style="margin-left:874px"></a>
<form action="LogoutServlet" style="margin-left: 1681px;margin-top: -10px" method="post">
<input type="submit"  name="search" value="Logout" width="50px" />
</form>
<form action="BookingHistory" style="margin-left: 1550px;margin-top: -24px" method="post">
<input type="submit" name="bookinghistory" value="Booking History" width="80px" />  

</form>
</div>
<form action="TransactionConfirmation" method="post">
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
<h3><label style="color:#FBFBEF">Selected flight details:</label></h3>


<div id="signin" class="signin-box" style="margin:-15px 0 0">

<label>
<strong class="uname-label">
Payment Details
</strong>
</label>

<div class="divunamepwd">
<label>
<strong class="uname-label">
Card Number
</strong>
</label>

<input type="text" id="uname" name="cardnumber"  class="logininput"></input>
</div>
<div class="divunamepwd">
<label>
<strong class="uname-label">
Name as on Card
</strong>
</label>
<input type="text" id="nameoncard" name="nameoncard" class="logininput"/>
</div>
<div>
<label>
<strong class="uname-label">
Card Type
</strong>
</label>
<input type="radio" name="type" value="Visa">Visa<br>
<input type="radio" name="type" value="Mastercard">Master Card<br>

</div>
<br>

<div class="divunamepwd">
<label>
<strong class="uname-label">
Expiry Date
</strong>
</label>
<input type="text" id="expirydate" name="expirydate"  class="logininput"></input>

</div>

<div class="divunamepwd">
<label>
<strong class="uname-label">
CVV Code
</strong>
</label>
<input type="text" id="Cvvcode" name="Cvvcode"  class="logininput"></input>
</div>
<input type="submit" name="Confirm" value="Confirmtransaction"/>
<a href="QueryPage.jsp">
<input type="button" name="Cancel" value="Cancel"></input></a>

</div>
<div>
			<%
            ArrayList<Flights> ss=(ArrayList<Flights>)request.getAttribute("data");
            int i=0;
            int totalamount=0;%>
            <table border="1" bgcolor="#F1F1F1">
    		<tr><td><h4>Flight Date</h4></td><td><h4>Flight Number</h4></td><td><h4>Source</h4></td><td><h4>Destination</h4></td><td><h4>Departure Time</h4></td><td><h4>Arrival Time</h4></td><td><h4>Number of seats</h4></td><td><h4>Number of Stops</h4></td><td><h4>Flight Class</h4></td><td><h4>Cost per seat</h4></td><td><h4>Total Cost</h4></td></tr>
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
                    <% out.println(ss.get(i).getSeatsrequired()); %>
                </center>
            </td>
            
             <td>
                <center>
                    <% out.println(ss.get(i).getNumberOfStops()); %>
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
            <td>
                <center>
                    <%out.println(ss.get(i).getCostperflight()); %>
                </center>
            </td>
            </tr>
            <% } %>
           
       </table>
       
</div>

 <%
 for (i = 0; i < ss.size(); i++) {
	 
	 totalamount+=ss.get(i).getCostperflight();
 }
%>
<h4>Total Amount to be paid:</h4> <input type="text" value="<%out.println(totalamount); %>" disabled="disabled" name="totalamount">
</form>

</body>
</html>