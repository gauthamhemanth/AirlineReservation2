<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="Scripts/Styles.css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Search</title>
<link rel="shortcut icon" href="aero2.ico" >
<script type="text/javascript" 
                  src="Scripts/common.js"></script>


  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="Scripts/common.css" media="screen" />
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
   <script>
   $(function() {
    $( "#txt_dateoftravel" ).datepicker({ minDate: 0, maxDate: " +3M " });
  });
   
   </script>
</head>
<body background="2.jpg" style="background-color:#B2B2B2">
<div class="signin-box" style="float:left;width:96.5%;margin-top:0px;margin-left:0px;border:1px">
<font size=100 color="Black" style="margin-left:442px" face="verdana">Fly High Airlines
</font>
<form action="BookingHistory" style="margin-left: 1550px" method="post">


<input type="submit" name="bookinghistory" value="Booking History" width="80px" />  

</form>
<form action="LogoutServlet" style="margin-left: 1681px;margin-top: -24px" method="post">
<input type="submit"  name="search" value="Logout" width="50px" />
</form>
</div>


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


<form action="Flightsearchquery" method="post" onsubmit="queryvalidation()">
<div class = "Divstyle2">
<div style="width:100px">Source : 
<input type="text" name="source" id="source" value="Start city" onclick="this.value=''" onkeyup="showState(this.value)"/></div><br>
<div id="country"></div>
<div style="width:100px">Destination :<input type="text" name="destination" value="End City" onclick="this.value=''"/> </div>
<br>
<div style="width:100px">Date of Travel: <input type="text" value="mm/dd/yyyy" name="dateoftravel" id="txt_dateoftravel" onclick="this.value=''"></div><br>
<div style="width:100px">Seats: <select name="seatsvalue">
  
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select> </div><br>
<div style="width:100px">Class: <select name="classvalue">
  
  <option value="Business">Business</option>
  <option value="FirstClass">First Class</option>
  <option value="Economy">Economy</option>
</select> </div> <br>
<input type="submit" name="search" value="Search" width="50px" />

</div>
</form>
</body>
</html>