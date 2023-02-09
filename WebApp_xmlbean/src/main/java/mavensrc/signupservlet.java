package mavensrc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@WebServlet("/SignUp")       //rem no ; here
public class signupservlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String paswd=req.getParameter("password");
		String phone=req.getParameter("phoneno");
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cubic","root","root");
//			PreparedStatement ps=conn.prepareStatement("insert into tableday8 values(?,?,?)");
//			//
//			ps.setString(1, email);
//			ps.setString(2, paswd);
//			ps.setString(3, phone);
//						
//			ps.execute();
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//above code is used in spring as below but first 
		//you have to import dependency for jdbc datasource and template in xml file under resources
		
//		ApplicationContext context=new ClassPathXmlApplicationContext("webappconfig.xml");
		//if you get cannot convert from... check import of AC, should be 
		//	import org.springframework.context.ApplicationContext not ...catalina...
		
//		JdbcTemplate jdbctemplate=(JdbcTemplate)context.getBean("jdbcTemplate");
		String sqlCommand="insert into tableday8 values(?,?,?)";
		//here check J or jdbcTemplate and the import used, above 2 lines can also be shortened by a static method
		Object[] input= {email,paswd,phone};
		//above line refers to 
		Springjdbc.getJdbcTemplate().update(sqlCommand,input);
		
		req.setAttribute("message1", "You have successfully registered.");
		req.getRequestDispatcher("promptPage.jsp").forward(req, resp);
		//dont forget to add .jsp 
		
	}
}
