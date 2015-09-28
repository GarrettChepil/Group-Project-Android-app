import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//Garrett

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class AuthService extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Objects used...............



        String uname=null;
        String pwd=null;
        PrintWriter pw=response.getWriter();
        System.out.println("in the AuthService");
        Enumeration<String> en;
        String temp;

        en=request.getParameterNames();

        while(en.hasMoreElements())
        {
            temp=en.nextElement().toString();
        System.out.println(temp+"-->"+request.getParameter(temp));
        }


    // This is used because my one servlet class is interacting with more than one activity and for each activity i have different connection:  
        if(request.getParameter("username")!=null&&request.getParameter("password")!=null)
        {
             uname=request.getParameter("username");
             pwd=request.getParameter("password");
            //System.out.println(uname+""+pwd);
             try {
            	 
             
            	 Customer customer = CustomersDB.Authorize(uname, pwd);
            	 
            	 if(customer.getCustomerId() != 0) {
            		 ArrayList<Bookings> bookings = CustomersDB.getBookings(customer.getCustomerId());
            		 Gson gson = new Gson(); 
 		            //creates json from country object
 		            JsonElement customerObj = gson.toJsonTree(customer);
 		 
 		            //create a new JSON object
 		            JsonObject myObj = new JsonObject();
 		            //add property as success
 		            myObj.addProperty("login", true);
 		            //add the country object
 		            myObj.add("CustomerInfo", customerObj);
 		            
 		            
 		            JsonArray jbookings = new JsonArray();
 		            for(Bookings b: bookings) {
 		            	JsonElement bookingobj = gson.toJsonTree(b);
 		            	jbookings.add(bookingobj);
 		            }
 		            
 		            myObj.add("bookings", jbookings);
 		            
 		            
 		            //convert the JSON to string and send back
 		            pw.println(myObj.toString());
 		            System.out.println(myObj.toString());
            		 System.out.println("login Successful");
            	 }
            	 else {
            		 JsonObject myObj = new JsonObject();
                     myObj.addProperty("login", false);
                     pw.println(myObj.toString());
                     System.out.println("login failed. Please check your credentials.");

            	 }
             }
          catch(Exception e)

                 {
                     e.printStackTrace();
                     System.out.println("connection failure...!");
                     pw.println("connection failure...!");
                 }
             
          
        }// login-if-condition
 }//doGet

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}