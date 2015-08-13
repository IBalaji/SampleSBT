package demo.sample.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import demo.sample.util.SampleURLMapper;

@Configuration
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler {

	Log logger = LogFactory.getLog(getClass());
	
	/**
	 * This method is used to get the logout time of a user and redirect it to the home page on logout
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,Authentication authentication) throws IOException, ServletException {
		response.sendRedirect(SampleURLMapper.HOME_PAGE);
	}
}