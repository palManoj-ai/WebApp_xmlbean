package mavensrc;

import java.io.IOException;
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

@WebServlet("/allData")       //rem no ; here
public class Moreinfoserv extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uemail=req.getParameter("key");

		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cubic","root","root");
//			
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
			
			//2 using jdbc template with xml config to do above 
//			ApplicationContext context=new ClassPathXmlApplicationContext("webappconfig.xml");
//			JdbcTemplate temp=(JdbcTemplate)context.getBean("jdbcTemplate");
			//template creation handled by new defined class Springjdbc 
			
			String sqlCmd="select email,password,phone from tableday8";
			List<userData> listOfUsers= Springjdbc.getJdbcTemplate().query(sqlCmd, new BeanPropertyRowMapper<>(userData.class));
			
			/*
			 * Iterator<userData> iterator=listOfUsers.iterator();
			 * 
			 * if(iterator.hasNext()) { userData userInstance=iterator.next();
			 * if(userInstance.getEmail().equals(uemail)) { req.setAttribute("user",
			 * userInstance); iterator.remove(); } }
			 */
			for(userData users:listOfUsers) {
				if(users.getEmail().equalsIgnoreCase(uemail)) {
					req.setAttribute("user",users);
				}
			}
			req.setAttribute("listofuser", listOfUsers);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		req.setAttribute("message3", "All stored credentials are: ");
		req.getRequestDispatcher("landingPage.jsp").forward(req, resp);
		//dont forget to add .jsp 
		
	}
}
