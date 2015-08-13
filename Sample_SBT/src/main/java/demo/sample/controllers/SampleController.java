package demo.sample.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.sample.util.SampleURLMapper;
import demo.sample.util.SampleViewMapper;

@Controller
public class SampleController {

	protected final Log logger = LogFactory.getLog(getClass());

	@RequestMapping(value = SampleURLMapper.HOME_PAGE, method = RequestMethod.GET)
	public String loadHomePage(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		return SampleViewMapper.HOME_PAGE;
	}
}