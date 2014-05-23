package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javamodel.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TransactionConfirmation")
public class TransactionConfirmation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionConfirmation() {
        super();
       
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Flights> ss=(ArrayList<Flights>) session.getAttribute("FlightDetailsList");
		BankModel bank=new BankModel();
		Bookings b=new Bookings();
		int bankresult;
		String cardnumber=request.getParameter("cardnumber");
		String nameoncard=request.getParameter("nameoncard");
		String expirydate=request.getParameter("expirydate");
		String cvvcode=request.getParameter("Cvvcode");
		String type=request.getParameter("type");
		int totalamount=0;//Integer.parseInt(request.getParameter("totalamount").trim());
		if(!ss.isEmpty())
		{
			for(int i=0;i<ss.size();i++)
			{
				totalamount+=ss.get(i).getCostperflight();
			}
		}
		String unamesession=(String) session.getAttribute("Username");
		try {
			bankresult=bank.validatebankdetails(cardnumber,nameoncard,expirydate,cvvcode,type,unamesession,totalamount);
			
		if(bankresult==1)
		{
				if(ss.size()>0)
					b.updatedatabase(ss,unamesession);
		}
				request.setAttribute("transactionresponse", bankresult);
				RequestDispatcher rd=request.getRequestDispatcher("/TransConfirmation.jsp");
				rd.forward(request, response);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
