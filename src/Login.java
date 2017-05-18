import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.sql.*;
import java.sql.*;
	
public class Login extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
	try
	{
		int flag=0;
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		out.println("<html>\n<head>\n<title>Login Page</title>\n</head>");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection=null;
		connection =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pranith","pranith");
		Statement st=connection.createStatement();
		//out.println("Connected to database succesfully");
		ResultSet rs=st.executeQuery("select * from LOGIN");
		String username=req.getParameter("rollno");
		String password=req.getParameter("pwd");
		HttpSession session = req.getSession();
		session.setAttribute("username",username);
		session.setAttribute("password",password);
		while(rs.next())
		{
			if(username.equals(rs.getString(3))&&password.equals(rs.getString(2)))
				flag=1;	
		}
		if(flag==1)
		{
			res.sendRedirect("welcome.jsp");
		}
		else
		{
			out.println("<script>alert(\"Invalid username or password\");location.href='index.html';</script>");
		}
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	}
}
