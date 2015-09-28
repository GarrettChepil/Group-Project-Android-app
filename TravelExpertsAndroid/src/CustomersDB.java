import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class CustomersDB {
	
	static Connection conn;
	static ResultSet rs;
	static PreparedStatement stmt;
	static String sql;
	  
	public static Customer Authorize (String UserName, String pwd) throws ClassNotFoundException, SQLException {
		
		Customer customer = new Customer();

		conn = TravelExpertsDB.Connect();
		sql = "SELECT * FROM Customers WHERE CustFirstName = ? AND CustLastName = ?";
		try{
			
		
		
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, UserName);
			stmt.setString(2, pwd);
			rs = stmt.executeQuery();
			if(rs.next()) {
				customer.setCustomerId(rs.getInt("CustomerId"));
				customer.setCustFirstName(rs.getString("CustFirstName").trim());
				customer.setCustLastName(rs.getString("CustLastName").trim());
				customer.setCustAddress(rs.getString("CustAddress").trim());
				customer.setCustCity(rs.getNString("CustCity").trim());
				customer.setCustProv(rs.getString("CustProv").trim());
				customer.setCustPostal(rs.getString("CustPostal").trim());
				customer.setCustCountry(rs.getString("CustCountry").trim());
				customer.setCustHomePhone(rs.getString("CustHomePhone").trim());
				customer.setCustBusPhone(rs.getString("CustBusPhone").trim());
				customer.setCustEmail(rs.getString("CustEmail").trim());
			}
			rs.close();
			
		}
		finally { conn.close(); }
		return customer;
	}
	
	public static Customer getCustinfo (int custid) throws ClassNotFoundException, SQLException {
		
		Customer customer = new Customer();
		conn = TravelExpertsDB.Connect();
		sql = "SELECT * FROM Customers WHERE CustomerId = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, custid);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				customer.setCustomerId(rs.getInt("CustomerId"));
				customer.setCustFirstName(rs.getString("CustFirstName"));
				customer.setCustLastName(rs.getString("CustLastName"));
				customer.setCustAddress(rs.getString("CustAddress"));
				customer.setCustCity(rs.getNString("CustCity"));
				customer.setCustProv(rs.getString("CustProv"));
				customer.setCustPostal(rs.getString("CustPostal"));
				customer.setCustCountry(rs.getString("CustCountry"));
				customer.setCustHomePhone(rs.getString("CustHomePhone"));
				customer.setCustBusPhone(rs.getString("CustBusPhone"));
				customer.setCustEmail(rs.getString("CustEmail"));
			}
			rs.close();
			stmt.close();
				
		}
		finally {conn.close();}
		return customer;
	}
		
	public static ArrayList<Bookings> getBookings(int custid) throws ClassNotFoundException, SQLException {
		
		ArrayList<Bookings> bookings = new ArrayList<Bookings>();
		conn = TravelExpertsDB.Connect();
		sql = "SELECT * FROM Bookings WHERE CustomerId = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, custid);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Bookings b = new Bookings();
				b.setBookingId(rs.getInt("bookingId"));
				b.setBookingDate(rs.getDate("bookingDate").toString());
				b.setBookingNo(rs.getString("bookingNo"));
				b.setTravelerCount(rs.getInt("travelerCount"));
				b.setTripTypeId(rs.getString("tripTypeId"));
				bookings.add(b);
			}
			rs.close();
			stmt.close();
		}
		finally {conn.close();}
		return bookings;
	}
	
	public static ArrayList<BookingDetails> getBookingDetails(int bookingId) throws ClassNotFoundException, SQLException {
		
		ArrayList<BookingDetails> bookingDetails = new ArrayList<BookingDetails>();
		conn = TravelExpertsDB.Connect();
		sql = "SELECT * FROM BookingDetails WHERE BookingId = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, bookingId);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				BookingDetails bd = new BookingDetails();
				bd.setBasePrice(rs.getBigDecimal("basePrice").toString());
				bd.setBookingDetailId(rs.getInt("bookingDetailId"));
				bd.setClassId(rs.getString("classId"));
				bd.setDescription(rs.getString("description"));
				bd.setDestination(rs.getString("destination"));
				bd.setItineraryNo(String.valueOf(rs.getInt("itineraryNo")));
				bd.setRegionId(rs.getString("regionId"));
				bd.setTripEnd(rs.getDate("tripEnd").toString());
				bd.setTripStart(rs.getDate("tripStart").toString());
				bookingDetails.add(bd);
			}
			rs.close();
			stmt.close();
		}
		finally { conn.close(); }
		return bookingDetails;
	}

	public static boolean upDateCust(Customer customer, int custid) throws ClassNotFoundException, SQLException {
		System.out.println("in update");
		conn = TravelExpertsDB.Connect();
		sql = "UPDATE customers SET CustFirstName = ?, CustLastName = ?, CustAddress = ?, CustCity = ?, CustProv = ?,"+
				" CustPostal = ?, CustCountry = ?, CustHomePhone = ?, CustBusPhone = ?, CustEmail = ? " +
				"WHERE CustomerId = ?";
		try {
			stmt = conn.prepareStatement(sql);
//			stmt.setString(1, (String) request.getAttribute("CustFirstName"));
//			stmt.setString(2, (String) request.getAttribute("CustLastName"));
//			stmt.setString(3, (String) request.getAttribute("CustAddress"));
//			stmt.setString(4, (String) request.getAttribute("CustCity"));
//			stmt.setString(5, (String) request.getAttribute("CustProv"));
//			stmt.setString(6, (String) request.getAttribute("CustPostal"));
//			stmt.setString(7, (String) request.getAttribute("CustCountry"));
//			stmt.setString(8, (String) request.getAttribute("CustHomePhone"));
//			stmt.setString(9, (String) request.getAttribute("CustBusPhone"));
//			stmt.setString(10, (String) request.getAttribute("CustEmail"));
			stmt.setString(1, customer.getCustFirstName());
			stmt.setString(2, customer.getCustLastName());
			stmt.setString(3, customer.getCustAddress());
			stmt.setString(4, customer.getCustCity());
			stmt.setString(5, customer.getCustProv());
			stmt.setString(6, customer.getCustPostal());
			stmt.setString(7, customer.getCustCountry());
			stmt.setString(8, customer.getCustHomePhone());
			stmt.setString(9, customer.getCustBusPhone());
			stmt.setString(10, customer.getCustEmail());
			stmt.setInt(11, custid );
			
			int num = stmt.executeUpdate();
			if (num > 0) {
				return true;
			}
			else 
				return false;
			
			

		}
		finally { conn.close();}
	}
}

