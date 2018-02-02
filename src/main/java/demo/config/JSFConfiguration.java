package demo.config;

import javax.annotation.ManagedBean;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.sun.faces.config.FacesInitializer;
import com.sun.faces.config.InitFacesContext;
import demo.scope.ViewScope;
import demo.view.JSFSpringBean;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Configuration
public class JSFConfiguration implements ServletContextAware {

    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public ServletRegistrationBean facesServletRegistration() {
        return new JsfServletRegistrationBean();
    }

    @Bean
    public ViewScope viewScope() {
        return new ViewScope();
    }

    /**
     * Allows the use of @Scope("view") on Spring @Component, @Service and @Controller beans
     */
    @Bean
    public CustomScopeConfigurer scopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("view", viewScope());
        configurer.setScopes(hashMap);
        return configurer;
    }

    @Bean
    public JSFSpringBean jsfSpringBean() {
        return new JSFSpringBean();
    }

    @Configuration
    @Profile("default")
    static class ConfigureJSFContextParameters implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) {
            servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
            servletContext.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
            servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
            servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
            servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");

        }
    }

    public class JsfServletRegistrationBean extends ServletRegistrationBean {

        public JsfServletRegistrationBean() {
            super();
        }

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {

            FacesInitializer facesInitializer = new FacesInitializer();
            Set<Class<?>> clazz = new HashSet<>();
            clazz.add(ManagedBean.class);
            clazz.add(JSFConfiguration.class);
            facesInitializer.onStartup(clazz, servletContext);
            InitFacesContext.getCurrentInstance();
        }
    }
}
