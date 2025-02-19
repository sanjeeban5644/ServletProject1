package com.sanjeeban.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {
	private static final String query = "UPDATE BOOKDATA set bookname=?, bookedition=?,bookprice=? where id=?";
	


	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		// get the id of the record
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		/// get the edited data
		
		String bookName = req.getParameter("bookName");
		String bookEdition = req.getParameter("bookEdition");
		float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
		
		// load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		// generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Servlet@5644");
				PreparedStatement ps = con.prepareStatement(query);){
				
				ps.setString(1, bookName);
				ps.setString(2, bookEdition);
				ps.setFloat(3, bookPrice);
				
				ps.setInt(4, id);
				
				int count = ps.executeUpdate();
				
				if(count==1) {
					pw.println("<h2>Record is edited successfully</h2>");
				}else {
					pw.println("<h2>Record is not edited successfully</h2>");

				}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+e.getMessage()+"</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='bookList>Book List</a>");
		
		
		
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
