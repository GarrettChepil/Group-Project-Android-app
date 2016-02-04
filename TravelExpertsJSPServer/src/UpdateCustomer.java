

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//Garrett
/**
 * Servlet implementation class UpdateCustomer
 */
@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
        
        if(request.getParameter("CustomerId")!=null)
        {
        	try {
        		Customer newcust = new Customer();
        		int custid = Integer.parseInt((String) request.getParameter("CustomerId"));
        		newcust.setCustFirstName((String) request.getParameter("CustFirstName"));
    			newcust.setCustLastName((String) request.getParameter("CustLastName"));
    			newcust.setCustAddress((String) request.getParameter("CustAddress"));
    			newcust.setCustCity((String) request.getParameter("CustCity"));
    			newcust.setCustProv((String) request.getParameter("CustProv"));
    			newcust.setCustPostal((String) request.getParameter("CustPostal"));
    			newcust.setCustCountry((String) request.getParameter("CustCountry"));
    			newcust.setCustHomePhone((String) request.getParameter("CustHomePhone"));
    			newcust.setCustBusPhone((String) request.getParameter("CustBusPhone"));
    			newcust.setCustEmail((String) request.getParameter("CustEmail"));
        		
				if(CustomersDB.upDateCust(newcust, custid)) {
					pw.print("true");
				}
				else
					pw.print("false");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
