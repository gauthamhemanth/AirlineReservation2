package javamodel;
import java.sql.*;

public class Usersjava {
	
	private Connection con;
	private PreparedStatement preparedStatement;
	private ResultSet rs;
	public boolean Register(String name1, String username1, String Password1) throws SQLException
	{
	  try {
		int userid=0;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
		preparedStatement = con.prepareStatement("SELECT * FROM USERS WHERE USERNAME = (?)");
		preparedStatement.setString(1, username1);
		rs=preparedStatement.executeQuery();
		if(!rs.next())
		{
		    
		   preparedStatement =con.prepareStatement("SELECT max(USERID) FROM Users ");
		   rs=preparedStatement.executeQuery();
		   while(rs.next())
		   {
			  userid =rs.getInt(1)+1;
		   }
		   preparedStatement = con.prepareStatement("INSERT INTO USERS(NAME,USERNAME,PASSWORD,USERID)"+ "VALUES (?, ?, ?,?)");
		    
		    preparedStatement.setString(1, name1);
		    preparedStatement.setString(2, username1);
		    preparedStatement.setString(3, Password1);
		    preparedStatement.setInt(4, userid);
		    preparedStatement.executeUpdate();
		    return true;
		}
		else
		{
		return false;
		}
		   
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	   return false;
	  
	}
	
	public int getUserName(String usrName, String pwd)
	{
		int returnvalue=-1;
	  try {
		  Class.forName("com.mysql.jdbc.Driver");

//		  	Class.forName("oracle.jdbc.driver.OracleDriver");
		  			   
	  } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	  }
	   
		try{

			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//			con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123"); 
		    Statement st=con.createStatement();
		    String sql="SELECT USERNAME, PASSWORD FROM USERS";
		    ResultSet rs = st.executeQuery(sql);
		    while(rs.next())
		    {
		    	String username = rs.getString(1);
		    	if(usrName.equals(username)){
		    		returnvalue=0;
		    		String password=rs.getString(2);
		    		if(pwd.equals(password)){
		    			returnvalue=1;
		    		}
		    	}
		    }
		    st.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return returnvalue;
		
		
	}

}
