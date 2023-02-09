package mavensrc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class Springjdbc {
	private static JdbcTemplate jdbcTemplate; 
		
	static {
			ApplicationContext context=new ClassPathXmlApplicationContext("webappconfig.xml");
			jdbcTemplate=(JdbcTemplate)context.getBean("jdbcTemplate");			
		}
	public static JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
}
