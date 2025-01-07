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

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final String query = "SELECT ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA";


	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException{
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		// load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		// generate the connection
		try(Connection con = DriverManager.getConnection("jdbc:mysql:///book","root","Servlet@5644");
				PreparedStatement ps = con.prepareStatement(query);){
				ResultSet rs = ps.executeQuery();
				pw.println("<table>");
				pw.println("<tr>");
				pw.println("<th>Book Id</th>");
				pw.println("<th>Book Name</th>");
				pw.println("<th>Book Edition</th>");
				pw.println("<th>Book Price</th>");
				pw.println("<th>Edit</th>");
				pw.println("<th>Delete</th>");
				pw.println("</tr>");
				while(rs.next()) {
					pw.println("<tr>");
					pw.println("<td>"+rs.getInt(1)+"</td>");
					pw.println("<td>"+rs.getString(2)+"</td>");
					pw.println("<td>"+rs.getString(3)+"</td>");
					pw.println("<td>"+rs.getFloat(4)+"</td>");
					pw.println("<td><a>  </a</td>");
					pw.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a</td>");
					pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a</td>");
					pw.println("</tr>");
				}
				pw.println("</table>");
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
		
		
		
		
		
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}



}

