import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Garrett Chepil
//CMPP264 Java Day7 Exercise
//March 7

// class to create a connection to the Travel Experts Database
public class TravelExpertsDB {

	public static Connection Connect() throws ClassNotFoundException, SQLException
	{
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ICTOOSD", "ICTOOSD");
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts","root","");
		return conn;
	}
	
	
	
}
