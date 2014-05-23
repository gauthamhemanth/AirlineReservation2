<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ page import = "javamodel.Flights"%>
   <%@ page import="java.util.ArrayList;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transaction Confirmation</title>
<script type="text/javascript" 
                  src="Scripts/common.js"></script>
   <script type="text/javascript" 
                  src="Scripts/jquery.print-preview.js"></script>               
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="Scripts/common.css" media="screen" />
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
<br>
<br>

<br>

<div>
			<%
			int transactionresponse=0;
		    transactionresponse=(Integer)request.getAttribute("transactionresponse");
			if(transactionresponse==1)
			{	
			if(session.getAttribute("FlightDetailsList")!=null)
			{
            ArrayList<Flights> ss= (ArrayList<Flights>)session.getAttribute("FlightDetailsList");
            int i=0;
            int totalamount=0;%>
            <h4>Order placed successfully!</h4>
            <table border="1" bgcolor="#F1F1F1">
    		<tr><td><h4>Flight Date</h4></td><td><h4>Flight Number</h4></td><td><h4>Source</h4></td><td><h4>Destination</h4></td><td><h4>Departure Time</h4></td><td><h4>Arrival Time</h4></td><td><h4>Number of seats</h4></td><td><h4>Number of Stops</h4></td><td><h4>Flight Class</h4></td><td><h4>Cost per seat</h4></td><td><h4>Total Cost</h4></td></tr>
                <%if(ss!=null && ss.size()>0){
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
            <% }%>       </table>  <form onsubmit="print1()">
 <div id="passengerdetails" class="signin-box">
 <h3>Passenger details:</h3>
<label>
<strong class="uname-label">
Passenger details
</strong>
</label>
<div class="divunamepwd">
<label>
<strong class="uname-label">
First Name<sup>*</sup>
</strong>
</label>
<input type="text" size="40px" name="fname"/>
<label>
<strong class="uname-label">
Middle Name
</strong>
</label>
<input type="text" size="40px" name="mname"/>
<label>
<strong class="uname-label">
Last Name<sup>*</sup>
</strong>
</label>
<input type="text" size="40px" name="lname"/>
<label>
<strong class="uname-label">
Sex
</strong>
</label>
<input type="radio" name="type" value="Male">Male<br>
<input type="radio" name="type" value="Female">Female<br>
<br>
<strong class="uname-label">
Age:
</strong>
<input type="text" size="5px" name="Age"/>

<strong class="uname-label">
Address1:
</strong>
<input type="text" size="40px" name="Address1"/>
<strong class="uname-label">
Address2:
</strong>
<input type="text" size="40px" name="Address2"/>
<strong class="uname-label">
City
</strong>
<input type="text" size="40px" name="city"/>
<strong class="uname-label">
State
</strong>
<input type="text" size="40px" name="State"/>
<strong class="uname-label">
Country
</strong>
<input type="text" size="40px" name="Country"/>

<input type="submit" id="btn_print" value="Print"/>


</div>
  
</div>  
   </form>  <%}}}
			else if(transactionresponse==0)
			{

				// Displaying failure reason (Incorrect details/insufficient funds etc.) if transactionresponse==0 it means insufficient funds
			%>
			<br></br>
			<br></br>
			<h3>Insufficient Balance. Please deposit some cash and try again</h3>
			<%}else{
				// Displaying failure reason (Incorrect details/insufficient funds etc.) if transactionresponse==-1 card details entered are wrong

				%>
			<br></br>
			<br></br>
           <h3>The Card details you have entered are wrong. Please check and book them again</h3>
<%}%>
       
            
</body>
</html>