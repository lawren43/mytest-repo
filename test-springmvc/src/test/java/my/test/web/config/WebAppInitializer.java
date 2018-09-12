package my.test.web.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class WebAppInitializer extends AbstractDispatcherServletInitializer {

	@Override
	protected WebApplicationContext createServletApplicationContext() {
		XmlWebApplicationContext ctx=new XmlWebApplicationContext();
		ctx.setConfigLocation("/WEB-INF/springconfig/dispatchservletcontext.xml");
		return ctx;
	}

	@Override
	protected String[] getServletMappings() {

		return new String[] {"/"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		XmlWebApplicationContext ctx=new XmlWebApplicationContext();
		ctx.setConfigLocation("/WEB-INF/springconfig/service.xml"); //如果有多個xml要載入的話，
													                //可以選擇setConfigLocations，以陣列型式傳入
		return ctx;
	}

}
