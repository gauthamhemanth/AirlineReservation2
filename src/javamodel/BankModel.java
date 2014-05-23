package javamodel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class BankModel {
	Connection con;
	PreparedStatement preparedStatement;
	ResultSet rs;
	int userid;
	public int validatebankdetails(String cardnumber,String nameoncard,String expirydate,String cvvcode, String type, String Username, int totalamount) throws ClassNotFoundException, SQLException
	{	int x=-1;
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/Airline", "root", "qwe123");

//		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
//		con = DriverManager.getConnection("jdbc:oracle:thin:@cci-ora02.uncc.edu:1521:class", "hvolety","qwe123");
		preparedStatement = con.prepareStatement("SELECT * from ACCOUNTDETAILS where USERS = (?) and CARDNUMBER=(?) and CVVCODE=(?) and NAMEASONCARD=(?) and CARDTYPE=(?)");
		preparedStatement.setString(1,Username);
		preparedStatement.setString(2,cardnumber);
		preparedStatement.setString(3,cvvcode);
		preparedStatement.setString(4,nameoncard);
		preparedStatement.setString(5,type);
		rs=preparedStatement.executeQuery();
		while(rs.next())
		{
			//variable x maintained to  Display failure reason (Incorrect details/insufficient funds etc.)
			x=0;
		if(rs.getInt("BALANCE")>=totalamount)
		{
			x=1;
			return x;
		}
		}
		return x;
	}


}
