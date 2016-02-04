

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GetCustInfo
 */
@WebServlet("/GetCustInfo")
public class GetCustInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int custId;
		PrintWriter pw=response.getWriter();
        System.out.println("in the getCustservice");
        Enumeration<String> en;
        String temp;
        Customer customer;

        en=request.getParameterNames();

        while(en.hasMoreElements())
        {
            temp=en.nextElement().toString();
        System.out.println(temp+"-->"+request.getParameter(temp));
        }
        
        if (request.getParameter("custid") != null) {
        	custId = Integer.parseInt(request.getParameter("custid"));
        	
        	try {
				customer = CustomersDB.getCustinfo(custId);
				
				if(customer.getCustomerId() == 0){
	                JsonObject myObj = new JsonObject();
	                myObj.addProperty("success", false);
	                pw.println(myObj.toString());
	            }
				//if a valid customer code was sent
		        else {
		            Gson gson = new Gson(); 
		            //creates json from country object
		            JsonElement customerObj = gson.toJsonTree(customer);
		 
		            //create a new JSON object
		            JsonObject myObj = new JsonObject();
		            //add property as success
		            myObj.addProperty("success", true);
		            //add the country object
		            myObj.add("countryInfo", customerObj);
		            //convert the JSON to string and send back
		            pw.println(myObj.toString());
		            System.out.println(myObj.toString());
		        }
		        pw.close();
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	//if invalid country code 
            
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
