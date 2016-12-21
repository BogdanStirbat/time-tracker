package com.bstirbat.timetracker.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class TimeTrackerWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        WebApplicationContext context = getContext();
        container.addListener(new ContextLoaderListener(context));
        ServletRegistration.Dynamic registration = container.addServlet("rest", new DispatcherServlet(context));
        registration.setLoadOnStartup(1);
        registration.addMapping("/rest/*");
    }

    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.bstirbat.timetracker.config");
        return context;
    }

}
