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

@WebServlet("/prompt")       //rem no ; here
public class promptservlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String temail=req.getParameter("email");
		String tpaswd=req.getParameter("password");
		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/cubic","root","root");
//			PreparedStatement ps=conn.prepareStatement("Select email,password,phone from tableday8 where email=? and password=?");
//			ps.setString(1, temail);
//			ps.setString(2, tpaswd);			
//			ResultSet rs=ps.executeQuery();
//			
//			if(rs.next()) {
//				userData userdata=new userData(rs.getString("email"),rs.getString("password"),rs.getString("phone"));
//				req.setAttribute("user", userdata);     
//				//try declaring it outside loop, know about var. scope in a loop, edit used if
//			}
		
			//above code using spring jdbc and xml configuration
			//	ApplicationContext context2=new ClassPathXmlApplicationContext("webappconfig.xml");
			//	JdbcTemplate template=(JdbcTemplate)context2.getBean("jdbcTemplate");
			//above 2 lines are being used repetitively so a method has been created in SpringJdbc  
			//and used in line 52
		
			String sqlCommand="Select email,password,phone from tableday8 where email=? and password=?";
			Object[] input= {temail,tpaswd};
			
			//userData user=(userData)SpringJdbc.getJdbcTemplate().query(sqlCommand, input, RowMapper<userData.class>);

//			@SuppressWarnings("deprecation")
//			userData user=(userData)SpringJdbc.getJdbcTemplate().queryForObject(sqlCommand, input, new BeanPropertyRowMapper<>(userData.class));
			List<userData> userList=Springjdbc.getJdbcTemplate().query(sqlCommand, input, new BeanPropertyRowMapper<>(userData.class));
			//why not queryforlist 
			//	for above line to work, need to implement the RowMapper interface
//			userData user=(userData)template.queryForObject(sqlCommand, input, new BeanPropertyRowMapper<>(userData.class));
			//  dep link:   https://www.javadoc.io/doc/org.springframework/spring-jdbc/latest/deprecated-list.html
			//why not queryForObject, why not queryForList, or query only
			// error above and solution to deprecated class
			//   https://stackoverflow.com/questions/65301011/jdbctemplate-queryforobject-and-query-is-deprecated-in-spring-what-should-i
			if(userList.size()>0) {
				req.setAttribute("user", userList.get(0));
				req.setAttribute("message2", "Your stored credentials are: ");
				req.getRequestDispatcher("landingPage.jsp").forward(req, resp);
				//setting first entry of list to user
			}else {
				
				req.setAttribute("error", "Wrong Login credentials, please try again:");
				req.getRequestDispatcher("signuppage.jsp").forward(req, resp);
				
			}
			
			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}

	}
}
