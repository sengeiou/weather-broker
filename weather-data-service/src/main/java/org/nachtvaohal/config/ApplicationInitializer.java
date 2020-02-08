//package org.nachtvaohal.config;
//
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletRegistration;
//
//public class ApplicationInitializer implements WebApplicationInitializer
//{
//    @Override
//    public void onStartup(ServletContext container)
//    {
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WeatherDataServiceConfig.class);
//
//        // Manage the lifecycle of the root application context
//        container.addListener(new ContextLoaderListener(context));
//
//        // Create the dispatcher servlet's Spring application context
////        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
////        dispatcherContext.register(DispatcherConfig.class);
//
//        ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
//                new DispatcherServlet(context));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
//}