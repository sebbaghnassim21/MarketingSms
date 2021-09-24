package org.marketingsms.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@Configuration
public class AuthenticationProviderConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/db_marketingsms");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("");
	    return driverManagerDataSource;
	}
    
    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSource());
    	jdbcImpl.setUsersByUsernameQuery("select email,password,'True' enabled from user where email=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select email, role as role from user where email=?");
    	jdbcImpl.setRolePrefix("ROLE_");
    	return jdbcImpl;
    }
}
