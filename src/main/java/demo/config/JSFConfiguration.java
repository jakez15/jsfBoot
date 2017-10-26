package demo.config;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import demo.view.JSFSpringBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.web.context.ServletContextAware;


@Configuration
public class JSFConfiguration implements ServletContextAware {

    @Bean
    public FacesServlet facesServlet() {
        return new FacesServlet();
    }

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(facesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

    @Bean
    public ServletRegistrationBean facesCustomDirectoryServletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(facesServlet(), "/console/*.xhtml");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    @Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}

    @Bean
    public JSFSpringBean JSFSpringBean() {
        return new JSFSpringBean();
    }

    @Configuration
    @Profile("default")
    static class ConfigureJSFContextParameters implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext)
                throws ServletException {

            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
                    ".xhtml");
            servletContext.setInitParameter(
                    "javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
                    "Development");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
            servletContext.setInitParameter(
                    "javax.faces.FACELETS_REFRESH_PERIOD", "1");

        }
    }
}
