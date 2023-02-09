package mavensrc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@WebServlet("/deleteRow")       //rem no ; here
public class delinfoserv2 extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uemail=req.getParameter("key");
		String demail=req.getParameter("keydel");
		
		try {			
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cubic","root","root");			
//			if(demail!=null) {
//				PreparedStatement ps=conn.prepareStatement("delete from tableday8 where email=?");
//				ps.setString(1,demail);
//				ps.execute();
//			}			
//			PreparedStatement ps1=conn.prepareStatement("select email,password,phone from tableday8");
//			ResultSet rs=ps1.executeQuery();
//			List<userData> listOfUser=new ArrayList<userData>();
//			while(rs.next()) {
//				String temail=rs.getString("email");
//				String tpaswd=rs.getString("password");
//				String tphone=rs.getString("phone");
//				
//				if(temail.equals(uemail)) {
//					userData user=new userData(temail,tpaswd,tphone);
//					req.setAttribute("user", user);
//				}
//				else {
//					listOfUser.add(new userData(temail,tpaswd,tphone));
//				}
//			}
//			req.setAttribute("listofuser", listOfUser);
			
			//2 now trying same with jdbc template and xml configuration
			//	ApplicationContext appContext=new ClassPathXmlApplicationContext("webappconfig.xml");
			//	JdbcTemplate tempJdbc=(JdbcTemplate)appContext.getBean("jdbcTemplate");
			// jdbctemplate creation  handled by SpringJdbc.getJdbcTemplate()
			
			String sqlCommand=("delete from tableday8 where email=?");
			Object[] mail= new Object[] {demail};
			Springjdbc.getJdbcTemplate().update(sqlCommand,mail);
			
			String sqlCommand2=("select email,password,phone from tableday8");
			List<userData> userList=Springjdbc.getJdbcTemplate().query(sqlCommand2, new BeanPropertyRowMapper<>(userData.class));
			
			for(userData userInstance:userList) {
				if(userInstance.getEmail().equals(uemail)) {
					req.setAttribute("user", userInstance);
			}
			}
			req.setAttribute("listofuser", userList);	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("message3", "All stored credentials are: ");
		req.getRequestDispatcher("landingPage.jsp").forward(req, resp);
		//dont forget to add .jsp 
		
	}
}
