<%@page language="java" import ="java.sql.*" %>  
 <%  
 String name=request.getParameter("count");  
 String buffer="<div>";  
 
 Class.forName("com.mysql.jdbc.Driver").newInstance();
 Connection	con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();  
// Connection con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123"); 
 Statement stmt = con.createStatement();  
 ResultSet rs = stmt.executeQuery("Select Source from Flights where Source LIKE '"+name+"%'");  
   while(rs.next()){
   buffer=buffer+rs.getString(1)+"<br>";  
   }  
 buffer=buffer+"</div>";  
 response.getWriter().println(buffer);  
 %>