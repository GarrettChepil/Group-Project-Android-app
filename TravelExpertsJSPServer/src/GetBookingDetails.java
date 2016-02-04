

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
//garrett
/**
 * Servlet implementation class GetBookingDetails
 */
@WebServlet("/GetBookingDetails")
public class GetBookingDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBookingDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int bookingId;
		
		PrintWriter pw=response.getWriter();
        System.out.println("in the GetBookingDetails");
        Enumeration<String> en;
        String temp;

        en=request.getParameterNames();

        while(en.hasMoreElements())
        {
            temp=en.nextElement().toString();
        System.out.println(temp+"-->"+request.getParameter(temp));
        }
        
        if(request.getParameter("bookingId")!=null)
        {
             bookingId=Integer.parseInt(request.getParameter("bookingId"));
            //System.out.println(uname+""+pwd);
             try {
            	 
            	 ArrayList<BookingDetails> bookingDetails = CustomersDB.getBookingDetails(bookingId);
            	 
            	 Gson gson = new Gson(); 
            	 
            	 //create a new JSON object
            	 JsonObject myObj = new JsonObject();
            	 
            	 
            	 JsonArray jbookingsDetails = new JsonArray();
            	 for(BookingDetails b: bookingDetails) {
            		 JsonElement bookingobj = gson.toJsonTree(b);
            		 jbookingsDetails.add(bookingobj);
            	 }
	            
            	 myObj.add("bookings", jbookingsDetails);
            	 pw.println(myObj.toString());
            	 System.out.println(myObj.toString());
            	 
             }
             
             
             catch(Exception e)

             {
                 e.printStackTrace();
                 System.out.println("connection failure...!");
                 pw.println("connection failure...!");
             }
         
        }

	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
