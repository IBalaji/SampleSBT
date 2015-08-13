package demo.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import demo.sample.service.LoginServiceManager;
import demo.sample.util.SampleURLMapper;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		LoginServiceManager loginService;
	
		@Autowired
		CustomLogoutHandler logoutHandler;
		
		@Autowired
		AuthenticationSuccessHandler successHandler;
		
		@Autowired
		AuthenticationFailureHandler failureHandler;
		
		@Override
	     protected void configure(HttpSecurity http) throws Exception {
			
			// http.requiresChannel().anyRequest().requiresSecure();  
			
			successHandler.setAlwaysUseDefaultTargetUrl(true);
			
	        http.authorizeRequests()
	         	   .antMatchers("/*registration.htm").permitAll()
	               .antMatchers(HttpMethod.GET,SampleURLMapper.HOME_PAGE).permitAll()
	               
	               .antMatchers("/css/**").permitAll()
	               .antMatchers("/js/**").permitAll()
	               .antMatchers("/images/**").permitAll()
	                 
	               //.anyRequest().authenticated()
	                  .and().csrf().disable()
	                 .formLogin()
	                 .loginPage(SampleURLMapper.HOME_PAGE)
	                 .successHandler(successHandler)
	                 .failureHandler(failureHandler)
	                 .usernameParameter("username")
	                 .passwordParameter("password")
	                 .permitAll()
	                 .and()
	                 .logout()
	                 .deleteCookies("JSESSIONID")
	                 .invalidateHttpSession(true)
	                 .logoutSuccessHandler(logoutHandler)
	                 .permitAll()
	                 .and()
	               .rememberMe()
	                 .and()
	               .sessionManagement()
	                 .sessionFixation().none()
	                 .maximumSessions(1);
			
	     }

	     @Autowired
	     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	 auth.userDetailsService(loginService).passwordEncoder(new Md5PasswordEncoder()); 
	     }
	}
	
