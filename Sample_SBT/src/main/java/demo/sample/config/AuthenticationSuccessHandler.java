package demo.sample.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
	
	Log logger = LogFactory.getLog(getClass());
	
	@Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		return null;
    }
}
